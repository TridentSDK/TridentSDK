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

/**
 * @author The TridentSDK Team
 */
public enum TagType {
    NULL(-1, NullTag.class),

    END(0, null),

    BYTE(1, ByteTag.class),

    SHORT(2, ShortTag.class),

    INT(3, IntTag.class),

    LONG(4, LongTag.class),

    FLOAT(5, FloatTag.class),

    DOUBLE(6, DoubleTag.class),

    BYTE_ARRAY(7, ByteArrayTag.class),

    STRING(8, StringTag.class),

    LIST(9, ListTag.class),

    COMPOUND(10, CompoundTag.class),

    INT_ARRAY(11, IntArrayTag.class);

    int id;
    Class<? extends NBTTag> implClass;

    TagType(int id, Class<? extends NBTTag> implClass) {
        this.id = id;
        this.implClass = implClass;
    }

    public static TagType fromId(byte fromId) {
        for (TagType type : TagType.values()) {
            if (type.id == fromId) {
                return type;
            }
        }
        return TagType.NULL;
    }

    public Class<? extends NBTTag> getImplementation() {
        return this.implClass;
    }

    public int getId() {
        return this.id;
    }
}
