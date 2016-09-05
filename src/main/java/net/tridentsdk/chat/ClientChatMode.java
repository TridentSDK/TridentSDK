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

import lombok.Getter;

/**
 * Chat modes which a player may set by navigating to the
 * "Multiplayer settings" and toggling the chat modes to
 * the states listed below.
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
public enum ClientChatMode {
    /**
     * Allows both chat messages and commands to be
     * displayed on the chat bar.
     */
    CHAT_AND_COMMANDS(0),
    /**
     * Allows only command feedback to be displayed on the
     * chat bar.
     */
    COMMANDS_ONLY(1),
    /**
     * Do not use the chat bar.
     */
    NONE(2);

    @Getter
    private final int data;

    /**
     * Creates a new chat mode with the given data value.
     *
     * @param data the identifyer used in the protocol to
     * signify a particular chat mode
     */
    ClientChatMode(int data) {
        this.data = data;
    }

    /**
     * Obtains the chat mode of the client given the data
     * that was sent using the client settings packet.
     *
     * @param data the chat mode identifing number
     * @return the chat mode
     */
    public static ClientChatMode of(int data) {
        for (ClientChatMode chatMode : values()) {
            if (chatMode.getData() == data) {
                return chatMode;
            }
        }

        throw new IllegalArgumentException("no client chat mode with id=" + data);
    }
}