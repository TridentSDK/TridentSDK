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

import net.tridentsdk.base.Block;
import net.tridentsdk.base.Substance;
import net.tridentsdk.world.ChunkLocation;

import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * An overlay brush is a block populator that is used to lay blocks or structures once the chunk has been generated
 *
 * @author The TridentSDK Team
 */
public abstract class AbstractOverlayBrush {
    protected final long seed;

    public AbstractOverlayBrush(long seed) {
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
     * Brushes the terrain
     *
     * @param location the location of the chunk to brush
     * @param relX the relative chunk X of the chunk
     * @param relZ the relative Z
     * @param random the generator random
     * @param heights the height array to edit
     * @param manipulator the tool used to set block types in the chunk and to cross
     *                    chunk boundaries if needed
     */
    public abstract void brush(ChunkLocation location,
                               int relX,
                               int relZ,
                               GeneratorRandom random,
                               AtomicReferenceArray<Integer> heights,
                               ChunkManipulator manipulator);

    /**
     * Provides special functionality for chunk manipulation
     *
     * @author The TridentSDK Team
     */
    public interface ChunkManipulator {
        /**
         * Manipulates the chunk
         *
         * @param relX the relative x, can be out of 0-15 range
         * @param y the y coordinate
         * @param relZ the relative z, can be out of 0-15 range
         * @param substance the substance to set
         * @param data the data to set
         */
        void manipulate(int relX, int y, int relZ, Substance substance, byte data);

        /**
         * Obtains the block at the relative location
         *
         * @param relX the relative x
         * @param y the y
         * @param relZ the relative z
         * @return the block
         */
        Block blockAt(int relX, int y, int relZ);

        /**
         * Ensures that the particular feature fits within the bounds of the chunk
         *
         * @param relX  the relative x
         * @param relZ  the relative z
         * @param bound the range from the center non-inclusively counting the last block
         * @return {@code true} if the node is within bound
         */
        default boolean nodeFits(int relX, int relZ, int bound) {
            return relX - bound >= 0 && relX + bound <= 15
                    && relZ - bound >= 0 && relZ + bound <= 15;
        }
    }
}