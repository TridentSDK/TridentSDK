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

import net.tridentsdk.api.Trident;
import net.tridentsdk.api.reflect.FastClass;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.EnumMap;

/**
 * EventManager for Trident
 */
public class EventManager {

    private final EnumMap<Importance, ArrayList<RegisteredListener>> importanceMap = new EnumMap<>(Importance.class);

    /**
     * Default constructor for EventManager
     */
    public EventManager() {
        // Check for invalid session
        if (!Trident.isTrident()) {
            throw new UnsupportedOperationException("EventManager must be initiated by TridentSDK!");
        }

        // Safe O(k)
        for (Importance importance : Importance.values()) {
            this.importanceMap.put(importance, new ArrayList<RegisteredListener>());
        }
    }

    /**
     * Register a listener with Trident
     * 
     * @param listener the listener to register
     */
    public void registerListener(Listener listener) {
        // Create new FastClass for the listener
        FastClass fastClass = FastClass.get(listener.getClass());

        // Iterate through all methods in the listener
        for (Method method : listener.getClass().getDeclaredMethods()) {
            // Grab all method parameters for the current method
            Class<?>[] parameterTypes = method.getParameterTypes();

            // End current method processing if the method has only one parameter
            // or does not have an Event as its first parameter
            if (parameterTypes.length != 1 || !Event.class.isAssignableFrom(parameterTypes[0])) {
                continue;
            }

            // Create RegisteredListener filler
            Class<? extends Event> eventClass = parameterTypes[0].asSubclass(Event.class);
            EventHandler handler = method.getAnnotation(EventHandler.class);
            Importance importance = (handler == null) ? Importance.MEDIUM : handler.importance();

            // Put new listener to the importance map
            this.importanceMap.get(importance)
            .add(new RegisteredListener(fastClass.getMethod(listener, method.getName()),
                    eventClass, importance));
        }
    }

    /**
     * Executes an event at all appropriate registered listeners 
     * @param event the event
     */
    public void call(Event event) {
        // Iterate over possible listeners in lowest priority order
        // Optimizes listener response
        for (ArrayList<RegisteredListener> rList : importanceMap.values()) {
            for (RegisteredListener listener : rList) {
                if (event.getClass().isAssignableFrom(listener.getEventClass())) {
                    listener.execute(event);
                }
            }
        }
    }
}
