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
 * Represents an event that can be cancelled and thus cause
 * the dispatcher to take a different course of action than
 * was initially planned.
 *
 * @author TridentSDK
 * @since 0.3-alpha-DP
 */
public interface Cancellable extends Event {
    /**
     * Obtains the cancel state of the event.
     *
     * @return {@code true} if the event has been cancelled
     */
    boolean isCancelled();

    /**
     * Sets the cancel state of the event.
     *
     * @param cancelled {@code true} to cancel
     */
    void setCancelled(boolean cancelled);
}