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
public class ZombieTypeTest {

    @Test
    public void testTypes() {
        for (ZombieType type : ZombieType.values()) {
            assertEquals(type, ZombieType.of(type.getData()));
        }
    }

    @Test
    public void testIndex() {
        for (int i = 0; i <= 6; i++) {
            ZombieType.of(i);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testError() {
        ZombieType.of(7);
    }

    @Test
    public void testData() {
        assertEquals(ZombieType.NORMAL, ZombieType.of(0));
        assertEquals(ZombieType.VILLAGER_FARMER, ZombieType.of(1));
        assertEquals(ZombieType.VILLAGER_LIBRARIAN, ZombieType.of(2));
        assertEquals(ZombieType.VILLAGER_PRIEST, ZombieType.of(3));
        assertEquals(ZombieType.VILLAGER_BLACKSMITH, ZombieType.of(4));
        assertEquals(ZombieType.VILLAGER_BUTCHER, ZombieType.of(5));
        assertEquals(ZombieType.HUSK, ZombieType.of(6));
    }

    @Test
    public void testVillagers() {
        for (ZombieType type : ZombieType.values()) {
            if (type.isVillager()) {
                assertEquals(VillagerProfession.of(type.getData() - 1), VillagerProfession.valueOf(type.name().substring(9)));
            }
        }
    }

}
