package net.tridentsdk.world.gen;

import net.tridentsdk.world.ChunkLocation;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ChunkAxisAlignedBoundingBoxTest {

    @Test
    public void testChunkAABB () {
        ChunkAxisAlignedBoundingBox box = new ChunkAxisAlignedBoundingBox(ChunkLocation.create(0, 0), ChunkLocation.create(0, 0));
        Iterator<ChunkLocation> iter = box.iterator();

        assertTrue(iter.hasNext());
        iter.next();
        assertFalse(iter.hasNext());

        ChunkAxisAlignedBoundingBox box2 = new ChunkAxisAlignedBoundingBox(ChunkLocation.create(0, 0), ChunkLocation.create(1, 1));
        iter = box2.iterator();

        for(int i = 0; i < 4; i++) {
            assertTrue(iter.hasNext());
            iter.next();
        }
        assertFalse(iter.hasNext());
    }

}