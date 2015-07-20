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

import net.tridentsdk.docs.AccessNoDoc;
import net.tridentsdk.world.ChunkLocation;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Represents an iterator over chunks within a specified range
 *
 * @author The TridentSDK Team
 * @since 0.3-alpha-DP
 */
@NotThreadSafe
public class ChunkAxisAlignedBoundingBox implements Iterable<ChunkLocation> {
    final int minX;
    final int minZ;
    final int maxX;
    final int maxZ;

    /**
     * Creates a new Chunk bounding box
     *
     * @param location1 the first chunk corner
     * @param location2 the second chunk corner
     */
    public ChunkAxisAlignedBoundingBox(ChunkLocation location1, ChunkLocation location2) {
        minX = (location1.x() < location2.x()) ? location1.x() : location2.x();
        minZ = (location1.z() < location2.z()) ? location1.z() : location2.z();

        maxX = (location1.x() > location2.x()) ? location1.x() : location2.x();
        maxZ = (location1.z() > location2.z()) ? location1.z() : location2.z();
    }

    @Override
    public Iterator<ChunkLocation> iterator() {
        return new ChunkAABBIterator(this);
    }

    @AccessNoDoc
    private class ChunkAABBIterator implements Iterator<ChunkLocation> {
        private final ChunkAxisAlignedBoundingBox parent;

        private final int xDistance;
        private final int zDistance;
        private final int numChunks;
        private int index = 0;

        private ChunkAABBIterator(ChunkAxisAlignedBoundingBox chunkLocations) {
            parent = chunkLocations;

            xDistance = parent.maxX - parent.minX + 1;
            zDistance = parent.maxZ - parent.minZ + 1;
            numChunks = xDistance * zDistance;
        }

        @Override
        public boolean hasNext() {
            return index < numChunks;
        }

        @Override
        public ChunkLocation next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No element found past index " + index);
            }
            ChunkLocation retVal;
            if (zDistance == 0 && xDistance == 0) {
                retVal = ChunkLocation.create(minX, minZ);
            }
            else if (zDistance == 0) {
                retVal = ChunkLocation.create(index % xDistance, minZ);
            }
            else if (xDistance == 0) {
                retVal = ChunkLocation.create(minX, index / zDistance);
            }
            else {
                retVal = ChunkLocation.create(index % xDistance, index / zDistance);
            }
            index ++;
            return retVal;
        }

        @Override
        public void remove() {
        }
    }
}
