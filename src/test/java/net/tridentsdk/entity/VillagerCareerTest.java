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

import net.tridentsdk.entity.types.VillagerCareer;
import net.tridentsdk.entity.types.VillagerProfession;
import org.junit.Assert;
import org.junit.Test;

public class VillagerCareerTest {

    @Test
    public void testGetParent() throws Exception {
        Assert.assertEquals(VillagerCareer.FLETCHER.getProfession(), VillagerProfession.FARMER);
        Assert.assertEquals(VillagerCareer.FARMER.getProfession(), VillagerProfession.FARMER);
        Assert.assertEquals(VillagerCareer.FISHERMAN.getProfession(), VillagerProfession.FARMER);
        Assert.assertEquals(VillagerCareer.SHEPHERD.getProfession(), VillagerProfession.FARMER);
        Assert.assertEquals(VillagerCareer.LIBRARIAN.getProfession(), VillagerProfession.LIBRARIAN);
        Assert.assertEquals(VillagerCareer.CLERIC.getProfession(), VillagerProfession.PRIEST);
        Assert.assertEquals(VillagerCareer.TOOL_SMITH.getProfession(), VillagerProfession.BLACKSMITH);
        Assert.assertEquals(VillagerCareer.ARMORER.getProfession(), VillagerProfession.BLACKSMITH);
        Assert.assertEquals(VillagerCareer.WEAPON_SMITH.getProfession(), VillagerProfession.BLACKSMITH);
        Assert.assertEquals(VillagerCareer.BUTCHER.getProfession(), VillagerProfession.BUTCHER);
        Assert.assertEquals(VillagerCareer.LEATHERWORKER.getProfession(), VillagerProfession.BUTCHER);
    }

    @Test
    public void testGetId() throws Exception {
        Assert.assertEquals(VillagerCareer.FLETCHER.asInt(), 0);
        Assert.assertEquals(VillagerCareer.FARMER.asInt(), 1);
        Assert.assertEquals(VillagerCareer.FISHERMAN.asInt(), 2);
        Assert.assertEquals(VillagerCareer.SHEPHERD.asInt(), 3);
        Assert.assertEquals(VillagerCareer.LIBRARIAN.asInt(), 0);
        Assert.assertEquals(VillagerCareer.CLERIC.asInt(), 0);
        Assert.assertEquals(VillagerCareer.TOOL_SMITH.asInt(), 0);
        Assert.assertEquals(VillagerCareer.ARMORER.asInt(), 1);
        Assert.assertEquals(VillagerCareer.WEAPON_SMITH.asInt(), 2);
        Assert.assertEquals(VillagerCareer.BUTCHER.asInt(), 0);
        Assert.assertEquals(VillagerCareer.LEATHERWORKER.asInt(), 1);
    }
}