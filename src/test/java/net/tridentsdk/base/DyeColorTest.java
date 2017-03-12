/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2017 The TridentSDK Team
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

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Nick Robson
 */
public class DyeColorTest {

    @Test
    public void testColors() {
        for (DyeColor color : DyeColor.values()) {
            assertEquals(color, DyeColor.of(color.getData()));
        }
    }

    @Test
    public void testIndex() {
        for (int i = 0; i <= 0xF; i++) {
            DyeColor.of(i);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testError() {
        DyeColor.of(16);
    }

    @Test
    public void testData() {
        assertEquals(DyeColor.BLACK, DyeColor.of(0x0));
        assertEquals(DyeColor.RED, DyeColor.of(0x1));
        assertEquals(DyeColor.DARK_GREEN, DyeColor.of(0x2));
        assertEquals(DyeColor.BROWN, DyeColor.of(0x3));
        assertEquals(DyeColor.DARK_BLUE, DyeColor.of(0x4));
        assertEquals(DyeColor.DARK_PURPLE, DyeColor.of(0x5));
        assertEquals(DyeColor.CYAN, DyeColor.of(0x6));
        assertEquals(DyeColor.LIGHT_GRAY, DyeColor.of(0x7));
        assertEquals(DyeColor.DARK_GRAY, DyeColor.of(0x8));
        assertEquals(DyeColor.PINK, DyeColor.of(0x9));
        assertEquals(DyeColor.LIGHT_GREEN, DyeColor.of(0xA));
        assertEquals(DyeColor.YELLOW, DyeColor.of(0xB));
        assertEquals(DyeColor.LIGHT_BLUE, DyeColor.of(0xC));
        assertEquals(DyeColor.MAGENTA, DyeColor.of(0xD));
        assertEquals(DyeColor.ORANGE, DyeColor.of(0xE));
        assertEquals(DyeColor.WHITE, DyeColor.of(0xF));
    }

}
