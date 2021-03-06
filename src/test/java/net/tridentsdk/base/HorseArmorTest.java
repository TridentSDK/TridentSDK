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

import net.tridentsdk.meta.entity.living.animal.HorseArmor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Nick Robson
 */
public class HorseArmorTest {

    @Test
    public void testArmors() {
        assertEquals(3, HorseArmor.LEATHER.getArmor());
        assertEquals(5, HorseArmor.IRON.getArmor());
        assertEquals(7, HorseArmor.GOLD.getArmor());
        assertEquals(11, HorseArmor.DIAMOND.getArmor());
    }

}
