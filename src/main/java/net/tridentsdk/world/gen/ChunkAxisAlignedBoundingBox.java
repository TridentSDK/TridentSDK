package net.tridentsdk.world.gen;

import net.tridentsdk.world.ChunkLocation;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
* Created by Dildas on 2/7/2015.
*/
public class ChunkAxisAlignedBoundingBox implements Iterable<ChunkLocation> {
    final int minX;
    final int minZ;
    final int maxX;
    final int maxZ;

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
    }
}
