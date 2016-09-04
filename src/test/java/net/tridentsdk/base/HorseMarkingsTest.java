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
public class HorseMarkingsTest {

    @Test
    public void testMarkings() {
        for (HorseMarkings markings : HorseMarkings.values()) {
            assertEquals(markings, HorseMarkings.of(markings.getData()));
        }
    }

    @Test
    public void testIndex() {
        for (int i = 0; i <= 6; i++) {
            HorseMarkings.of(i);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testError() {
        HorseMarkings.of(7);
    }

    @Test
    public void testData() {
        assertEquals(HorseMarkings.WHITE, HorseMarkings.of(0));
        assertEquals(HorseMarkings.CREAMY, HorseMarkings.of(1));
        assertEquals(HorseMarkings.CHESTNUT, HorseMarkings.of(2));
        assertEquals(HorseMarkings.BROWN, HorseMarkings.of(3));
        assertEquals(HorseMarkings.BLACK, HorseMarkings.of(4));
        assertEquals(HorseMarkings.GRAY, HorseMarkings.of(5));
        assertEquals(HorseMarkings.DARK_BROWN, HorseMarkings.of(6));
    }

}
