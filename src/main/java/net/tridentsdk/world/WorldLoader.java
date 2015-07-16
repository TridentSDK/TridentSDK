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

import net.tridentsdk.world.gen.AbstractGenerator;

/**
 * Manages the worlds for the server
 *
 * @author The TridentSDK Team
 */
public interface WorldLoader {
    /**
     * Load an existing world inside the server's file container
     *
     * @param world the name of the folder containing a world
     * @return the world loaded
     */
    World load(String world);

    /**
     * Writes the changes made to the world to the world folder
     *
     * @param world the world to save the changes
     */
    void save(World world);

    /**
     * Creates a new world
     *
     * @param name the name of the new world
     * @return the world which was created
     */
    World createWorld(String name);

    /**
     * Checks if the world has been loaded yet
     *
     * @param world the name of the folder to check
     * @return {@code true} if the world has been loaded
     */
    boolean worldExists(String world);

    /**
     * Checks the existence of a chunk in a world, based on the world directory
     *
     * @param world the world which to check the existence of a chunk
     * @param x     the X coordinate of the chunk
     * @param z     the Z coordinate of the chunk
     * @return {@code true} if the chunk is not present within the world directory
     */
    boolean chunkExists(World world, int x, int z);

    /**
     * Checks the existence of a chunk in a world, based on the world directory
     *
     * @param world    the world which to check the existence of a chunk
     * @param location the location which the chunk should be checked for existence
     * @return {@code true} if the chunk is not present within the world directory
     */
    boolean chunkExists(World world, ChunkLocation location);

    /**
     * Loads the chunk into the world
     *
     * @param world the world which to load the chunk
     * @param x     the X of the chunk
     * @param z     the Z of the chunk
     * @return the chunk which was loaded
     */
    Chunk loadChunk(World world, int x, int z);

    /**
     * Loads the chunk into the world
     *
     * @param world    the world which to load the chunk
     * @param location the location of the chunk to load
     * @return the chunk which was loaded
     */
    Chunk loadChunk(World world, ChunkLocation location);

    /**
     * Writes the changes in the chunk to the world file
     *
     * @param chunk the chunk which to write the changes
     */
    void saveChunk(Chunk chunk);

    /**
     * The generator used to load new chunks used by this world loader
     *
     * @return the generation abstraction to generated chunks
     */
    AbstractGenerator getGenerator();
}
