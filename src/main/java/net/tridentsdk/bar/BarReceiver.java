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
package net.tridentsdk.bar;

import net.tridentsdk.meta.BarType;

/**
 * A player entity or something that can recieve bars.
 *
 * @author The TridentSDK Team
 */
public interface BarReceiver {
    /**
     * Sends a bar accordingly to the type and the message.
     *
     * @param barType the type of bar
     * @param message the message on the bar
     */
    void sendBar(BarType barType, String message);
}
