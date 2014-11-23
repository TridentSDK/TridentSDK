/*
 *     TridentSDK - A Minecraft Server API
 *     Copyright (C) 2014, The TridentSDK Team
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.tridentsdk.api.event;

import com.google.common.collect.Maps;
import net.tridentsdk.api.Trident;
import net.tridentsdk.api.docs.InternalUseOnly;
import net.tridentsdk.api.factory.Factories;

import javax.annotation.concurrent.NotThreadSafe;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

@NotThreadSafe
public class EventManager {
    private final Map<Class<? extends Event>, PriorityQueue<RegisteredListener>> callers = Maps.newHashMap();

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

            if (parameterTypes.length != 1 || !Event.class.isAssignableFrom(parameterTypes[0])) {
                continue;
            }

            Class<? extends Event> eventClass = parameterTypes[0].asSubclass(Event.class);
            EventHandler handler = method.getAnnotation(EventHandler.class);
            Importance importance = handler == null ? Importance.MEDIUM : handler.importance();

<<<<<<< Updated upstream
            List<RegisteredListener> eventCallers = this.callers.get(eventClass);
            if (eventCallers == null) eventCallers = new ArrayList<>();
            
            eventCallers.add(new RegisteredListener(fastClass.getMethod(method.getName()), eventClass, importance));
            Collections.sort(eventCallers);
=======
            RegisteredListener registeredListener = new RegisteredListener(
                    Factories.reflect().getMethod(listener, method.getName()),
                    eventClass,
                    importance);
            PriorityQueue<RegisteredListener> eventCallers = callers.get(eventClass);
            if (eventCallers == null)
                eventCallers = new PriorityQueue<>(11, registeredListener);
            eventCallers.add(registeredListener);
>>>>>>> Stashed changes
            callers.put(eventClass, eventCallers);
        }
    }

    /**
     * Calls an event
     *
     * @param event the event to call
     */
    public void call(Event event) {
        Queue<RegisteredListener> listeners = callers.get(event.getClass().asSubclass(Event.class));
        if (listeners == null) return;
        for (RegisteredListener listener : listeners)
            listener.execute(event);
    }

    /**
     * Removes the listener from the caller queue, preventing it from being invoked
     *
     * @param listener the listener to unregister
     */
    public void unregister(Listener listener) {
        for (Map.Entry<Class<? extends Event>, PriorityQueue<RegisteredListener>> entry : this.callers.entrySet()) {
            for (Iterator<RegisteredListener> iterator = entry.getValue().iterator(); iterator.hasNext();) {
                RegisteredListener it = iterator.next();
                if (it.getMethod().getInstance().equals(listener)) {
                    iterator.remove();
                    callers.put(entry.getKey(), entry.getValue());
                    break;
                }
            }
        }
    }
}
