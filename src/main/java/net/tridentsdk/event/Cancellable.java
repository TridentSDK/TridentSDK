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

/**
 * An event that implements this supports the ability to cancel (cancel) the event actions and calling behavior
 *
 * @author The TridentSDK Team
 */
public interface Cancellable {
    /**
     * @return return true if event is set to cancelled
     */
    boolean isCancelled();

    /**
     * @param cancel set the cancellation state of the event
     */
    void setCancelled(boolean cancel);
}
