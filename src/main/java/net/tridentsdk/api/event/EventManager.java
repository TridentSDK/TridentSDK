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
import java.util.Map;

public class EventManager {
    private final EnumMap<Importance, ArrayList<RegisteredListener>> importanceMap =
            new EnumMap<>(Importance.class);
    private RegisteredListener[] listeners;

    public EventManager() {
        if (!Trident.isTrident()) {
            throw new UnsupportedOperationException("EventManager must be initiated by TridentSDK!");
        }

        this.listeners = new RegisteredListener[] { };

        for (Importance importance : Importance.values()) {
            this.importanceMap.put(importance, new ArrayList<RegisteredListener>());
        }
    }

    public void registerListener(Listener listener) {
        FastClass fastClass = FastClass.get(listener.getClass());

        for (Method method : listener.getClass().getDeclaredMethods()) {
            Class<?>[] parameterTypes = method.getParameterTypes();

            if (parameterTypes.length != 1 || !Event.class.isAssignableFrom(parameterTypes[0])) {
                continue;
            }

            Class<? extends Event> eventClass = parameterTypes[0].asSubclass(Event.class);
            EventHandler handler = method.getAnnotation(EventHandler.class);
            Importance importance = handler == null ? Importance.MEDIUM : handler.importance();

            this.importanceMap.get(importance)
                    .add(new RegisteredListener(fastClass.getMethod(listener, method.getName()),
                            eventClass, importance));
        }

        this.updateListeners();
    }

    public void updateListeners() {
        ArrayList<RegisteredListener> l = new ArrayList<>();

        for (Map.Entry<Importance, ArrayList<RegisteredListener>> entry : this.importanceMap.entrySet()) {
            l.addAll(entry.getValue());
        }

        this.listeners = l.toArray(new RegisteredListener[l.size()]);
    }

    public void call(Event event) {
        for (RegisteredListener listener : this.listeners) {
            if(event.getClass().equals(listener.getEventClass())) {
                listener.execute(event);
            }
        }
    }
}
