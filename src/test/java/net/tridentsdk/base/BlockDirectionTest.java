package net.tridentsdk.base;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

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
    }

}
