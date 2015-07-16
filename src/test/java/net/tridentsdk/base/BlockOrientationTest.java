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

import net.tridentsdk.Position;
import net.tridentsdk.util.Vector;
import org.junit.Assert;
import org.junit.Test;

public class BlockOrientationTest {

    @Test
    public void testGetDifference() throws Exception {
        Assert.assertEquals(BlockOrientation.NORTH.getVector(), new Vector(0, 0, -1));
        Assert.assertEquals(BlockOrientation.SOUTH.getVector(), new Vector(0, 0, 1));
        Assert.assertEquals(BlockOrientation.EAST.getVector(), new Vector(1, 0, 0));
        Assert.assertEquals(BlockOrientation.WEST.getVector(), new Vector(-1, 0, 0));
        Assert.assertEquals(BlockOrientation.NORTH_EAST.getVector(), new Vector(1, 0, -1));
        Assert.assertEquals(BlockOrientation.NORTH_WEST.getVector(), new Vector(-1, 0, -1));
        Assert.assertEquals(BlockOrientation.SOUTH_EAST.getVector(), new Vector(1, 0, 1));
        Assert.assertEquals(BlockOrientation.SOUTH_WEST.getVector(), new Vector(-1, 0, 1));
        Assert.assertEquals(BlockOrientation.TOP.getVector(), new Vector(0, 1, 0));
        Assert.assertEquals(BlockOrientation.BOTTOM.getVector(), new Vector(0, -1, 0));
        Assert.assertEquals(BlockOrientation.SELF.getVector(), new Vector(0, 0, 0));
    }

    @Test
    public void testApply() throws Exception {
        Assert.assertEquals(BlockOrientation.NORTH.apply(Position.create(null, 0d, 0d, 0d)),
                Position.create(null, 0, 0, -1));
        Assert.assertEquals(BlockOrientation.SOUTH.apply(Position.create(null, 0d, 0d, 0d)),
                Position.create(null, 0, 0, 1));
        Assert.assertEquals(BlockOrientation.EAST.apply(Position.create(null, 0d, 0d, 0d)),
                Position.create(null, 1, 0, 0));
        Assert.assertEquals(BlockOrientation.WEST.apply(Position.create(null, 0d, 0d, 0d)),
                Position.create(null, -1, 0, 0));
        Assert.assertEquals(BlockOrientation.NORTH_EAST.apply(Position.create(null, 0d, 0d, 0d)),
                Position.create(null, 1, 0, -1));
        Assert.assertEquals(BlockOrientation.NORTH_WEST.apply(Position.create(null, 0d, 0d, 0d)),
                Position.create(null, -1, 0, -1));
        Assert.assertEquals(BlockOrientation.SOUTH_EAST.apply(Position.create(null, 0d, 0d, 0d)),
                Position.create(null, 1, 0, 1));
        Assert.assertEquals(BlockOrientation.SOUTH_WEST.apply(Position.create(null, 0d, 0d, 0d)),
                Position.create(null, -1, 0, 1));
        Assert.assertEquals(BlockOrientation.TOP.apply(Position.create(null, 0d, 0d, 0d)),
                Position.create(null, 0, 1, 0));
        Assert.assertEquals(BlockOrientation.BOTTOM.apply(Position.create(null, 0d, 0d, 0d)),
                Position.create(null, 0, -1, 0));
        Assert.assertEquals(BlockOrientation.SELF.apply(Position.create(null, 0d, 0d, 0d)),
                Position.create(null, 0, 0, 0));
    }
}