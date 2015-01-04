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

public class NBTSerializerTest {

    @Test
    public void testSerialize() throws Exception {
        TestSerializable testSerializable = new TestSerializable();

        CompoundTag serialized = NBTSerializer.serialize(testSerializable);

        if (serialized == null) {
            Assert.fail();
        }

        TestSerializable deserialized = NBTSerializer.deserialize(new TestSerializable(), serialized);

        if (deserialized == null) {
            Assert.fail();
        }

        Assert.assertEquals(deserialized.getTestNull(), null);
        Assert.assertEquals(deserialized.getTestByte(), 1);
        Assert.assertEquals(deserialized.getTestShort(), 5);
        Assert.assertEquals(deserialized.getTestInt(), 123);
        Assert.assertEquals(deserialized.getTestLong(), 10000);
        Assert.assertEquals(deserialized.getTestFloat(), 25.5, 1);
        Assert.assertEquals(deserialized.getTestDouble(), 35.5, 1);
        Assert.assertEquals(new String(deserialized.getTestByteArray()), "byteArray");
        Assert.assertEquals(deserialized.getTestString(), "Hello World!");
        Assert.assertEquals(deserialized.getTestList().getTag(0).getName(), "listIntTag");
        Assert.assertEquals(deserialized.getTestCompound().getTag("compoundIntTag").getType(), TagType.INT);

        Assert.assertEquals(deserialized.getTestIntArray()[0], 1);
        Assert.assertEquals(deserialized.getTestIntArray()[1], 2);
        Assert.assertEquals(deserialized.getTestIntArray()[2], 3);
        Assert.assertEquals(deserialized.getTestIntArray()[3], 4);
        Assert.assertEquals(deserialized.getTestIntArray()[4], 5);
    }

    public class TestSerializable implements NBTSerializable {

        @NBTField(name = "testNull", type = TagType.NULL)
        protected String testNull = null;
        @NBTField(name = "testByte", type = TagType.BYTE)
        protected byte testByte = 1;
        @NBTField(name = "testShort", type = TagType.SHORT)
        protected short testShort = 5;
        @NBTField(name = "testInt", type = TagType.INT)
        protected int testInt = 123;
        @NBTField(name = "testLong", type = TagType.LONG)
        protected long testLong = 10000;
        @NBTField(name = "testFloat", type = TagType.FLOAT)
        protected float testFloat = 25.5f;
        @NBTField(name = "testDouble", type = TagType.DOUBLE)
        protected double testDouble = 35.5d;
        @NBTField(name = "testByteArray", type = TagType.BYTE_ARRAY)
        protected byte[] testByteArray = "byteArray".getBytes();
        @NBTField(name = "testString", type = TagType.STRING)
        protected String testString = "Hello World!";
        @NBTField(name = "testList", type = TagType.LIST)
        protected ListTag testList = new ListTag("listTag", TagType.INT);
        @NBTField(name = "testCompound", type = TagType.COMPOUND)
        protected CompoundTag testCompound = new CompoundTag("compoundTag");
        @NBTField(name = "testIntArray", type = TagType.INT_ARRAY)
        protected int[] testIntArray = new int[] { 1, 2, 3, 4, 5 };

        protected TestSerializable() {
            testList.addTag(new IntTag("listIntTag"));
            testCompound.addTag(new IntTag("compoundIntTag"));
        }

        public String getTestNull() {
            return testNull;
        }

        public byte getTestByte() {
            return testByte;
        }

        public short getTestShort() {
            return testShort;
        }

        public int getTestInt() {
            return testInt;
        }

        public long getTestLong() {
            return testLong;
        }

        public float getTestFloat() {
            return testFloat;
        }

        public double getTestDouble() {
            return testDouble;
        }

        public byte[] getTestByteArray() {
            return testByteArray;
        }

        public String getTestString() {
            return testString;
        }

        public ListTag getTestList() {
            return testList;
        }

        public CompoundTag getTestCompound() {
            return testCompound;
        }

        public int[] getTestIntArray() {
            return testIntArray;
        }
    }
}