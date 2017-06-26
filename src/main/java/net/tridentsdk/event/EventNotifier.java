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

import net.tridentsdk.plugin.Plugin;

/**
 * Represents a tool which is used to notify event listeners
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 */
public interface EventNotifier {
    /**
     * Obtains the plugin that contains the event listener
     *
     * @return the plugin
     */
    Plugin plugin();

    /**
     * Obtains the instance of the listener which was registered
     *
     * @return the listener instance
     */
    Listener listener();

    /**
     * Obtains the class of the event type that this notifier will be calling listeners for
     *
     * @return the event type
     */
    Class<? extends Event> eventType();

    /**
     * Obtains the importance of the event listener
     *
     * @return the importance
     */
    Importance importance();

    /**
     * Notifies the listener
     *
     * @param event the event to notify the listener
     */
    void handle(Event event);
}
