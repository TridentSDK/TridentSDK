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
package net.tridentsdk.chat;

/**
 * Represents the different types of messages sent to a client.
 *
 * @author Nick Robson
 * @since 0.5-alpha
 */
public enum ChatType {

    /**
     * A chat message (such as notifications or player chat)
     */
    CHAT,

    /**
     * A system message (such as command output)
     */
    SYSTEM,

    /**
     * A message above the hotbar
     */
    ABOVE_HOTBAR

}
