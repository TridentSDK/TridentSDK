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
package net.tridentsdk.api.event;

import com.esotericsoftware.reflectasm.MethodAccess;
import com.google.common.collect.HashMultimap;
import net.tridentsdk.api.Trident;
import net.tridentsdk.api.docs.InternalUseOnly;
import net.tridentsdk.api.factory.Factories;
import net.tridentsdk.api.threads.ConcurrentCache;
import net.tridentsdk.api.threads.TaskExecutor;

import javax.annotation.concurrent.ThreadSafe;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.PriorityBlockingQueue;

@ThreadSafe
public class EventManager {
    private static final Comparator<EventReflector> COMPARATOR = new EventReflector(null, 0, null, null, null);
    private static final Callable<EventManager> CREATE_MANAGER = new Callable<EventManager>() {
        @Override
        public EventManager call() throws Exception {
            return new EventManager();
        }
    };

    private final ConcurrentMap<Class<? extends Listenable>, PriorityBlockingQueue<EventReflector>>
            callers = Factories.collect().createMap();
    private final ConcurrentCache<Class<?>, MethodAccess> accessors = Factories.collect().createCache();

    private final ConcurrentCache<TaskExecutor, EventManager> handles = Factories.collect().createCache();

    @InternalUseOnly
    public EventManager() {
        if (!Trident.isTrident()) {
            throw new UnsupportedOperationException("EventManager must be initiated by TridentSDK!");
        }
    }

    /**
     * Normally not needed to be used. Plugin listeners are automatically registered when they are loaded.
     *
     * @param listener the listener instance to use to register
     */
    @InternalUseOnly
    public void registerListener(TaskExecutor executor, Listener listener) {
        handles.retrieve(executor, CREATE_MANAGER).doRegister(listener);
    }

    private void doRegister(Listener listener) {
        final Class<?> c = listener.getClass();
        HashMultimap<Class<? extends Listenable>, EventReflector> reflectors = reflectorsFrom(listener, c);

        for (Class<? extends Listenable> eventClass : reflectors.keySet()) {
            PriorityBlockingQueue<EventReflector> eventCallers = callers.get(eventClass);
            if (eventCallers == null)
                eventCallers = new PriorityBlockingQueue<>(128, COMPARATOR);
            eventCallers.addAll(reflectors.get(eventClass));
            callers.put(eventClass, eventCallers);
        }
    }

    private HashMultimap<Class<? extends Listenable>, EventReflector> reflectorsFrom(Listener listener, final Class<?> c) {
        MethodAccess access = accessors.retrieve(c, new Callable<MethodAccess>() {
            @Override
            public MethodAccess call() throws Exception {
                return MethodAccess.get(c);
            }
        });

        Method[] methods = c.getDeclaredMethods();

        HashMultimap<Class<? extends Listenable>, EventReflector> map = HashMultimap.create(11, 11);
        for (int i = 0, n = methods.length; i < n; i++) {
            Method method = methods[i];
            Class<?>[] parameterTypes = method.getParameterTypes();
            Class<?> type = parameterTypes[0];

            if (parameterTypes.length != 1 || !Listenable.class.isAssignableFrom(type)) {
                continue;
            }

            Class<? extends Listenable> eventClass = type.asSubclass(Listenable.class);
            Call handler = method.getAnnotation(Call.class);
            Importance importance = handler == null ? Importance.MEDIUM : handler.importance();

            EventReflector registeredListener = new EventReflector(
                    access, i, listener, eventClass, importance);
        }

        return map;
    }

    /**
     * Calls an event
     *
     * @param event the event to call
     */
    public void call(Listenable event) {
        for (EventManager handle : handles.values())
            handle.doCall(event);
    }

    private void doCall(Listenable event) {
        Queue<EventReflector> listeners = callers.get(event.getClass());
        if (listeners == null) return;
        for (final EventReflector listener : listeners)
            listener.reflect(event);
    }

    /**
     * Removes the listener from the caller queue, preventing it from being invoked
     *
     * @param listener the listener to unregister
     */
    public void unregister(Listener listener) {
        for (Map.Entry<Class<? extends Listenable>, PriorityBlockingQueue<EventReflector>> entry :
                this.callers.entrySet()) {
            for (Iterator<EventReflector> iterator = entry.getValue().iterator(); iterator.hasNext();) {
                EventReflector it = iterator.next();
                if (it.getInstance().equals(listener)) {
                    iterator.remove();
                    callers.put(entry.getKey(), entry.getValue());
                    break;
                }
            }
        }
    }
}
