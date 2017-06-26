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
package net.tridentsdk.meta.block;

import net.tridentsdk.entity.living.Player;

/**
 * Represents an updatable block that contains special data
 *
 * @author The TridentSDK TeaM
 * @since 0.4-alpha
 */
public interface Tile {
    /**
     * Causes the tile to be updated to the player provided
     *
     * @param player the player to update
     */
    void update(Player player);

    /**
     * Performs the tick logic for the tile
     */
    default void tick() {
    }
}
