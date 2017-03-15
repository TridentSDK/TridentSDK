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

/**
 * The order in which events that are dispatched by the
 * server's {@link EventController} are transmitted to their
 * respective listeners.
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
public enum DispatchOrder {
    /**
     * A listener marked with this order is attempted to be
     * invoked as soon as possible.
     */
    FIRST,
    /**
     * A listener marked with this order will be invoked
     * sometime after the first listeners and the middle
     * listeners.
     */
    EARLY,
    /**
     * The default order.
     *
     * <p>A listener marked with this order will be invoked
     * arbitrarily somewhere after the early listeners
     * and the late listeners.</p>
     */
    MIDDLE,
    /**
     * A listener marked with this order will be invoked
     * sometime between the middle and the last listener.
     */
    LATE,
    /**
     * Listeners marked with this method attempt to be
     * invoked after all listeners.
     *
     * <p>Because the last listener is important for
     * building API and managerial plugins, two listeners
     * registered on the same event that are both marked
     * with this order will cause a warning to be printed
     * to the console.</p>
     */
    LAST
}