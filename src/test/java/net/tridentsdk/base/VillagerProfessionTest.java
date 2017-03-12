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
public class VillagerProfessionTest {

    @Test
    public void testTypes() {
        for (VillagerProfession type : VillagerProfession.values()) {
            assertEquals(type, VillagerProfession.of(type.getData()));
        }
    }

    @Test
    public void testIndex() {
        for (int i = 0; i <= 4; i++) {
            VillagerProfession.of(i);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testError() {
        VillagerProfession.of(5);
    }

    @Test
    public void testData() {
        assertEquals(VillagerProfession.FARMER, VillagerProfession.of(0));
        assertEquals(VillagerProfession.LIBRARIAN, VillagerProfession.of(1));
        assertEquals(VillagerProfession.PRIEST, VillagerProfession.of(2));
        assertEquals(VillagerProfession.BLACKSMITH, VillagerProfession.of(3));
        assertEquals(VillagerProfession.BUTCHER, VillagerProfession.of(4));
    }

    @Test
    public void testZombies() {
        for (VillagerProfession type : VillagerProfession.values()) {
            assertEquals(ZombieType.of(type.getData() + 1), ZombieType.valueOf("VILLAGER_" + type.name()));
        }
    }

}
