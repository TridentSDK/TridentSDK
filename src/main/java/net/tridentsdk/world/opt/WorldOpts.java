/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2017 The TridentSDK Team
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
package net.tridentsdk.world.opt;

import net.tridentsdk.base.Vector;

import javax.annotation.concurrent.ThreadSafe;

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
    /**
     * Checks the world in order to determine whether
     * players with client-side flight mods can fly.
     *
     * @return {@code true} if those players can fly,
     *         {@code false} to kick players who are flying
     */
    boolean isAllowFlight();

    /**
     * Changes whether flight is allowed using client-side
     * mods.
     *
     * @param allow {@code true} to allow flying,
     *              {@code false} to disallow
     */
    void setAllowFlight(boolean allow);

    /**
     * Checks the world in order to determine whether player
     * versus player damage is allowed.
     *
     * @return {@code true} if pvp is allowed, {@code false}
     *         if not
     */
    boolean isAllowPvp();

    /**
     * Changes whether or not PvP is allowed in the world.
     *
     * @param allow {@code true} to allow, {@code false} to
     *              disallow
     */
    void setAllowPvp(boolean allow);

    /**
     * Checks the world in order to determine if travel to
     * the nether via portals is allowed.
     *
     * @return {@code true} if nether travel is allowed,
     *         {@code false} if not
     */
    boolean isAllowPortals();

    /**
     * Sets whether or not nether portals are allowed in the
     * world.
     *
     * @param allow {@code true} to allow, {@code false} to
     *              disallow
     */
    void setAllowPortals(boolean allow);

    /**
     * Checks the world in order to determine if joining
     * players will use the game mode they left with or the
     * game mode of the current world.
     *
     * @return {@code true} to use the world game mode,
     *         {@code false} to use the player's last game
     *         mode
     */
    boolean isForceGameMode();

    /**
     * Sets whether the player will join with their last
     * game mode, or will be set to the world default.
     *
     * @param force {@code true} to force, {@code false} to
     *              use the last player game mode
     */
    void setForceGameMode(boolean force);

    /**
     * Obtains the game type to which players in the world
     * are set.
     *
     * @return the gamemode
     */
    GameMode getGameMode();

    /**
     * Sets the game type to which players in the world are
     * set.
     *
     * @param mode the new game mode which to set players
     */
    void setGameMode(GameMode mode);

    /**
     * Obtains the difficult to which the world will spawn
     * monsters and animals.
     *
     * @return the world difficulty
     */
    Difficulty getDifficulty();

    /**
     * Sets the world difficulty to the given difficulty.
     *
     * @param difficulty the new difficulty which to set the
     *                   world
     */
    void setDifficulty(Difficulty difficulty);

    /**
     * Checks the world in order to determine whether the
     * difficulty cannot be changed.
     *
     * @return {@code true} to indicate that the difficulty
     *         is locked, {@code false} if it is not
     */
    boolean isDifficultyLocked();

    /**
     * Sets whether the world difficulty is locked or not.
     *
     * @param locked {@code true} to lock, {@code false} to
     *               unlock
     */
    void setDifficultyLocked(boolean locked);

    /**
     * The radius of spawn protection, {@code 0} does not
     * mean no spawn protection.
     *
     * @return the spawn protection radius
     */
    int getSpawnProtectionRadius();

    /**
     * Sets the new spawn protection radius to the given
     * value.
     *
     * @param radius the new protection radius
     * @see #getSpawnProtectionRadius()
     */
    void setSpawnProtectionRadius(int radius);

    /**
     * Obtains the dimension of the world.
     *
     * @return the world's dimension
     */
    Dimension getDimension();

    /**
     * Obtains the XYZ coordinates of this world's spawn
     * position.
     *
     * @return the spawn position
     */
    Vector getSpawn();

    /**
     * Sets the spawn XYZ coordinates to the given vector.
     *
     * @param vector the new spawn position
     */
    void setSpawn(Vector vector);

    /**
     * Obtains the collection of modified game rules.
     *
     * @return the game rules applying to the world
     */
    GameRuleMap getGameRules();

    /*
     * Work(s) cited
     *
     * "Level Format." Minecraft Wiki. Curse Inc.,
     * 27 Apr. 2016. Web. 26 June 2016.
     * <http://minecraft.gamepedia.com/Level_format>
     */
}
