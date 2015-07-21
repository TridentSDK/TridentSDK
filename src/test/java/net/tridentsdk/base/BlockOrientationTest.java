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

package net.tridentsdk.base;

import net.tridentsdk.util.Vector;
import org.junit.Assert;
import org.junit.Test;

public class BlockOrientationTest {

    @Test
    public void testGetDifference() throws Exception {
        Assert.assertEquals(BlockDirection.NORTH.difference(), new Vector(0, 0, -1));
        Assert.assertEquals(BlockDirection.SOUTH.difference(), new Vector(0, 0, 1));
        Assert.assertEquals(BlockDirection.EAST.difference(), new Vector(1, 0, 0));
        Assert.assertEquals(BlockDirection.WEST.difference(), new Vector(-1, 0, 0));
        Assert.assertEquals(BlockDirection.NORTH_EAST.difference(), new Vector(1, 0, -1));
        Assert.assertEquals(BlockDirection.NORTH_WEST.difference(), new Vector(-1, 0, -1));
        Assert.assertEquals(BlockDirection.SOUTH_EAST.difference(), new Vector(1, 0, 1));
        Assert.assertEquals(BlockDirection.SOUTH_WEST.difference(), new Vector(-1, 0, 1));
        Assert.assertEquals(BlockDirection.TOP.difference(), new Vector(0, 1, 0));
        Assert.assertEquals(BlockDirection.BOTTOM.difference(), new Vector(0, -1, 0));
        Assert.assertEquals(BlockDirection.SELF.difference(), new Vector(0, 0, 0));
    }

    @Test
    public void testApply() throws Exception {
        Assert.assertEquals(BlockDirection.NORTH.apply(Position.create(null, 0d, 0d, 0d)),
                Position.create(null, 0, 0, -1));
        Assert.assertEquals(BlockDirection.SOUTH.apply(Position.create(null, 0d, 0d, 0d)),
                Position.create(null, 0, 0, 1));
        Assert.assertEquals(BlockDirection.EAST.apply(Position.create(null, 0d, 0d, 0d)),
                Position.create(null, 1, 0, 0));
        Assert.assertEquals(BlockDirection.WEST.apply(Position.create(null, 0d, 0d, 0d)),
                Position.create(null, -1, 0, 0));
        Assert.assertEquals(BlockDirection.NORTH_EAST.apply(Position.create(null, 0d, 0d, 0d)),
                Position.create(null, 1, 0, -1));
        Assert.assertEquals(BlockDirection.NORTH_WEST.apply(Position.create(null, 0d, 0d, 0d)),
                Position.create(null, -1, 0, -1));
        Assert.assertEquals(BlockDirection.SOUTH_EAST.apply(Position.create(null, 0d, 0d, 0d)),
                Position.create(null, 1, 0, 1));
        Assert.assertEquals(BlockDirection.SOUTH_WEST.apply(Position.create(null, 0d, 0d, 0d)),
                Position.create(null, -1, 0, 1));
        Assert.assertEquals(BlockDirection.TOP.apply(Position.create(null, 0d, 0d, 0d)),
                Position.create(null, 0, 1, 0));
        Assert.assertEquals(BlockDirection.BOTTOM.apply(Position.create(null, 0d, 0d, 0d)),
                Position.create(null, 0, -1, 0));
        Assert.assertEquals(BlockDirection.SELF.apply(Position.create(null, 0d, 0d, 0d)),
                Position.create(null, 0, 0, 0));
    }
}