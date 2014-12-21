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

import org.junit.Assert;
import org.junit.Test;

public class TagTypeTest {

    @Test
    public void testFromId() throws Exception{
        Assert.assertEquals(TagType.fromId((byte) -1), TagType.NULL);
        Assert.assertEquals(TagType.fromId((byte) 0), TagType.END);
        Assert.assertEquals(TagType.fromId((byte) 1), TagType.BYTE);
        Assert.assertEquals(TagType.fromId((byte) 2), TagType.SHORT);
        Assert.assertEquals(TagType.fromId((byte) 3), TagType.INT);
        Assert.assertEquals(TagType.fromId((byte) 4), TagType.LONG);
        Assert.assertEquals(TagType.fromId((byte) 5), TagType.FLOAT);
        Assert.assertEquals(TagType.fromId((byte) 6), TagType.DOUBLE);
        Assert.assertEquals(TagType.fromId((byte) 7), TagType.BYTE_ARRAY);
        Assert.assertEquals(TagType.fromId((byte) 8), TagType.STRING);
        Assert.assertEquals(TagType.fromId((byte) 9), TagType.LIST);
        Assert.assertEquals(TagType.fromId((byte) 10), TagType.COMPOUND);
        Assert.assertEquals(TagType.fromId((byte) 11), TagType.INT_ARRAY);
        Assert.assertEquals(TagType.fromId((byte) 12), TagType.NULL);
    }

    @Test
    public void testGetImplementation() throws Exception{
        Assert.assertEquals(TagType.NULL.getImplementation(), NullTag.class);
        Assert.assertEquals(TagType.END.getImplementation(), null);
        Assert.assertEquals(TagType.BYTE.getImplementation(), ByteTag.class);
        Assert.assertEquals(TagType.SHORT.getImplementation(), ShortTag.class);
        Assert.assertEquals(TagType.INT.getImplementation(), IntTag.class);
        Assert.assertEquals(TagType.LONG.getImplementation(), LongTag.class);
        Assert.assertEquals(TagType.FLOAT.getImplementation(), FloatTag.class);
        Assert.assertEquals(TagType.DOUBLE.getImplementation(), DoubleTag.class);
        Assert.assertEquals(TagType.BYTE_ARRAY.getImplementation(), ByteArrayTag.class);
        Assert.assertEquals(TagType.STRING.getImplementation(), StringTag.class);
        Assert.assertEquals(TagType.LIST.getImplementation(), ListTag.class);
        Assert.assertEquals(TagType.COMPOUND.getImplementation(), CompoundTag.class);
        Assert.assertEquals(TagType.INT_ARRAY.getImplementation(), IntArrayTag.class);
    }

    @Test
    public void testGetId() throws Exception{
        Assert.assertEquals(TagType.NULL.getId(), -1);
        Assert.assertEquals(TagType.END.getId(), 0);
        Assert.assertEquals(TagType.BYTE.getId(), 1);
        Assert.assertEquals(TagType.SHORT.getId(), 2);
        Assert.assertEquals(TagType.INT.getId(), 3);
        Assert.assertEquals(TagType.LONG.getId(), 4);
        Assert.assertEquals(TagType.FLOAT.getId(), 5);
        Assert.assertEquals(TagType.DOUBLE.getId(), 6);
        Assert.assertEquals(TagType.BYTE_ARRAY.getId(), 7);
        Assert.assertEquals(TagType.STRING.getId(), 8);
        Assert.assertEquals(TagType.LIST.getId(), 9);
        Assert.assertEquals(TagType.COMPOUND.getId(), 10);
        Assert.assertEquals(TagType.INT_ARRAY.getId(), 11);
    }

}