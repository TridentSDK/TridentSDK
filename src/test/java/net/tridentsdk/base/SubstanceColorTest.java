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

import org.junit.Assert;
import org.junit.Test;

public class SubstanceColorTest {

    @Test
    public void testAsInt() throws Exception {
        Assert.assertEquals(SubstanceColor.WHITE.asInt(), 0);
        Assert.assertEquals(SubstanceColor.ORANGE.asInt(), 1);
        Assert.assertEquals(SubstanceColor.MAGENTA.asInt(), 2);
        Assert.assertEquals(SubstanceColor.LIGHT_BLUE.asInt(), 3);
        Assert.assertEquals(SubstanceColor.YELLOW.asInt(), 4);
        Assert.assertEquals(SubstanceColor.LIME.asInt(), 5);
        Assert.assertEquals(SubstanceColor.PINK.asInt(), 6);
        Assert.assertEquals(SubstanceColor.GRAY.asInt(), 7);
        Assert.assertEquals(SubstanceColor.SILVER.asInt(), 8);
        Assert.assertEquals(SubstanceColor.CYAN.asInt(), 9);
        Assert.assertEquals(SubstanceColor.PURPLE.asInt(), 10);
        Assert.assertEquals(SubstanceColor.BLUE.asInt(), 11);
        Assert.assertEquals(SubstanceColor.BROWN.asInt(), 12);
        Assert.assertEquals(SubstanceColor.GREEN.asInt(), 13);
        Assert.assertEquals(SubstanceColor.RED.asInt(), 14);
        Assert.assertEquals(SubstanceColor.BLACK.asInt(), 15);

        for (SubstanceColor color : SubstanceColor.values()) {
            byte[] original = color.asBytes(0);
            Assert.assertEquals(original[0], (byte) ((0 << 5 | 0 & 0x1F) & 0xFF));
            Assert.assertEquals(original[1], (byte) color.asInt());
        }
    }
}