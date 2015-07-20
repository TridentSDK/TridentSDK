/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2014 The TridentSDK Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.tridentsdk.event;

import com.esotericsoftware.reflectasm.MethodAccess;
import com.google.common.collect.ForwardingCollection;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import net.tridentsdk.Trident;
import net.tridentsdk.docs.InternalUseOnly;
import net.tridentsdk.plugin.Plugin;
import net.tridentsdk.plugin.annotation.IgnoreRegistration;
import net.tridentsdk.registry.Registered;
import net.tridentsdk.registry.Registry;
import net.tridentsdk.util.TridentLogger;

import javax.annotation.concurrent.ThreadSafe;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.function.Function;

/**
 * The server's event handler, should only be created once, and only once by the server only
 *
 * <p>To access this handler, use this code:
 * <pre><code>
 *     Events handler = Registered.events();
 * </code></pre></p>
 *
 * @author The TridentSDK Team
 * @since 0.3-alpha-DP
 */
@ThreadSafe
public class Events extends ForwardingCollection<EventReflector> implements Registry<EventReflector> {
    private static final Comparator<EventReflector> COMPARATOR = new EventReflector(null, null, 0, null, null, null);
    public static final Function<Class<?>, Queue<EventReflector>> CREATE_QUEUE = (k) ->
            new PriorityBlockingQueue<>(128, COMPARATOR);

    private final ConcurrentMap<Class<? extends Event>, Queue<EventReflector>> callers = new ConcurrentHashMap<>();
    private final ConcurrentMap<Class<?>, MethodAccess> accessors = new ConcurrentHashMap<>();

    private Events() {
        if (!Trident.isTrident()) {
            TridentLogger.error(new IllegalAccessException("EventManager must be initiated by TridentSDK!"));
        }
    }

    /**
     * Creates a new event handler, should only be used internally
     *
     * <p>To access this handler, use this code:
     * <pre><code>
     *     Events handler = Registered.events();
     * </code></pre></p>
     *
     * @return the new event handler
     */
    @InternalUseOnly
    public static Events create() {
        return new Events();
    }

    /**
     * Normally not needed to be used. Plugin listeners are automatically registered when they are loaded.
     *
     * @param listener the listener instance to use to register
     */
    @InternalUseOnly
    public void registerListener(Plugin plugin, Listener listener) {
        final Class<?> c = listener.getClass();
        HashMultimap<Class<? extends Event>, EventReflector> reflectors = reflectorsFrom(plugin, listener, c);

        for (Class<? extends Event> eventClass : reflectors.keys()) {
            Queue<EventReflector> eventCallers = callers.computeIfAbsent(eventClass, CREATE_QUEUE);
            eventCallers.addAll(reflectors.get(eventClass));
        }
    }

    private HashMultimap<Class<? extends Event>, EventReflector> reflectorsFrom(Plugin plugin, Listener listener,
            final Class<?> c) {
        MethodAccess access = accessors.computeIfAbsent(c, (k) -> MethodAccess.get(c));

        Method[] methods = c.getDeclaredMethods();

        HashMultimap<Class<? extends Event>, EventReflector> map = HashMultimap.create(11, 11);
        for (int i = 0, n = methods.length; i < n; i++) {
            Method method = methods[i];
            if (method.isAnnotationPresent(IgnoreRegistration.class)) {
                continue;
            }
            Class<?>[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length != 1)
                continue;

            Class<?> type = parameterTypes[0];

            if (!Event.class.isAssignableFrom(type))
                continue;

            Class<? extends Event> eventClass = type.asSubclass(Event.class);
            ListenerData handler = method.getAnnotation(ListenerData.class);
            Importance importance = handler == null ? Importance.MEDIUM : handler.importance();

            EventReflector registeredListener = new EventReflector(access, plugin, access.getIndex(method.getName()),
                    listener, eventClass, importance);
            map.put(eventClass, registeredListener);
        }

        return map;
    }

    /**
     * Calls an event
     *
     * @param event the event to call
     */
    public void fire(final Event event) {
        final Queue<EventReflector> listeners = callers.get(event.getClass());
        if (listeners == null) return;

        final CountDownLatch latch = new CountDownLatch(1);

        Registered.plugins().executor().execute(() -> {
            for (EventReflector listener : listeners) {
                listener.reflect(event);
            }

            latch.countDown();
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes the listener from the caller queue, preventing it from being invoked
     *
     * @param cls the listener class to unregister
     */
    public void unregister(Class<? extends Listener> cls) {
        for (Map.Entry<Class<? extends Event>, Queue<EventReflector>> entry : this.callers.entrySet()) {
            for (Iterator<EventReflector> iterator = entry.getValue().iterator(); iterator.hasNext(); ) {
                EventReflector it = iterator.next();
                if (it.instance().getClass().equals(cls)) {
                    iterator.remove();
                    break;
                }
            }
        }
    }

    @Override
    protected Collection<EventReflector> delegate() {
        List<EventReflector> reflectors = Lists.newArrayList();
        callers.values().forEach(reflectors::addAll);

        return reflectors;
    }
}