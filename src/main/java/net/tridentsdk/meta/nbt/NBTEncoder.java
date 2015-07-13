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

import java.io.DataOutput;
import java.io.IOException;
import java.util.List;

/**
 * @author The TridentSDK Team
 */
public class NBTEncoder {
    final DataOutput output;

    public NBTEncoder(DataOutput output) {
        this.output = output;
    }

    public void encode(CompoundTag tag) throws NBTException {
        try {
            this.writeTag(tag);
        } catch (IOException e) {
            TridentLogger.error(new NBTException("IO Error encoding the NBT Data", e));
        }
    }

    private void writeCompoundTag(CompoundTag tag) throws IOException {
        for (NBTTag inner : tag.listTags()) {
            this.writeTag(inner);
        }
        //Write Tag_End to signify end of Compound
        this.output.writeByte(TagType.END.id());
    }

    private void writeListTag(ListTag tag) throws IOException {
        //Write inner tag-type id
        this.output.writeByte(tag.getInnerType().id());

        List<NBTTag> innerTags = tag.listTags();
        this.output.writeInt(innerTags.size());

        for (NBTTag inner : innerTags) {
            this.writeTag(inner, false, false);
        }
    }

    public void writeTag(NBTTag tag) throws IOException {
        writeTag(tag, true, true);
    }

    private void writeTag(NBTTag tag, boolean name, boolean id) throws IOException {
        if (id) {
            this.output.writeByte(tag.type().id());
        }

        if (tag.hasName() && name) {
            this.writeString(tag.name());
        }

        switch (tag.type()) {
            case BYTE:
                this.output.writeByte((int) tag.asType(ByteTag.class).value());
                break;

            case SHORT:
                this.output.writeShort((int) tag.asType(ShortTag.class).value());
                break;

            case INT:
                this.output.writeInt(tag.asType(IntTag.class).value());
                break;

            case LONG:
                this.output.writeLong(tag.asType(LongTag.class).value());
                break;

            case FLOAT:
                this.output.writeFloat(tag.asType(FloatTag.class).value());
                break;
            case DOUBLE:
                this.output.writeDouble(tag.asType(DoubleTag.class).value());
                break;

            case BYTE_ARRAY:
                byte[] barray = tag.asType(ByteArrayTag.class).value();
                this.output.writeInt(barray.length);
                this.output.write(barray);
                break;

            case STRING:
                this.writeString(tag.asType(StringTag.class).value());
                break;

            case LIST:
                this.writeListTag(tag.asType(ListTag.class));
                break;

            case COMPOUND:
                this.writeCompoundTag(tag.asType(CompoundTag.class));
                break;

            case INT_ARRAY:
                int[] iarray = tag.asType(IntArrayTag.class).value();
                this.output.writeInt(iarray.length);
                for (int anIarray : iarray) {
                    this.output.writeInt(anIarray);
                }
                break;

            default:
                //Shouldn't/Can't happen
                break;
        }
    }

    private void writeString(String s) throws IOException {
        this.output.writeShort(s.length());
        this.output.write(s.getBytes(Charsets.UTF_8));
    }
}
