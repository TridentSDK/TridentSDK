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

import com.google.common.collect.Lists;
import net.tridentsdk.world.ChunkLocation;

import java.util.List;

/**
 * The base class for implementing world generation extensions
 *
 * @author The TridentSDK Team
 */
public abstract class AbstractGenerator {
    /**
     * Where ChunkLocation is the x/z of the block for the height to be specified in the value
     *
     * @param x the x coordinate to find the height
     * @param z the z coordinate to find the height
     * @return the height at that coordinate
     */
    public abstract int height(int x, int z);

    /**
     * The block to be set at the coordinates
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @param z the z coordinate
     * @return the block to be set at the coordinates
     */
    public abstract TempGenBlock atCoordinate(int x, int y, int z);


    public List<TempGenBlock> doGen(ChunkLocation corner1, ChunkLocation corner2) {
        int minX = Math.min(corner1.x(), corner2.x()) << 4;
        int maxX = (Math.max(corner1.x(), corner2.x()) << 4) + 16;
        int minZ = Math.min(corner1.z(), corner2.z()) << 4;
        int maxZ = (Math.max(corner1.z(), corner2.z()) << 4) + 16;

        List<TempGenBlock> gen = Lists.newArrayList();

        for (int x = minX; x < maxX; x++) {
            for (int z = minZ; z < maxZ; z++) {
                int height = height(x, z);

                gen.add(atCoordinate(x, height, z));
                for (int i = height; i >= 0; i--) {
                    gen.add(atCoordinate(x, i, z));
                }
            }
        }

        return gen;
    }


    /**
     * What is called to populate the block ids for a chunk
     *
     * <p>The first array index is the section number, the
     * second index is the position in that section, i.e.
     * x << 8 + y << 4 + z</p>
     *
     * <p>Should only be invoked by TridentChunk</p>
     * @param location
     * @return
     */
    public abstract char[][] generateChunkBlocks(ChunkLocation location);


    /**
     * What is called to populate block data for a chunk
     * @param location
     * @return
     */
    public abstract byte[][] generateBlockData(ChunkLocation location);
}
