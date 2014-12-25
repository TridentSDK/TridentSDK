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

package net.tridentsdk.meta.nbt;

import net.tridentsdk.meta.nbt.IntTag;
import net.tridentsdk.meta.nbt.ListTag;
import net.tridentsdk.meta.nbt.TagType;
import org.junit.Assert;
import org.junit.Test;

public class ListTagTest {

    @Test
    public void testListTags() throws Exception {
        ListTag tag = new ListTag("tag", TagType.INT);

        Assert.assertEquals(tag.listTags().size(), 0);

        IntTag intTag1 = new IntTag("IntTag1");
        tag.addTag(intTag1);

        Assert.assertEquals(tag.listTags().size(), 1);
        Assert.assertEquals(tag.listTags().get(0).getName(), "IntTag1");

        IntTag intTag2 = new IntTag("IntTag2");
        tag.addTag(intTag2);

        Assert.assertEquals(tag.listTags().size(), 2);

        Assert.assertEquals(tag.containsTag(intTag1), true);
        Assert.assertEquals(tag.containsTag(intTag2), true);
        Assert.assertEquals(tag.containsTag(null), false);

        Assert.assertEquals(tag.getTag(0), intTag1);
        Assert.assertEquals(tag.getTag(1), intTag2);

        tag.removeTag(intTag1);

        Assert.assertEquals(tag.listTags().size(), 1);
        Assert.assertEquals(tag.listTags().get(0).getName(), "IntTag2");

        tag.clearTags();

        Assert.assertEquals(tag.listTags().size(), 0);
    }

    @Test
    public void testGetInnerType() throws Exception {
        ListTag tag = new ListTag("tag", TagType.INT);
        Assert.assertEquals(tag.getInnerType(), TagType.INT);
    }

    @Test
    public void testGetType() throws Exception {
        ListTag tag = new ListTag("tag", TagType.INT);
        Assert.assertEquals(tag.getType(), TagType.LIST);
    }
}