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

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Nick Robson
 */
public class HorseColorTest {

    @Test
    public void testColors() {
        for (HorseColor color : HorseColor.values()) {
            assertEquals(color, HorseColor.of(color.getData()));
        }
    }

    @Test
    public void testIndex() {
        for (int i = 0; i <= 4; i++) {
            HorseColor.of(i);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testError() {
        HorseColor.of(5);
    }

    @Test
    public void testData() {
        assertEquals(HorseColor.NONE, HorseColor.of(0));
        assertEquals(HorseColor.WHITE, HorseColor.of(1));
        assertEquals(HorseColor.WHITE_FIELD, HorseColor.of(2));
        assertEquals(HorseColor.WHITE_DOTS, HorseColor.of(3));
        assertEquals(HorseColor.BLACK_DOTS, HorseColor.of(4));
    }

}
