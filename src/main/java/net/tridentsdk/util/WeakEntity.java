package net.tridentsdk.util;

import net.tridentsdk.entity.Entity;

import javax.annotation.concurrent.ThreadSafe;

// TODO reference management

/**
 * A reference to an entity
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
 * optional which stores a {@link java.lang.ref.WeakReference} of the specified property. While this class does include
 * the usage of a WeakReference, other methods to safeguard the reference is also implemented, such as removal
 * listeners.</p>
 *
 * <p></p>
 *
 * <p>Here are examples on the usage of this class:
 * <pre><code>
 *     private final Map&lt;WeakEntity&lt;Player&gt;, Integer&gt; score = Maps.newHashMap();
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
 *
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
 *     findClosest(player, 69).or(EntityBuilder.create().build(TridentBat.class)).setVelocity(new Vector(0, 10, 0));
 *
 *     // Using it to find bugs
 *     // Oh, leave it here for a while until I implement a different method, then I'll come back to it
 *     WeakEntity&lt;Player&gt; weakEntity = WeakEntity.of(null);
 *     ...
 *     // I was smart and used obtain() instead of entity()
 *     weakEntity.obtain().hide();
 *     ...
 *     ===== BEGIN ERROR =====
 *     ...
 *     // Go back to the line you found in the stacktrace
 *     weakEntity.obtain() // Ohhhhhhh! I forgot to change it to a method that I made!
 *     ...
 *     // Final code:
 *     WeakEntity&lt;Player&gt; weakEntity = WeakEntity.of(getPlayer());
 *     // Now we are good!
 * </code></pre></p>
 *
 * @author The TridentSDK Team
 */
@ThreadSafe
public class WeakEntity<T extends Entity> {
    private static final WeakEntity<?> NULL_ENTITY = WeakEntity.of(null);
    private volatile T referencedEntity;

    private WeakEntity(T referencedEntity) {
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
        return new WeakEntity<>(referencedEntity);
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
        return new WeakEntity<>(referencedEntity);
    }

    /**
     * Observes the reference to see if the entity is {@code null} or not accessible via the server anymore
     *
     * @return {@code true} to indicate the entity is removed and cannot be further referenced, in which case the
     *         instance of this object should also become {@code null}
     */
    public boolean isNull() {
        return referencedEntity == null;
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
        T ref = this.referencedEntity;
        return ref == null ? fallback : ref;
    }

    /**
     * Obtains the instance of the referenced entity, or {@code null} if it has been dereferenced
     *
     * @return the entity which this reference points to, or {@code null} if the entity no longer exists on the server
     */
    public T entity() {
        return this.referencedEntity;
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
        T ref = this.referencedEntity;
        if (ref == null)
            throw new IllegalStateException("Obtained entity is null");
        return ref;
    }
}