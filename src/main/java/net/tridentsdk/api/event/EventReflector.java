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

import java.util.Comparator;

public class EventReflector implements Comparator<EventReflector> {
    private final MethodAccess handle;
    private final int index;
    private final Object instance;
    private final Class<? extends Event> eventClass;
    private final Importance importance;

    EventReflector(MethodAccess handle, int index, Object instance,
                   Class<? extends Event> eventClass, Importance importance) {
        this.handle = handle;
        this.index = index;
        this.instance = instance;
        this.eventClass = eventClass;
        this.importance = importance;
    }

    public MethodAccess getMethod() {
        return this.handle;
    }

    public int getIndex() {
        return index;
    }

    public Class<? extends Event> getEventClass() {
        return this.eventClass;
    }

    public Importance getImportance() {
        return this.importance;
    }

    public void reflect(Event event) {
        this.handle.invoke(this.instance, this.index, event);
    }

    public Object getInstance() {
        return this.instance;
    }

    @Override
    public int compare(EventReflector registeredListener, EventReflector t1) {
        return registeredListener.importance.ordinal() - t1.importance.ordinal();
    }
}
