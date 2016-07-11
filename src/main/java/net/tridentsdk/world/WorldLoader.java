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

import net.tridentsdk.registry.Registered;
import net.tridentsdk.world.gen.ChunkGenerator;
import net.tridentsdk.world.gen.FeatureGenerator;
import net.tridentsdk.world.settings.WorldCreateOptions;

import javax.annotation.concurrent.ThreadSafe;
import java.util.List;

/**
 * Manages the worlds for the server
 *
 * @author The TridentSDK Team
 * @since 0.3-alpha-DP
 */
@ThreadSafe
public interface WorldLoader {
    /**
     * Creates a new world loader using the default generator
     *
     * @return the new world loader
     */
    static WorldLoader newLoader() {
        return Registered.impl().newLoader(null);
    }

    /**
     * Creates a new world loader using the generator specified
     *
     * @param clas the class of the generator to use
     * @return the new world loaer
     */
    static WorldLoader newLoader(Class<? extends ChunkGenerator> clas) {
        return Registered.impl().newLoader(clas);
    }

    /**
     * Checks if the world has been loaded yet
     *
     * @param world the name of the folder to check
     * @return {@code true} if the world has been loaded
     */
    static boolean worldExists(String world) {
        return Registered.worlds().containsKey(world);
    }

    /**
     * Load an existing world inside the server's file container
     *
     * @param world the name of the folder containing a world
     * @return the world loaded
     */
    World load(String world);

    /**
     * Creates a new world
     *
     * @param name the name of the new world
     * @return the world which was created
     */
    World createWorld(String name);

    /**
     * Writes the changes made to the world to the world folder
     */
    void save();

    /**
     * Checks the existence of a chunk in a world, based on the world directory
     *
     * @param x     the X coordinate of the chunk
     * @param z     the Z coordinate of the chunk
     * @return {@code true} if the chunk is not present within the world directory
     */
    boolean chunkExists(int x, int z);

    /**
     * Checks the existence of a chunk in a world, based on the world directory
     *
     * @param position the position which the chunk should be checked for existence
     * @return {@code true} if the chunk is not present within the world directory
     */
    boolean chunkExists(ChunkLocation position);

    /**
     * Loads the chunk into the world
     *
     * @param x     the X of the chunk
     * @param z     the Z of the chunk
     * @return the chunk which was loaded
     */
    Chunk loadChunk(int x, int z);

    /**
     * Loads the chunk into the world
     *
     * @param position the position of the chunk to load
     * @return the chunk which was loaded
     */
    Chunk loadChunk(ChunkLocation position);

    /**
     * Writes the changes in the chunk to the world file
     *
     * @param chunk the chunk which to write the changes
     */
    void saveChunk(Chunk chunk);

    /**
     * Options used for creating the world
     *
     * @return the world creation options
     */
    WorldCreateOptions options();

    /**
     * The generator used to load new chunks used by this world loader
     *
     * @return the generation abstraction to generated chunks
     */
    ChunkGenerator generator();

    /**
     * Obtains a mutable collection of the overlay brushes used to generate the world
     *
     * @return the overlay brushes
     */
    List<FeatureGenerator> brushes();
}