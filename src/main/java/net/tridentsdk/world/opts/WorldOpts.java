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
package net.tridentsdk.world.opts;

import net.tridentsdk.base.Vector;

import javax.annotation.concurrent.ThreadSafe;
import java.util.Map;

/**
 * This class represents the data contained in the level.dat
 * file which provides options regarding how the world will
 * be generated.
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 */
@ThreadSafe
public interface WorldOpts {
    // Level format
    // http://minecraft.gamepedia.com/Level_format

    // dimension data
    // version - x
    // initialized - x
    // LevelName - (world.name())
    // generatorName,generatorVersion,generatorOptions,seed,
    // features (world.genOpts())

    // LastPlayed - x
    // SizeOnDisk - x

    // hardcore - x (this.difficulty())

    // Time - x

    // borders - (world.border())
    // weather - (world.weather())
    // Player - MISC

    // Version - x

    // boolean allowCheats();
    // TODO single-player only?

    /**
     * Checks the world in order to determine whether
     * players with client-side flight mods can fly.
     *
     * @return {@code true} if those players can fly,
     *         {@code false} to kick players who are flying
     */
    boolean allowFlight();

    /**
     * Checks the world in order to determine whether player
     * versus player damage is allowed.
     *
     * @return {@code true} if pvp is allowed, {@code false}
     *         if not
     */
    boolean allowPvp();

    /**
     * Checks the world in order to determine if travel to
     * the nether via portals is allowed.
     *
     * @return {@code true} if nether travel is allowed,
     *         {@code false} if not
     */
    boolean allowNether();

    /**
     * Checks the world in order to determine if joining
     * players will use the game mode they left with or the
     * game mode of the current world.
     *
     * @return {@code true} to use the world game mode,
     *         {@code false} to use the player's last game
     *         mode
     */
    boolean forceGameMode();

    /**
     * Obtains the game type to which players in the world
     * are set.
     *
     * @return the gamemode
     */
    GameMode gameMode();

    /**
     * Obtains the difficult to which the world will spawn
     * monsters and animals.
     *
     * @return the world difficulty
     */
    Difficulty difficulty();

    /**
     * Checks the world in order to determine whether the
     * difficulty cannot be changed.
     *
     * @return {@code true} to indicate that the difficulty
     *         is locked, {@code false} if it is not
     */
    boolean difficultyLocked();

    /**
     * The radius of spawn protection, {@code 0} does not
     * mean no spawn protection
     *
     * @return the spawn protection radius
     */
    int spawnProtectionRadius();

    // int viewDistance(); ???
    // URL resourcePack(); ???
    // int maxWorldSize();
    // int maxHeight(
    // boolean spawnNpcs(); boolean spawnMonsters(); boolean spawnAnimals();

    // LevelType levelType();
    // Dimension dimension();

    // long time();
    // TODO return non resetting time or 24000 time?

    /**
     * Obtains the XYZ coordinates of this world's spawn
     * position.
     *
     * @return the spawn position
     */
    Vector spawn();

    /**
     * Obtains the collection of modified game rules
     *
     * @return the game rules applying to the world
     */
    Map<GameRule, GameRuleValue> gameRules();

    /*
     * Work(s) cited
     *
     * "Level Format." Minecraft Wiki. Curse Inc.,
     * 27 Apr. 2016. Web. 26 June 2016.
     * <http://minecraft.gamepedia.com/Level_format>
     */
}