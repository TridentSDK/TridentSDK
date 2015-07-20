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

import net.tridentsdk.base.Block;
import net.tridentsdk.base.Position;
import net.tridentsdk.entity.Entity;
import net.tridentsdk.entity.types.EntityType;
import net.tridentsdk.registry.Registered;
import net.tridentsdk.world.settings.WorldSettings;

import java.util.Set;

/**
 * A Minecraft world
 * <p>
 * <p>Worlds can be created using the following code:
 * <pre>{@code
 *      WorldLoader loader = Factory.newWorldLoader();
 *      loader.createWorld("New world");
 * }</pre></p>
 * <p>
 * <p>A collection of the worlds on the server can be obtained using {@link Registered#worlds()}</p>
 *
 * @author The TridentSDK Team
 * @since 0.3-alpha-DP
 */
public interface World extends Cloneable, WorldSettings {
    /**
     * Gets the name of the world
     *
     * @return the name of the world
     */
    String name();

    /**
     * Gets the chunk on the given location, and generates the chunk if it does not exist.
     *
     * @return The chunk on the given location
     */
    Chunk chunkAt(ChunkLocation loc, boolean generateIfNotFound);

    /**
     * Gets the chunk on the given x and z , and generates the chunk if it does not exist
     *
     * @return The chunk on the given location
     */
    Chunk chunkAt(int x, int z, boolean generateIfNotFound);

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
    Block blockAt(Position location);

    /**
     * Obtains the loading handler which created this object, passed in from the constructor
     *
     * @return the world loader for this world
     */
    WorldLoader loader();

    /**
     * Gets the time in the world
     *
     * @return The time in the world
     */
    long time();

    /**
     * Gets the spawn location of the world
     *
     * @return The spawn location in the world
     */
    Position spawnPosition();

    /**
     * Obtains the weather controller for the world
     *
     * @return the weather controller
     */
    WeatherConditions weather();

    /**
     * Obtains the world border properties of this world
     *
     * @return the border properties
     */
    WorldBorder border();

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
    Set<Entity> entities();
}
