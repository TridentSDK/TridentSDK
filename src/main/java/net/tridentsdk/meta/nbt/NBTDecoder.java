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

import com.google.common.base.Charsets;
import net.tridentsdk.util.TridentLogger;

import java.io.DataInput;
import java.io.IOException;

/**
 * @author The TridentSDK Team
 */
public class NBTDecoder {
    final DataInput input;

    public NBTDecoder(DataInput input) {
        this.input = input;
    }

    public CompoundTag decode() throws NBTException {
        try {
            return this.decode(this.input.readByte());
        } catch (IOException e) {
            TridentLogger.error(new NBTException("IO Error decoding the NBT Data", e));
            return null;
        }
    }

    public CompoundTag decode(byte b) throws NBTException {
        TagType initType = TagType.getById(b);

        //NBT source must start with a compound tag or is invalid
        if (initType != TagType.COMPOUND) {
            TridentLogger.error(new NBTException("NBT Data must start with a Compound Tag."));
        }

        //Create the resulting CompoundTag to return
        //Uses recursion to recursively walk through the tag tree
        try {
            return this.resolveCompoundTag(this.readString());
        } catch (IOException e) {
            TridentLogger.error(new NBTException("IO Error decoding the NBT Data", e));
            return null;
        }
    }

    private CompoundTag resolveCompoundTag(String name) throws IOException {
        CompoundTag compound = new CompoundTag(name);
        TagType innerType;

        while ((innerType = TagType.getById(this.input.readByte())) != TagType.END) {
            compound.addTag(this.resolveTag(innerType, true));
        }

        return compound;
    }

    private ListTag resolveListTag(String name) throws IOException {
        TagType listType = TagType.getById(this.input.readByte());
        ListTag list = new ListTag(name, listType);
        int length = this.input.readInt();

        for (int i = 0; i < length; i++) {
            list.addTag(this.resolveTag(listType, false));
        }

        return list;
    }

    private NBTTag resolveTag(TagType type, boolean withName) throws IOException {
        //Reads name if required
        String name = null;
        if (withName) {
            name = this.readString();
        }

        NBTTag result;
        switch (type) {
            case BYTE:
                result = new ByteTag(name);
                result.asType(ByteTag.class).setValue(this.input.readByte());
                break;

            case SHORT:
                result = new ShortTag(name);
                result.asType(ShortTag.class).setValue(this.input.readShort());
                break;

            case INT:
                result = new IntTag(name);
                result.asType(IntTag.class).setValue(this.input.readInt());
                break;

            case LONG:
                result = new LongTag(name);
                result.asType(LongTag.class).setValue(this.input.readLong());
                break;

            case FLOAT:
                result = new FloatTag(name);
                result.asType(FloatTag.class).setValue(this.input.readFloat());
                break;

            case DOUBLE:
                result = new DoubleTag(name);
                result.asType(DoubleTag.class).setValue(this.input.readDouble());
                break;

            case BYTE_ARRAY:
                result = new ByteArrayTag(name);
                int balength = this.input.readInt();
                byte[] babytes = new byte[balength];

                this.input.readFully(babytes);
                result.asType(ByteArrayTag.class).setValue(babytes);

                break;

            case STRING:
                result = new StringTag(name);
                result.asType(StringTag.class).setValue(this.readString());

                break;

            case LIST:
                result = this.resolveListTag(name);
                break;

            case COMPOUND:
                result = this.resolveCompoundTag(name);
                break;

            case INT_ARRAY:
                result = new IntArrayTag(name);
                int ialength = this.input.readInt();
                int[] array = new int[ialength];

                for (int i = 0; i < array.length; i++) {
                    array[i] = this.input.readInt();
                }

                result.asType(IntArrayTag.class).setValue(array);
                break;

            default:
                result = new NullTag(name);
                break;
        }

        return result;
    }

    private String readString() throws IOException {
        short length = this.input.readShort();
        byte[] bytes = new byte[(int) length];

        this.input.readFully(bytes);

        return new String(bytes, Charsets.UTF_8);
    }
}
