/*
 * Copyright (c) 2014, The TridentSDK Team
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     1. Redistributions of source code must retain the above copyright
 *        notice, this list of conditions and the following disclaimer.
 *     2. Redistributions in binary form must reproduce the above copyright
 *        notice, this list of conditions and the following disclaimer in the
 *        documentation and/or other materials provided with the distribution.
 *     3. Neither the name of the The TridentSDK Team nor the
 *        names of its contributors may be used to endorse or promote products
 *        derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL The TridentSDK Team BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package net.tridentsdk.api.nbt;

import com.google.common.base.Charsets;

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
            throw new NBTException("IO Error encoding the NBT Data", e);
        }
    }

    private void writeCompoundTag(CompoundTag tag) throws IOException {
        for (NBTTag inner : tag.listTags()) {
            this.writeTag(inner);
        }
        //Write Tag_End to signify end of Compound
        this.output.writeByte(TagType.END.getId());
    }

    private void writeListTag(ListTag tag) throws IOException {
        //Write inner tag-type id
        this.output.writeByte(tag.getInnerType().getId());

        List<NBTTag> innerTags = tag.listTags();
        this.output.writeInt(innerTags.size());

        for (NBTTag inner : innerTags) {
            this.writeTag(inner);
        }
    }

    private void writeTag(NBTTag tag) throws IOException {
        this.output.writeByte(tag.getType().getId());

        if (tag.hasName()) {
            this.writeString(tag.getName());
        }

        switch (tag.getType()) {
            case BYTE:
                this.output.writeByte((int) tag.asType(ByteTag.class).getValue());
                break;

            case SHORT:
                this.output.writeShort((int) tag.asType(ShortTag.class).getValue());
                break;

            case INT:
                this.output.writeInt(tag.asType(IntTag.class).getValue());
                break;

            case LONG:
                this.output.writeLong(tag.asType(LongTag.class).getValue());
                break;

            case FLOAT:
                this.output.writeFloat(tag.asType(FloatTag.class).getValue());
                break;

            case DOUBLE:
                this.output.writeDouble(tag.asType(DoubleTag.class).getValue());
                break;

            case BYTE_ARRAY:
                byte[] barray = tag.asType(ByteArrayTag.class).getValue();
                this.output.writeInt(barray.length);
                this.output.write(barray);
                break;

            case STRING:
                this.writeString(tag.asType(StringTag.class).getValue());
                break;

            case LIST:
                this.writeListTag(tag.asType(ListTag.class));
                break;

            case COMPOUND:
                this.writeCompoundTag(tag.asType(CompoundTag.class));
                break;

            case INT_ARRAY:
                int[] iarray = tag.asType(IntArrayTag.class).getValue();
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
