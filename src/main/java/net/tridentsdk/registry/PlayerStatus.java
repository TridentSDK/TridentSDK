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
package net.tridentsdk.registry;

import javax.annotation.concurrent.ThreadSafe;
import java.net.InetSocketAddress;
import java.util.UUID;

/**
 * Represents players with a special status, such as banned, ip-banned, operator, or whitelisted
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 */
@ThreadSafe
public interface PlayerStatus {
    /**
     * Checks the ban status of the player with the given UUID
     *
     * @param uuid the uuid to check
     * @return {@code true} if the player is banned
     */
    boolean isBanned(UUID uuid);

    /**
     * Checks if the IP has been banned from the server
     *
     * @param address the IP to check
     * @return {@code true} if the IP has been banned
     */
    boolean isIpBanned(InetSocketAddress address);

    /**
     * Checks if the player owning the UUID has been given operator status
     *
     * @param uuid the uuid to check
     * @return {@code true} if the UUID is opped
     */
    boolean isOpped(UUID uuid);

    /**
     * Checks if the player owning the UUID is whitelisted
     *
     * @param uuid the uuid to check
     * @return {@code true} if the player is whitelisted
     */
    boolean isWhitelisted(UUID uuid);

    /**
     * Bans the player given the UUID
     *
     * @param uuid the uuid of the player to ban
     */
    void ban(UUID uuid);

    /**
     * IP bans the given address
     *
     * @param address the IP to ban
     */
    void ipBan(InetSocketAddress address);

    /**
     * Ops the given UUID
     *
     * @param uuid the UUID to give op to
     */
    void op(UUID uuid);

    /**
     * Whitelists the UUID provided
     *
     * @param uuid the to give operator status
     */
    void whitelist(UUID uuid);
}
