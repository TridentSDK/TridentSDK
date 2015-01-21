package net.tridentsdk.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import net.tridentsdk.Coordinates;
import net.tridentsdk.Trident;
import net.tridentsdk.docs.InternalUseOnly;
import net.tridentsdk.entity.Entity;
import net.tridentsdk.entity.EntityProperties;
import net.tridentsdk.entity.EntityType;
import net.tridentsdk.factory.CollectFactory;
import net.tridentsdk.factory.Factories;
import net.tridentsdk.world.World;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.lang.ref.WeakReference;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A reference to an entity
 *
 * <p>Three constructors are provided. In the case that the stored value comes from an <em>alien-source</em>, or any
 * general situation where the value to be held by the WeakEntity is unknown and unspecified, the recommended factory
 * method to use is {@link #orEmpty(net.tridentsdk.entity.Entity)}. This prevents the creation of unnecessary instances
 * of WeakEntity where the value is always going to {@code null}. Because this method always returns the same instance
 * of a {@code null} holding WeakEntity, registration does not need to be executed, where registration means to place
 * the reference into a queue which will be cleared once the entity has been removed. in this context, and alien method
 * is: <em>"A method you call for which you have no control over the code, and further don’t even know what the code
 * does, other than the method signature</em> [...] <em>it could refer to calls to third party libraries</em>."</p>
 *
 * <p>Because entities are loaded with all of their data and AI, the removal of them entirely depends on the references
 * left held to it. If the entity is technically removed by the server, but still held by a plugin, for example, then
 * a memory leak will occur because the entity will never actually be removed. It is not updated for players, but the
 * reference stays in memory, wasting computing power on a non-existent entity.</p>
 *
 * <p>Use this class to store a link or bridge to the reference of an entity. The storage of an instance of this class
 * will only hold this class only, and the reference to the entity in this class may become {@code null} at any time,
 * in which case is indicated by {@link #isNull()} returning {@code true}.</p>
 *
 * <p>Much thought was put into the design of this class, especially if it should be made thread-safe. Because this is
 * an internal change, it can be easily done without breaking the API. However, the decision was made to make this class
 * thread-safe in order to preserve the fact that both the users of this API, including plugins and the server itself
 * is concurrent. It would decrease the burden to handle this safely using the internal implementation rather than
 * having the client code execute awkward constructs in order to keep consistency. This is OK for the implementation to
 * perform, but because plugin authors may not always think about the concurrent nature of this API, it was done at the
 * cost of performance to reduce bugs which are often difficult to find.</p>
 *
 * <p>This is the equivalent of {@link com.google.common.base.Optional} but for entities specifically, much like an
 * optional which stores a {@link java.lang.ref.WeakReference} of the specified property. While this class does use
 * WeakReference to manage the references, other methods to safeguard the reference is also implemented, such as removal
 * listeners.</p>
 *
 * <p>Internally, this class manages a queue of references which, in turn, are polled by a thread called
 * {@code Trident - Reference Handler}. Every call to clear a specific reference also runs the mark/sweep collector,
 * although it is invoked directly rather than using {@link #runMarkSweep()}. The implementation of the collector may be
 * found in the documentation for {@link  #runMarkSweep()}. Entities call
 * {@link #clearReferencesTo(net.tridentsdk.entity.Entity)} when they are removed to purge old references to that entity
 * after it has finished despawning. This method also runs the mark/sweep collector. This is only implemented as a
 * safeguard behind {@link java.lang.ref.WeakReference}, which is used to store the </p>
 *
 * <p>Here are examples on the usage of this class.</p>
 *
 * <p>WeakEntity can and should be used when storing entities, such as players inside a collection or reference.</p>
 * <pre><code>
 *     private final Map&lt;WeakEntity&lt;Player&gt;, Integer&gt; score =
 *         Maps.newHashMap();
 *
 *     // Add player
 *     Player player = ...
 *     int playerScore = ...
 *     score.put(WeakEntity.of(player), playerScore);
 *
 *     // Checking player
 *     for (WeakEntity&lt;Player&gt; weakEntity : score.keySet()) {
 *         // This is simply proof of concept, don't check if it is null
 *         // then try to obtain it
 *         // Or try to hide the player from himself/herself
 *         if (weakEntity.isNull())
 *             continue;
 *         weakEntity.obtain().hide(weakEntity);
 *     }
 * </code></pre>
 *
 * <p>WeakEntity acts like an optional which can be used to eliminate the need to check for {@code null}.</p>
 * <pre><code>
 *     // Finding the player
 *     // Don't do this in production-stage code, it doesn't work the way the name implies!
 *     public WeakEntity&lt;Bat&gt; findClosest(Player player, int range) {
 *         for (Entity entity : player.withinRange(range)) {
 *             if (entity instanceof Bat) {
 *                 // Unable to be null, so we use of(Entity)
 *                 // Instead of orEmpty(Entity)
 *                 return WeakEntity.of(entity);
 *             }
 *         }
 *
 *         // No bats near the player, it's empty
 *         return WeakEntity.empty();
 *     }
 *
 *     // Find a Bat closest to the player, or spawn a new one, and accelerate it upwards (just because)
 *     findClosest(player, 69)
 *         .or(EntityBuilder.create().build(TridentBat.class))
 *         .setVelocity(new Vector(0, 10, 0));
 * </code></pre>
 *
 * <p>Finally, WeakEntity can be used to find and prevent bugs which would otherwise cause NullPointerExceptions.</p>
 * <pre><code>
 *     // Using it to find bugs
 *     // Oh, leave it here for a while until I implement a different method, then I'll come back to it
 *     WeakEntity&lt;Player&gt; weakEntity = WeakEntity.of(null);
 *     ...
 *     // I was smart and used obtain() instead of entity()
 *     weakEntity.obtain().hide();
 *
 *     // Code errors
 *
 *     // Go back to the line you found in the stacktrace
 *     // Looks like it was on the line that tried to obtain the entity
 *     // from a WeakEntity
 *     // We remember that we implemented a find player method later on
 *
 *     // Final code
 *     WeakEntity&lt;Player&gt; weakEntity = WeakEntity.of(getPlayer());
 * </code></pre>
 *
 * @author The TridentSDK Team
 */
@ThreadSafe
public final class WeakEntity<T extends Entity> {
    private static final WeakEntity<?> NULL_ENTITY = new WeakEntity<>(new SafeReference<Entity>(null));
    private static final RefQueue REFERENCE_QUEUE = RefQueue.newQueue();

    // Not automatically instantiated by the server, won't be started if it is not needed
    static {
        Thread thread = new Thread(REFERENCE_QUEUE, "Trident - Reference Handler");
        //thread.setDaemon(true);
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
    }

    private final SafeReference<T> referencedEntity;

    private WeakEntity(SafeReference<T> referencedEntity) {
        this.referencedEntity = referencedEntity;
    }

    /**
     * Obtains a WeakEntity which holds a {@code null} referenced entity
     *
     * <p>This method does not produce a new object. The instance of an empty WeakEntity is held in a {@code static}
     * field inside this class.</p>
     *
     * @param <T> the type of entity to hold {@code null} for
     * @return a WeakEntity which holds {@code null}
     */
    public static <T extends Entity> WeakEntity<T> empty() {
        return (WeakEntity<T>) NULL_ENTITY;
    }

    /**
     * Obtains a WeakEntity which holds the specified entity
     *
     * <p>The entity which is specified will become {@code null} when it is dereferenced from the server.</p>
     *
     * <p>This may be used in the place of {@link #orEmpty(net.tridentsdk.entity.Entity)} if creation of a new
     * WeakEntity for a {@code null} entity is OK. In other words, this is a fast-path (albeit the fact that the
     * difference in performance is negligible in comparison with creation overhead and memory) for the
     * {@link #orEmpty(net.tridentsdk.entity.Entity)}.</p>
     *
     * @param referencedEntity the entity to hold until it is removed
     * @param <T> the type of entity to hold
     * @return a WeakEntity which holds the entity until it becomes {@code null}
     */
    public static <T extends Entity> WeakEntity<T> of(T referencedEntity) {
        return REFERENCE_QUEUE.put(referencedEntity);
    }

    /**
     * Obtains a WeakEntity which is either a {@link #empty()} reference or a reference
     * {@link #of(net.tridentsdk.entity.Entity)}, depending on if the specified entity is {@code null} or
     * non-{@code null}, respectively
     *
     * <p>If the returned WeakEntity holds {@code null}, and is equivalent to {@link #empty()}, then no instance of
     * WeakEntity was created.</p>
     *
     * <p>If the referenced entity is <em>known</em> to be {@code null}, then do not use this method. Although it is
     * still correct, if it is not possible for it to be {@code null}, use an {@link #empty()} WeakEntity.</p>
     *
     * @param referencedEntity the entity to obtain the reference for
     * @param <T> the type of entity to hold
     * @return a WeakEntity which holds the specified entity, or {@link #empty()} if the specified entity to reference
     *         is {@code null}
     */
    public static <T extends Entity> WeakEntity<T> orEmpty(T referencedEntity) {
        if (referencedEntity == null)
            return empty();
        return of(referencedEntity);
    }

    /**
     * Removes the references of the entity from any WeakEntity instances which are non-null
     *
     * <p>This method was meant to be used only for the implementation. The calling of this method by a non-Trident
     * class has no effect.</p>
     *
     * @param entity the entity to dereference and clear from WeakEntity
     */
    @InternalUseOnly
    public static void clearReferencesTo(Entity entity) throws IllegalAccessException {
        if (!Trident.isTrident())
            throw new IllegalAccessException("WeakEntities may only be cleared by a Trident class");
        REFERENCE_QUEUE.clearReference(entity);
    }

    /**
     * Forces the reference handler thread to run the {@code null} or garbage reference collection cycle and reclaim
     * memory lost by those references
     *
     * <p>This runs the mark sweeping strategy employed by the reference handler. Previous references are swept from the
     * session and the thread continues to mark the {@code null} references. They are placed into a collection until the
     * sweeping completes. When this step does complete, the collection is checked, then the references are purged from
     * the reference queue.</p>
     *
     * <p>Unlike Java's default GC implementation, this method is strongly bound. This always succeeds in running the
     * collection cycle.</p>
     */
    public static void runMarkSweep() {
        REFERENCE_QUEUE.beginSweep();
    }

    /**
     * Observes the reference to see if the entity is {@code null} or not accessible via the server anymore
     *
     * @return {@code true} to indicate the entity is removed and cannot be further referenced, in which case the
     *         instance of this object should also become {@code null}
     */
    public boolean isNull() {
        return referencedEntity.get() == null;
    }

    /**
     * Obtains the instance of the referenced entity, or, if it {@link #isNull()}, then this method will return the
     * specified fallback object, which must match the type of this object
     *
     * <p>The provided fallback should not be {@code null}. This is allowed, and is still correct, however, a more
     * effective strategy is to use {@link #entity()} instead.</p>
     *
     * @param fallback the entity to return in place of the reference in the case that the reference is {@code null}
     * @return the entity if non-{@code null}, or the fallback if it is
     */
    public T or(T fallback) {
        T ref = this.referencedEntity.get();
        return ref == null ? fallback : ref;
    }

    /**
     * Obtains the instance of the referenced entity, or {@code null} if it has been dereferenced
     *
     * @return the entity which this reference points to, or {@code null} if the entity no longer exists on the server
     */
    public T entity() {
        return this.referencedEntity.get();
    }

    /**
     * Obtains the referenced entity, but is fail-fast
     *
     * <p>This method will throw an {@link java.lang.IllegalStateException} if the referenced entity is {@code null}.
     * Usually, this should be used for debugging purposes, but can be used as a marker in the case that something
     * within the code goes wrong.</p>
     *
     * @return the referenced entity, can <strong>never</strong> be {@code null}
     */
    public T obtain() {
        T ref = this.referencedEntity.get();
        if (ref == null)
            throw new IllegalStateException("Obtained entity is null");
        return ref;
    }

    /**
     * This removes the reference to the entity in this particular instance of WeakEntity
     *
     * <p>If the referenced entity is {@code null} already, no changes will take effect. The call of this method also
     * has <em>happens-before</em> consistency to any subsequent inspection calls to this WeakEntity.</p>
     */
    public void clear() {
        this.referencedEntity.clear();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof WeakEntity))
            return false;
        WeakEntity entity = (WeakEntity) obj;
        return entity.entity().equals(this.entity());
    }

    @Override
    public int hashCode() {
        T ref = referencedEntity.get();
        if (ref == null)
            return 0;
        return ref.hashCode();
    }

    @Override
    public String toString() {
        return getClass() + "{referencedEntity = " + referencedEntity.get() + "}@" + hashCode();
    }

    private static class SafeReference<T> extends WeakReference<T> {
        private volatile int fence = 0;

        public SafeReference(T referent) {
            super(referent);
        }

        @Override
        public T get() {
            int barrier = fence;
            return super.get();
        }

        @Override
        public void clear() {
            super.clear();
            fence = 0;
        }
    }

    private static class RefQueue implements Runnable {
        static {
            Factories.init(new CollectFactory() {
                @Override
                public <K, V> ConcurrentMap<K, V> createMap() {
                    return new ConcurrentHashMap<>();
                }
            });
        }

        @GuardedBy("lock")
        private final Map<Entity, Node> references = Maps.newTreeMap(new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        });

        private final Lock lock = new ReentrantLock();
        private final Condition hasNodes = lock.newCondition();

        private volatile Node marked = Node.newNode(null, null);
        private final List<Entity> old = Lists.newArrayList();

        private RefQueue() {
        }

        public static RefQueue newQueue() {
            return new RefQueue();
        }

        public static void main(String[] args) {
            RefQueue queue = RefQueue.newQueue();
            Entity entity = new Entity() {
                @Override
                public void teleport(double x, double y, double z) {

                }

                @Override
                public void teleport(Entity entity) {

                }

                @Override
                public void teleport(Coordinates location) {

                }

                @Override
                public World world() {
                    return null;
                }

                @Override
                public Coordinates location() {
                    return null;
                }

                @Override
                public Vector velocity() {
                    return null;
                }

                @Override
                public void setVelocity(Vector vector) {

                }

                @Override
                public boolean onGround() {
                    return false;
                }

                @Override
                public Set<Entity> withinRange(double radius) {
                    return null;
                }

                @Override
                public String displayName() {
                    return null;
                }

                @Override
                public void setDisplayName(String name) {

                }

                @Override
                public boolean isNameVisible() {
                    return false;
                }

                @Override
                public boolean isSilent() {
                    return false;
                }

                @Override
                public int entityId() {
                    return 0;
                }

                @Override
                public UUID uniqueId() {
                    return null;
                }

                @Override
                public void remove() {

                }

                @Override
                public Entity passenger() {
                    return null;
                }

                @Override
                public void setPassenger(Entity entity) {

                }

                @Override
                public void eject() {

                }

                @Override
                public EntityType type() {
                    return null;
                }

                @Override
                public void applyProperties(EntityProperties properties) {

                }
            };

            new Thread(queue).start();

            WeakEntity ref = queue.put(entity);

            System.out.println(queue.references.get(entity).references().contains(ref));
            queue.beginSweep();
            System.out.println(queue.references.get(entity).references().contains(ref));
            ref.clear();
            System.out.println(queue.references.containsKey(entity));
            queue.beginSweep();
            System.out.println(queue.references.containsKey(entity));
            queue.clearReference(entity);
            System.out.println(queue.references.containsKey(entity));
        }

        public static void find(RefQueue queue, Entity entity) {
            System.out.println(queue.references.get(entity).references().iterator().next());
        }

        public WeakEntity put(Entity entity) {
            WeakEntity weakEntity = new WeakEntity<>(new SafeReference<>(entity));

            lock.lock();
            try {
                references.put(entity, Node.newNode(weakEntity, references.get(entity)));
            } finally {
                lock.unlock();
            }

            return weakEntity;
        }

        public void clearReference(Entity entity) {
            Node entities = null;

            lock.lock();
            try {
                entities = references.get(entity);
                hasNodes.signal();
            } finally {
                lock.unlock();
            }

            if (entities == null) return;
            for (WeakEntity<?> e : entities)
                e.clear();
        }

        public void beginSweep() {
            lock.lock();
            try {
                hasNodes.signal();
            } finally {
                lock.unlock();
            }
        }

        @Override
        public void run() {
            while (true) {
                // Step 1: find nodes
                Set<Map.Entry<Entity, Node>> entries;

                lock.lock();
                try {
                    entries = references.entrySet();
                } finally {
                    lock.unlock();
                }

                // Step 2: mark
                for (Map.Entry<Entity, Node> entry : entries) {
                    System.out.println(entry.getKey() + " " + entry.getValue());
                    Set<WeakEntity> weakRefs = entry.getValue().references();

                    for (WeakEntity ref : weakRefs) {
                        System.out.println(ref.toString());
                        if (ref.isNull()) {
                            marked = Node.newNode(ref, marked);
                        }
                    }

                    // Remove old keys
                    if (weakRefs.isEmpty()) {
                        old.add(entry.getKey());
                        return;
                    }
                }


                try {
                    lock.lockInterruptibly();
                    while (references.isEmpty())
                        hasNodes.await();

                    // Step 3: sweep
                    for (Entity entity : references.keySet())
                        references.get(entity).references().removeAll(marked.references());

                    marked.clean();

                    for (Entity entity : old)
                        references.remove(entity);

                    old.clear();
                } catch (InterruptedException ignored) {
                    // Run by a daemon thread, doesn't matter if it was interrupted
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    private static class Node implements Iterable<WeakEntity> {
        @GuardedBy("lock")
        private final Set<WeakEntity> weakEntities;
        private final Object lock = new Object();

        private Node(WeakEntity entity, Node node) {
            Set<WeakEntity> original;
            if (node == null)
                original = Sets.newHashSet();
            else original = node.weakEntities;

            if (original == null)
                original = Sets.newHashSet();
            original.add(entity);

            weakEntities = original;
        }

        public static Node newNode(WeakEntity entity, Node node) {
            return new Node(entity, node);
        }

        public void clean() throws InterruptedException {
            synchronized (lock) {
                for (WeakEntity entity : weakEntities) {
                    if (entity == null || entity.isNull())
                        weakEntities.remove(entity);
                }
            }
        }

        public void clear(WeakEntity entity) {
            synchronized (lock) {
                weakEntities.remove(entity);
            }
        }

        public Set<WeakEntity> references() {
            synchronized (lock) {
                return weakEntities;
            }
        }

        @Override
        public Iterator<WeakEntity> iterator() {
            synchronized (lock) {
                return weakEntities.iterator();
            }
        }
    }
}