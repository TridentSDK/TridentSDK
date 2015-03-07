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
        Assert.assertEquals(VillagerCareer.FLETCHER.parent(), VillagerProfession.FARMER);
        Assert.assertEquals(VillagerCareer.FARMER.parent(), VillagerProfession.FARMER);
        Assert.assertEquals(VillagerCareer.FISHERMAN.parent(), VillagerProfession.FARMER);
        Assert.assertEquals(VillagerCareer.SHEPHERD.parent(), VillagerProfession.FARMER);
        Assert.assertEquals(VillagerCareer.LIBRARIAN.parent(), VillagerProfession.LIBRARIAN);
        Assert.assertEquals(VillagerCareer.CLERIC.parent(), VillagerProfession.PRIEST);
        Assert.assertEquals(VillagerCareer.TOOL_SMITH.parent(), VillagerProfession.BLACKSMITH);
        Assert.assertEquals(VillagerCareer.ARMORER.parent(), VillagerProfession.BLACKSMITH);
        Assert.assertEquals(VillagerCareer.WEAPON_SMITH.parent(), VillagerProfession.BLACKSMITH);
        Assert.assertEquals(VillagerCareer.BUTCHER.parent(), VillagerProfession.BUTCHER);
        Assert.assertEquals(VillagerCareer.LEATHERWORKER.parent(), VillagerProfession.BUTCHER);
    }

    @Test
    public void testGetId() throws Exception {
        Assert.assertEquals(VillagerCareer.FLETCHER.id(), 0);
        Assert.assertEquals(VillagerCareer.FARMER.id(), 1);
        Assert.assertEquals(VillagerCareer.FISHERMAN.id(), 2);
        Assert.assertEquals(VillagerCareer.SHEPHERD.id(), 3);
        Assert.assertEquals(VillagerCareer.LIBRARIAN.id(), 0);
        Assert.assertEquals(VillagerCareer.CLERIC.id(), 0);
        Assert.assertEquals(VillagerCareer.TOOL_SMITH.id(), 0);
        Assert.assertEquals(VillagerCareer.ARMORER.id(), 1);
        Assert.assertEquals(VillagerCareer.WEAPON_SMITH.id(), 2);
        Assert.assertEquals(VillagerCareer.BUTCHER.id(), 0);
        Assert.assertEquals(VillagerCareer.LEATHERWORKER.id(), 1);
    }
}