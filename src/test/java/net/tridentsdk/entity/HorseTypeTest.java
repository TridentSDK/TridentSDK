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

package net.tridentsdk.entity;

import net.tridentsdk.entity.types.HorseType;
import org.junit.Assert;
import org.junit.Test;

public class HorseTypeTest {

    @Test
    public void testGetId() throws Exception {
        Assert.assertEquals(HorseType.HORSE.id(), 0);
        Assert.assertEquals(HorseType.DONKEY.id(), 1);
        Assert.assertEquals(HorseType.MULE.id(), 2);
        Assert.assertEquals(HorseType.ZOMBIE.id(), 3);
        Assert.assertEquals(HorseType.SKELETON.id(), 4);
    }
}