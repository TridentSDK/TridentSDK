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
package net.tridentsdk.world.settings;

import javax.annotation.concurrent.ThreadSafe;
import java.util.Set;

/**
 * Represents the settings of the world, such as the level type, gamemode, etc...
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 */
@ThreadSafe
public interface WorldSettings {
    /**
     * Gets the dimension of a world
     *
     * @return The dimension of a world
     */
    Dimension dimension();

    /**
     * Gets the difficulty set in a world
     *
     * @return The difficulty set in a world
     */
    Difficulty difficulty();

    /**
     * Sets the world difficulty
     *
     * @param difficulty the difficulty
     */
    void setDifficulty(Difficulty difficulty);

    /**
     * Gets the default gamemode in a given chunk
     *
     * @return The default gamemode in a given chunk
     */
    GameMode defaultGameMode();

    /**
     * Sets the world game mode
     *
     * @param gameMode the world gamemode
     */
    void setGameMode(GameMode gameMode);

    /**
     * Gets the type of a world
     *
     * @return The type of a world
     */
    LevelType levelType();

    /**
     * Gets the set boolean for the given gamerule
     *
     * @return The set boolean for the given gamerule
     */
    boolean isRule(String rule);

    /**
     * Obtains a list of the game rules applied to this world
     *
     * @return the world's game rules
     */
    Set<String> gameRules();

    /**
     * Checks if structures are generated in a world (Stronghold, villages, dungeons)
     *
     * @return True if structures are generated in a world (Stronghold, villages, dungeons)
     */
    boolean generateStructures();
}
