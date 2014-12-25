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

import net.tridentsdk.entity.VillagerProfession;
import org.junit.Assert;
import org.junit.Test;

public class VillagerProfessionTest {

    @Test
    public void testToInt() throws Exception {
        Assert.assertEquals(VillagerProfession.FARMER.toInt(), 0);
        Assert.assertEquals(VillagerProfession.LIBRARIAN.toInt(), 1);
        Assert.assertEquals(VillagerProfession.PRIEST.toInt(), 2);
        Assert.assertEquals(VillagerProfession.BLACKSMITH.toInt(), 3);
        Assert.assertEquals(VillagerProfession.BUTCHER.toInt(), 4);
    }

    @Test
    public void testToInt1() throws Exception {
        Assert.assertEquals(VillagerProfession.toInt(VillagerProfession.FARMER), 0);
        Assert.assertEquals(VillagerProfession.toInt(VillagerProfession.LIBRARIAN), 1);
        Assert.assertEquals(VillagerProfession.toInt(VillagerProfession.PRIEST), 2);
        Assert.assertEquals(VillagerProfession.toInt(VillagerProfession.BLACKSMITH), 3);
        Assert.assertEquals(VillagerProfession.toInt(VillagerProfession.BUTCHER), 4);
    }
}