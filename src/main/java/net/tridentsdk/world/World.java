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

package net.tridentsdk.world;

import net.tridentsdk.Position;
import net.tridentsdk.Difficulty;
import net.tridentsdk.GameMode;
import net.tridentsdk.base.Block;
import net.tridentsdk.entity.Entity;
import net.tridentsdk.entity.types.EntityType;

import java.util.Set;

public interface World extends Cloneable {
    /**
     * Gets the name of the world
     *
     * @return the name of the world
     */
    String getName();

    /**
     * Gets the chunk on the given location, and generates the chunk if it does not exist.
     *
     * @return The chunk on the given location
     */
    Chunk getChunkAt(ChunkLocation loc, boolean generateIfNotFound);

    /**
     * Gets the chunk on the given x and z , and generates the chunk if it does not exist
     *
     * @return The chunk on the given location
     */
    Chunk getChunkAt(int x, int z, boolean generateIfNotFound);

    /**
     * Generates the chunk on the given location
     *
     * @return The generated chunk
     */
    Chunk generateChunk(int x, int z);

    /**
     * Generates the chunk on the given location
     *
     * @return The generated chunk
     */
    Chunk generateChunk(ChunkLocation location);

    /**
     * Gets the block on the given location
     *
     * @return The block on the given location
     */
    Block getBlockAt(Position location);

    /**
     * Gets the dimension of a world
     *
     * @return The dimension of a world
     */
    Dimension getDimension();

    /**
     * Gets the difficulty set in a world
     *
     * @return The difficulty set in a world
     */
    Difficulty getDifficulty();

    /**
     * Gets the default gamemode in a given chunk
     *
     * @return The default gamemode in a given chunk
     */
    GameMode getDefaultGamemode();

    /**
     * Obtains the loading handler which created this object, passed in from the constructor
     *
     * @return the world loader for this world
     */
    WorldLoader getLoader();

    /**
     * Gets the type of a world
     *
     * @return The type of a world
     */
    LevelType getLevelType();

    /**
     * Gets the set boolean for the given gamerule
     *
     * @return The set boolean for the given gamerule
     */
    boolean getGameRule(String rule);

    /**
     * Gets the time in a world
     *
     * @return The time in a world
     */
    long getTime();

    /**
     * Gets the spawn location of a world
     *
     * @return The spawn location in a world
     */
    Position getSpawnPosition();

    /**
     * Checks if it is raining in a world
     *
     * @return True if it is raining in a world
     */
    boolean isRaining();

    /**
     * Gets the number of ticks before raining is toggled
     *
     * @return The number of ticks before raining is toggled
     */
    int getRainTime();

    /**
     * Checks if it is thundering in a world
     *
     * @return True if it is thundering in a world
     */
    boolean isThundering();

    /**
     * Gets the number of ticks before thundering is toggled
     *
     * @return The number of ticks before thundering is toggled
     */
    int getThunderTime();

    /**
     * Checks if structures are generated in a world (Stronghold, villages, dungeons)
     *
     * @return True if structures are generated in a world (Stronghold, villages, dungeons)
     */
    boolean canGenerateStructures();

    /**
     * Gets the size of the worldborder
     *
     * @return The size of the worldborder
     */
    double getBorderSize();

    /**
     * Gets the location where the worldborder is centered
     *
     * @return The location where the worldborder is centered
     */
    Position getBorderCenter();

    /**
     * Gets to what size a border is contracting, 60000000 by default
     *
     * @return To what size a border is contracting, 60000000 by default
     */
    int getBorderSizeContraction();

    /**
     * Gets the time the border has to contract to the contraction target
     *
     * @return The time the border has to contract to the contraction target
     */
    int getBorderSizeContractionTime();

    /**
     * Spawns an entity in the world
     *
     * @param type the type of entity to spawn
     * @return the entity spawn
     */
    Entity spawn(EntityType type, Position spawnPosition);

    /**
     * Get the entities currently in this world
     *
     * @return the entities in the world
     */
    Set<Entity> getEntities();
}
