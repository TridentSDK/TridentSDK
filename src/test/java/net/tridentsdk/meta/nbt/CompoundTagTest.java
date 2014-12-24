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
package java.net.tridentsdk.meta.nbt;

import net.tridentsdk.meta.nbt.*;
import org.junit.Assert;
import org.junit.Test;

public class CompoundTagTest {

    @Test
    public void testListTags() throws Exception{
        CompoundTag tag = new CompoundTag("tag");

        Assert.assertEquals(tag.listTags().size(), 0);

        StringTag stringTag = new StringTag("StringTag");
        tag.addTag(stringTag);

        Assert.assertEquals(tag.listTags().size(), 1);
        Assert.assertEquals(tag.listTags().get(0).getName(), "StringTag");

        IntTag intTag = new IntTag("IntTag");
        tag.addTag(intTag);

        Assert.assertEquals(tag.listTags().size(), 2);

        Assert.assertEquals(tag.containsTag("IntTag"), true);
        Assert.assertEquals(tag.containsTag("StringTag"), true);
        Assert.assertEquals(tag.containsTag("NoTag"), false);

        if(!(tag.getTag("NoTag") instanceof NullTag)){
            Assert.fail();
        }

        Assert.assertEquals(tag.getTag("StringTag"), stringTag);
        Assert.assertEquals(tag.getTag("IntTag"), intTag);

        if(!(tag.getTagAs("NoTag") instanceof NullTag)){
            Assert.fail();
        }

        Assert.assertEquals(tag.getTagAs("StringTag"), stringTag);
        Assert.assertEquals(tag.getTagAs("IntTag"), intTag);

        tag.removeTag("StringTag");

        Assert.assertEquals(tag.listTags().size(), 1);
        Assert.assertEquals(tag.listTags().get(0).getName(), "IntTag");

        tag.clearTags();

        Assert.assertEquals(tag.listTags().size(), 0);
    }

    @Test
    public void testGetType() throws Exception{
        CompoundTag tag = new CompoundTag("tag");
        Assert.assertEquals(tag.getType(), TagType.COMPOUND);
    }

}