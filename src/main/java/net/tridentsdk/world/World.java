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
package net.tridentsdk.world;

import net.tridentsdk.base.Block;
import net.tridentsdk.base.Position;
import net.tridentsdk.world.opt.GenOpts;
import net.tridentsdk.world.opt.Weather;
import net.tridentsdk.world.opt.WorldBorder;
import net.tridentsdk.world.opt.WorldOpts;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import java.nio.file.Path;
import java.util.Collection;

/**
 * This class is a representation of a world
 *
 * <p>A world is defined as a folder containing compressed
 * chunk data and may be accessed using this class both
 * whilst loaded and unloaded.</p>
 *
 * @author TridentSDK
 * @since 0.3-alpha-DP
 */
@ThreadSafe
public interface World {
    /**
     * Obtains the name of the world that is represented
     * with the name of the file folder containing the
     * region files.
     *
     * @return the name of the world
     */
    String name();

    /**
     * Obtains the current world time in ticks.
     *
     * <p>Each day-night cycle takes 23999 ticks to complete
     * where 0 is sunrise and 24000 is the next day's 0.</p>
     *
     * @return the current 24000 tick time cycle time
     */
    int time();

    /**
     * Obtains the options that this world has been created
     * to use.
     *
     * @return the world options
     */
    WorldOpts opts();

    /**
     * Obtains the weather conditions currently taking place
     * in the world.
     *
     * @return the weather options
     */
    Weather weather();

    /**
     * Obtains the options for the generator of this world.
     *
     * @return the generator options
     */
    GenOpts genOpts();

    /**
     * Obtains the world border
     *
     * @return the world border options
     */
    WorldBorder border();

    /**
     * Obtains the chunk that is located at the given X/Z
     * horizontal plane coordinates.
     *
     * @param x the x coordinate which to find the chunk
     * @param z the z coordinate which to find the chunk
     * @return the chunk at the given coordinates
     */
    @Nonnull
    Chunk chunkAt(int x, int z);

    /**
     * Obtains a chunk at the given coordinates with an
     * option to not generate the chunk.
     *
     * @param x the x coordinate
     * @param z the z coordinate
     * @param gen {@code true} to generate the chunk if it
     * doesn't exist, {@code false} to return null
     * @return the chunk, or {@code null} if {@code gen} is
     * {@code false} and no chunk is found
     */
    @Nullable
    Chunk chunkAt(int x, int z, boolean gen);

    /**
     * Obtains the collection of chunks that are currently
     * loaded on this world.
     *
     * @return the collection of loaded chunks
     */
    Collection<? extends Chunk> loadedChunks();

    /**
     * Obtains the block that is located at the given XYZ
     * coordinates.
     *
     * <p>This method should be preferred over
     * {@link Position#block()} if you do not already have
     * a Position object.</p>
     *
     * @param x the x coordinate which to find the block
     * @param y the y coordinate which to find the block
     * @param z the z coordinate which to find the block
     * @return the block located at the given position
     */
    Block blockAt(int x, int y, int z);

    /**
     * Obtains the enclosing directory which contains the
     * region and data files of this worlds's chunks.
     *
     * @return the world's directory
     */
    Path dir();

    /**
     * Saves this world to the region files in the world
     * directory.
     */
    void save();
}