/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2017 The TridentSDK Team
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

import net.tridentsdk.doc.Policy;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.function.Consumer;

/**
 * An event controller manages and handles dispatched
 * events and their corresponding listeners
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
@NotThreadSafe
public interface EventController {
    /**
     * Registers the given listener object to receive
     * events dispatched by the controller.
     *
     * <p>This method should be default accept duplicates.
     * Events being fired twice should be checked to ensure
     * that they were not already registered.</p>
     *
     * @param listener the listener to register
     */
    void register(Listener listener);

    /**
     * Removes the given listener from being handling
     * events dispatched by the event controller.
     *
     * @param listener the listener to remove
     */
    void unregister(Class<? extends Listener> listener);

    /**
     * Dispatches the event to the event listener/handlers
     * that are registered under the event controller.
     *
     * <p>This method must always be called on the plugin
     * thread.</p>
     *
     * <p>This method passes events to their respective
     * listeners without responding to them. This means that
     * when this method complete, not all event handlers
     * have been guaranteed to run yet. There still may be
     * handlers that run after this method is called. If a
     * response is needed from this method, use {@link
     * #dispatch(Event, Consumer)}.</p>
     *
     * @param event the event to dispatch
     * @param <T> the event type
     */
    @Policy("call on plugin thread")
    <T extends Event> void dispatch(T event);

    /**
     * Dispatches the event to the event listener/handlers
     * that are registered under the event controller.
     *
     * <p>This method dispatches events to their listeners
     * and processes the event when done in the plugin
     * thread.</p>
     *
     * <p>Only the callback is guaranteed to run once all
     * event handlers are finished. Checking upon the event
     * state prematurely (i.e. as soon as the method
     * completes) may result in unexpected behavior. Always
     * check for the event state in the callback.</p>
     *
     * @param event the event to dispatch
     * @param callback the callback to execute when the
     * controller finishes processing all listeners
     * @param <T> the event type
     */
    <T extends Event> void dispatch(T event, Consumer<T> callback);
}