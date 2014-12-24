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
package java.net.tridentsdk.entity;

import net.tridentsdk.entity.VillagerCareer;
import net.tridentsdk.entity.VillagerProfession;
import org.junit.Assert;
import org.junit.Test;

public class VillagerCareerTest {

    @Test
    public void testGetParent() throws Exception{
        Assert.assertEquals(VillagerCareer.FLETCHER.getParent(), VillagerProfession.FARMER);
        Assert.assertEquals(VillagerCareer.FARMER.getParent(), VillagerProfession.FARMER);
        Assert.assertEquals(VillagerCareer.FISHERMAN.getParent(), VillagerProfession.FARMER);
        Assert.assertEquals(VillagerCareer.SHEPHERD.getParent(), VillagerProfession.FARMER);
        Assert.assertEquals(VillagerCareer.LIBRARIAN.getParent(), VillagerProfession.LIBRARIAN);
        Assert.assertEquals(VillagerCareer.CLERIC.getParent(), VillagerProfession.PRIEST);
        Assert.assertEquals(VillagerCareer.TOOL_SMITH.getParent(), VillagerProfession.BLACKSMITH);
        Assert.assertEquals(VillagerCareer.ARMORER.getParent(), VillagerProfession.BLACKSMITH);
        Assert.assertEquals(VillagerCareer.WEAPON_SMITH.getParent(), VillagerProfession.BLACKSMITH);
        Assert.assertEquals(VillagerCareer.BUTCHER.getParent(), VillagerProfession.BUTCHER);
        Assert.assertEquals(VillagerCareer.LEATHERWORKER.getParent(), VillagerProfession.BUTCHER);
    }

    @Test
    public void testGetId() throws Exception{
        Assert.assertEquals(VillagerCareer.FLETCHER.getId(), 0);
        Assert.assertEquals(VillagerCareer.FARMER.getId(), 1);
        Assert.assertEquals(VillagerCareer.FISHERMAN.getId(), 2);
        Assert.assertEquals(VillagerCareer.SHEPHERD.getId(), 3);
        Assert.assertEquals(VillagerCareer.LIBRARIAN.getId(), 0);
        Assert.assertEquals(VillagerCareer.CLERIC.getId(), 0);
        Assert.assertEquals(VillagerCareer.TOOL_SMITH.getId(), 0);
        Assert.assertEquals(VillagerCareer.ARMORER.getId(), 1);
        Assert.assertEquals(VillagerCareer.WEAPON_SMITH.getId(), 2);
        Assert.assertEquals(VillagerCareer.BUTCHER.getId(), 0);
        Assert.assertEquals(VillagerCareer.LEATHERWORKER.getId(), 1);
    }

}