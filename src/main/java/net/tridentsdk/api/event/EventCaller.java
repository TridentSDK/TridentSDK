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

import net.tridentsdk.api.reflect.FastMethod;

/**
 * The invocation handler for event listener methods
 *
 * @author The TridentSDK Team
 */
public class EventCaller implements Comparable<EventCaller> {
    private final FastMethod method;
    private final Class<? extends Listenable> eventClass;
    private final Importance importance;

    EventCaller(FastMethod method, Class<? extends Listenable> eventClass, Importance importance) {
        this.method = method;
        this.eventClass = eventClass;
        this.importance = importance;
    }

    /**
     * Gets the method wrapper that invokes the {@code public} listener
     *
     * @return the {@link net.tridentsdk.api.reflect.FastMethod} that invokes the event
     */
    public FastMethod getMethod() {
        return this.method;
    }

    /**
     * The event that the method listens for
     *
     * @return the {@code class} being listened to
     */
    public Class<? extends Listenable> getEventClass() {
        return this.eventClass;
    }

    /**
     * The event priority order based on the listener nnotation
     *
     * @return the importance of the event
     */
    public Importance getImportance() {
        return this.importance;
    }

    /**
     * Invokes the method with the ASM reflection wrapper
     *
     * @param event the instance of the event class to be using for the event data
     */
    public void call(Listenable event) {
        this.method.invoke(event);
    }

    @Override
    public int compareTo(EventCaller eventCaller) {
        return importance.compareTo(eventCaller.getImportance());
    }
}
