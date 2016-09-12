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
package net.tridentsdk.base;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
public class BlockDirectionTest {

    @Test
    public void testMinecraftDirections() {
        for (BlockDirection dir : BlockDirection.values()) {
            if (dir.hasMinecraftDirection()) {
                assertEquals(dir, BlockDirection.fromMinecraftDirection(dir.getMinecraftDirection()));
            } else {
                assertNull(BlockDirection.fromMinecraftDirection(dir.getMinecraftDirection()));
            }
        }
    }

    @Test
    public void testDirectionalDifferences() {
        for (BlockDirection dir : BlockDirection.values()) {
            int[] diffs = new int[]{ dir.getXDiff(), dir.getYDiff(), dir.getZDiff() };
            long numNonZero = Arrays.stream(diffs).filter(i -> i != 0).count();
            assertEquals(dir.hasMinecraftDirection() ? 1 : 2, numNonZero);
        }
    }

    @Test
    public void testOpposites() {
        BiMap<BlockDirection, BlockDirection> opps = HashBiMap.create();
        opps.put(BlockDirection.UP, BlockDirection.DOWN);
        opps.put(BlockDirection.SOUTH, BlockDirection.NORTH);
        opps.put(BlockDirection.EAST, BlockDirection.WEST);
        opps.put(BlockDirection.NORTH_EAST, BlockDirection.SOUTH_WEST);
        opps.put(BlockDirection.NORTH_WEST, BlockDirection.SOUTH_EAST);

        for (BlockDirection dir : BlockDirection.values()) {
            if (opps.containsKey(dir)) {
                assertEquals(opps.get(dir), dir.getOpposite());
            } else {
                assertEquals(opps.inverse().get(dir), dir.getOpposite());
            }
        }

        assertEquals(BlockDirection.DOWN, BlockDirection.UP.getOpposite());
        assertEquals(BlockDirection.UP, BlockDirection.DOWN.getOpposite());
    }

    @Test
    public void testAnticlockwise() {
        BlockDirection[] dirs = { BlockDirection.NORTH, BlockDirection.NORTH_EAST, BlockDirection.EAST, BlockDirection.SOUTH_EAST, BlockDirection.SOUTH, BlockDirection.SOUTH_WEST, BlockDirection.WEST, BlockDirection.NORTH_WEST };

        for (int i = 0; i < dirs.length; i++) {
            assertEquals(dirs[(i + 7) % 8], dirs[i].anticlockwise(true));
        }

        for (int i = 0; i < dirs.length; i++) {
            assertEquals(dirs[(i + 6) % 8], dirs[i].anticlockwise(false));
        }

        for (int i = 0; i < dirs.length; i++) {
            assertEquals(dirs[(i + 6) % 8], dirs[i].anticlockwise());
        }

        assertEquals(BlockDirection.UP, BlockDirection.UP.anticlockwise(false));
        assertEquals(BlockDirection.DOWN, BlockDirection.DOWN.anticlockwise(false));
        assertEquals(BlockDirection.UP, BlockDirection.UP.anticlockwise(true));
        assertEquals(BlockDirection.DOWN, BlockDirection.DOWN.anticlockwise(true));
        assertEquals(BlockDirection.UP, BlockDirection.UP.anticlockwise());
        assertEquals(BlockDirection.DOWN, BlockDirection.DOWN.anticlockwise());
    }

    @Test
    public void testClockwise() {
        BlockDirection[] dirs = { BlockDirection.NORTH, BlockDirection.NORTH_EAST, BlockDirection.EAST, BlockDirection.SOUTH_EAST, BlockDirection.SOUTH, BlockDirection.SOUTH_WEST, BlockDirection.WEST, BlockDirection.NORTH_WEST };

        for (int i = 0; i < dirs.length; i++) {
            assertEquals(dirs[(i + 1) % 8], dirs[i].clockwise(true));
        }

        for (int i = 0; i < dirs.length; i++) {
            assertEquals(dirs[(i + 2) % 8], dirs[i].clockwise(false));
        }

        for (int i = 0; i < dirs.length; i++) {
            assertEquals(dirs[(i + 2) % 8], dirs[i].clockwise());
        }

        assertEquals(BlockDirection.UP, BlockDirection.UP.clockwise(false));
        assertEquals(BlockDirection.DOWN, BlockDirection.DOWN.clockwise(false));
        assertEquals(BlockDirection.UP, BlockDirection.UP.clockwise(true));
        assertEquals(BlockDirection.DOWN, BlockDirection.DOWN.clockwise(true));
        assertEquals(BlockDirection.UP, BlockDirection.UP.clockwise());
        assertEquals(BlockDirection.DOWN, BlockDirection.DOWN.clockwise());
    }

}
