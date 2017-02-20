/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2016 The TridentSDK Team
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

import javax.annotation.concurrent.ThreadSafe;
import java.util.function.Consumer;

/**
 * An event controller manages and handles dispatched
 * events and their corresponding listeners
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
@ThreadSafe
public interface EventController {
    /**
     * Registers the given listener object to receive
     * events dispatched by the controller.
     *
     * @param listener the listener to register
     */
    void register(Object listener);

    /**
     * Removes the given listener from being handling
     * events dispatched by the event controller.
     *
     * @param listener the listener to remove
     */
    void unregister(Object listener);

    /**
     * Dispatches the event to the event listener/handlers
     * that are registered under the event controller.
     *
     * @param event the event to dispatch
     * @param callback the callback to execute when the
     * controller finishes processing all listeners
     */
    <T extends Event> void dispatch(T event, Consumer<T> callback);
}