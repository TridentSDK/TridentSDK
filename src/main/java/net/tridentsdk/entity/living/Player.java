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
package net.tridentsdk.entity.living;

import net.tridentsdk.chat.ChatComponent;
import net.tridentsdk.chat.ChatType;
import net.tridentsdk.entity.Entity;

import javax.annotation.concurrent.ThreadSafe;
import java.util.UUID;

/**
 * This class represents a player entity that has joined
 * the server.
 *
 * @author TridentSDK
 * @since 0.3-alpha-DP
 */
@ThreadSafe
public interface Player extends Entity {
    /**
     * Obtains the name that this player had logged in.
     *
     * @return the player's name
     */
    String name();

    /**
     * Obtains the UUID of this player.
     *
     * <p>This UUID should be this player's identifier
     * wherever possible as the name is subject to change.
     * </p>
     *
     * @return the player's UUID
     */
    UUID uuid();

    /**
     * Sends this player a message.
     *
     * @param message The message.
     * @param type The type of message.
     */
    void sendMessage(ChatComponent message, ChatType type);

    /**
     * Sends this player a system message.
     *
     * Equivalent to {@code sendMessage(message, ChatType.SYSTEM)}.
     *
     * @param message The message.
     */
    default void sendMessage(ChatComponent message) {
        sendMessage(message, ChatType.SYSTEM);
    }

    /**
     * Disconnects this player from the server, displaying
     * the given message on screen as to why they may have
     * been disconnected.
     *
     * @param reason the reason for kicking the player
     */
    void kick(ChatComponent reason);
}
