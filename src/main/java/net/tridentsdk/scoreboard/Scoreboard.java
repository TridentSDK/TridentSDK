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
package net.tridentsdk.scoreboard;

import net.tridentsdk.entity.living.Player;

/**
 * TODO Write Description
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 */
public interface Scoreboard {

    /**
     * Add a module to the scoreboard with a specific priority
     *
     * @param module The module to add
     * @param priority The priority to set
     */
    void addModule(ScoreboardModule module, int priority);

    /**
     * Default method for addModule() with a default priority
     *
     * @param module The module to add
     */
    default void addModule(ScoreboardModule module) {
        addModule(module, 0);
    }

    /**
     * Show the scoreboard to a player
     *
     * @param player The player to show the scoreboard to
     */
    void showToPlayer(Player player);

}
