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
package net.tridentsdk.ui.bossbar;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Nick Robson
 */
public class BossBarDivisionTest {

    @Test
    public void testDivisions() {
        for (BossBarDivision division : BossBarDivision.values()) {
            assertEquals(division, BossBarDivision.of(division.getId()));
        }
    }

    @Test
    public void testIndex() {
        assertEquals(5, BossBarDivision.values().length);
        for (int i = 0; i <= 4; i++) {
            BossBarDivision.of(i);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testError() {
        BossBarDivision.of(5);
    }

    @Test
    public void testIDs() {
        assertEquals(BossBarDivision.NO_DIVISION, BossBarDivision.of(0));
        assertEquals(BossBarDivision.NOTCHES_6, BossBarDivision.of(1));
        assertEquals(BossBarDivision.NOTCHES_10, BossBarDivision.of(2));
        assertEquals(BossBarDivision.NOTCHES_12, BossBarDivision.of(3));
        assertEquals(BossBarDivision.NOTCHES_20, BossBarDivision.of(4));
    }

    @Test
    public void testNotches() {
        assertEquals(BossBarDivision.NO_DIVISION.getNotches(), 0);
        assertEquals(BossBarDivision.NOTCHES_6.getNotches(), 6);
        assertEquals(BossBarDivision.NOTCHES_10.getNotches(), 10);
        assertEquals(BossBarDivision.NOTCHES_12.getNotches(), 12);
        assertEquals(BossBarDivision.NOTCHES_20.getNotches(), 20);
    }

}
