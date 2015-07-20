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

import net.tridentsdk.entity.living.Player;

import java.util.UUID;

/**
 * The player registry
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 */
public interface Players extends Registry<Player> {
    /**
     * Obtains a player from the player's UUID
     *
     * @param uuid the player's uuid
     * @return the player with that UUID, or null if the player is not online
     */
    Player fromUuid(UUID uuid);

    /**
     * Obtains a potentially offline player
     *
     * @param uuid the uuid to find
     * @return a non-null player that could be offline
     */
    Player offline(UUID uuid);
}
