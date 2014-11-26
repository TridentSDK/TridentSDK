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

import com.google.common.collect.Maps;
import net.tridentsdk.api.Trident;
import net.tridentsdk.api.docs.InternalUseOnly;
import net.tridentsdk.api.factory.Factories;

import javax.annotation.concurrent.NotThreadSafe;
import java.lang.reflect.Method;
import java.util.*;

@NotThreadSafe
public class EventManager {
    private final Map<Class<? extends Listenable>, PriorityQueue<EventReflector>> callers = Maps.newHashMap();

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
    public void registerListener(Listener listener) {
        for (Method method : listener.getClass().getDeclaredMethods()) {
            Class<?>[] parameterTypes = method.getParameterTypes();

            if (parameterTypes.length != 1 || !Listenable.class.isAssignableFrom(parameterTypes[0])) {
                continue;
            }

            Class<? extends Listenable> eventClass = parameterTypes[0].asSubclass(Listenable.class);
            Call handler = method.getAnnotation(Call.class);
            Importance importance = handler == null ? Importance.MEDIUM : handler.importance();

            EventReflector registeredListener = new EventReflector(
                    Factories.reflect().getMethod(listener, method.getName()),
                    eventClass,
                    importance);
            PriorityQueue<EventReflector> eventCallers = callers.get(eventClass);
            if (eventCallers == null)
                eventCallers = new PriorityQueue<>(11, registeredListener);
            eventCallers.add(registeredListener);
            callers.put(eventClass, eventCallers);
        }
    }

    /**
     * Calls an event
     *
     * @param event the event to call
     */
    public void call(Listenable event) {
        Queue<EventReflector> listeners = callers.get(event.getClass().asSubclass(Listenable.class));
        if (listeners == null) return;
        for (EventReflector listener : listeners)
            listener.reflect(event);
    }

    /**
     * Removes the listener from the caller queue, preventing it from being invoked
     *
     * @param listener the listener to unregister
     */
    public void unregister(Listener listener) {
        for (Map.Entry<Class<? extends Listenable>, PriorityQueue<EventReflector>> entry : this.callers.entrySet()) {
            for (Iterator<EventReflector> iterator = entry.getValue().iterator(); iterator.hasNext();) {
                EventReflector it = iterator.next();
                if (it.getMethod().getInstance().equals(listener)) {
                    iterator.remove();
                    callers.put(entry.getKey(), entry.getValue());
                    break;
                }
            }
        }
    }
}
