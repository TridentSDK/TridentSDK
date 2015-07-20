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

package net.tridentsdk.world.gen;

import net.tridentsdk.world.ChunkLocation;

/**
 * The base class for implementing world generation extensions
 *
 * @author The TridentSDK Team
 * @since 0.3-alpha-DP
 */
public abstract class AbstractGenerator {
    protected final long seed;

    public AbstractGenerator(long seed) {
        this.seed = seed;
    }

    /**
     * Obtains the chunk generation seed
     *
     * @return the seed
     */
    public long seed() {
        return this.seed;
    }

    /**
     * Populates the block ids for a chunk
     *
     * <p>The first array index is the section number, the
     * second index is the position in that section, i.e.
     * x << 8 + y << 4 + z</p>
     *
     * <p>Should only be invoked by TridentChunk</p>
     *
     * @param location the location of the chunk to be generated
     * @return the data array to fill the chunk with
     */
    public abstract char[][] generateChunkBlocks(ChunkLocation location);

    /**
     * Populates block data for a chunk
     *
     * @param location the location of the chunk to be generated
     * @return the data to apply to each block
     */
    public abstract byte[][] generateBlockData(ChunkLocation location);
}
