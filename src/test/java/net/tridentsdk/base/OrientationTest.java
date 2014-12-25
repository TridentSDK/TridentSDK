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

import net.tridentsdk.base.Orientation;
import org.junit.Assert;
import org.junit.Test;

public class OrientationTest {

    @Test
    public void testEnum() throws Exception {
        Assert.assertEquals(Orientation.NORTH, Orientation.NORTH);
        Assert.assertEquals(Orientation.SOUTH, Orientation.SOUTH);
        Assert.assertEquals(Orientation.EAST, Orientation.EAST);
        Assert.assertEquals(Orientation.WEST, Orientation.WEST);
        Assert.assertEquals(Orientation.NORTH_WEST, Orientation.NORTH_WEST);
        Assert.assertEquals(Orientation.SOUTH_WEST, Orientation.SOUTH_WEST);
        Assert.assertEquals(Orientation.NORTH_EAST, Orientation.NORTH_EAST);
        Assert.assertEquals(Orientation.SOUTH_EAST, Orientation.SOUTH_EAST);
        Assert.assertEquals(Orientation.NORTH_NORTH_WEST, Orientation.NORTH_NORTH_WEST);
        Assert.assertEquals(Orientation.NORTH_WEST_WEST, Orientation.NORTH_WEST_WEST);
        Assert.assertEquals(Orientation.SOUTH_SOUTH_WEST, Orientation.SOUTH_SOUTH_WEST);
        Assert.assertEquals(Orientation.SOUTH_WEST_WEST, Orientation.SOUTH_WEST_WEST);
        Assert.assertEquals(Orientation.NORTH_NORTH_EAST, Orientation.NORTH_NORTH_EAST);
        Assert.assertEquals(Orientation.NORTH_EAST_EAST, Orientation.NORTH_EAST_EAST);
        Assert.assertEquals(Orientation.SOUTH_SOUTH_EAST, Orientation.SOUTH_SOUTH_EAST);
        Assert.assertEquals(Orientation.SOUTH_EAST_EAST, Orientation.SOUTH_EAST_EAST);
    }
}