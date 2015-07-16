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

package net.tridentsdk.world;

import org.junit.Assert;
import org.junit.Test;

public class DimensionTest {

    @Test
    public void testToByte() throws Exception {
        Assert.assertEquals(Dimension.NETHER.asByte(), (byte) -1);
        Assert.assertEquals(Dimension.OVERWORLD.asByte(), (byte) 0);
        Assert.assertEquals(Dimension.END.asByte(), (byte) 1);
    }

    @Test
    public void testGetDimension() throws Exception {
        Assert.assertEquals(Dimension.getDimension(-1), Dimension.NETHER);
        Assert.assertEquals(Dimension.getDimension(0), Dimension.OVERWORLD);
        Assert.assertEquals(Dimension.getDimension(1), Dimension.END);
        Assert.assertEquals(Dimension.getDimension(5), null);
    }
}