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
import net.tridentsdk.event.player.PlayerMoveEvent;
import net.tridentsdk.plugin.Plugin;

import java.lang.reflect.Method;
import java.util.Comparator;

/**
 * A fast-reflection based event invoker which notifies event listeners
 *
 * @author The TridentSDK Team
 * @since 0.3-alpha-DP
 */
public class EventReflector implements Comparator<EventReflector> {
    private final MethodAccess handle;
    private final Plugin plugin;
    private final int index;
    private final Listener instance;
    private final Class<? extends Event> eventClass;
    private final Importance importance;

    EventReflector(MethodAccess handle, Plugin plugin, int index, Listener instance,
            Class<? extends Event> eventClass, Importance importance) {
        this.handle = handle;
        this.plugin = plugin;
        this.index = index;
        this.instance = instance;
        this.eventClass = eventClass;
        this.importance = importance;
    }

    public MethodAccess method() {
        return this.handle;
    }

    public Plugin plugin() {
        return plugin;
    }

    public int index() {
        return index;
    }

    public Class<? extends Event> eventClass() {
        return this.eventClass;
    }

    public Importance importance() {
        return this.importance;
    }

    public void reflect(Event event) {
        if (event instanceof PlayerMoveEvent) {
            try {
                Method method = this.instance.getClass().getMethod("move", PlayerMoveEvent.class);
                method.invoke(this.instance, event);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }

        this.handle.invoke(this.instance, this.index, event);
    }

    public Listener instance() {
        return this.instance;
    }

    @Override
    public int compare(EventReflector registeredListener, EventReflector t1) {
        return registeredListener.importance.ordinal() - t1.importance.ordinal();
    }
}
