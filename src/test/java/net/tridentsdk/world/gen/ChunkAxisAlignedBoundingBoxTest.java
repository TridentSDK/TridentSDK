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