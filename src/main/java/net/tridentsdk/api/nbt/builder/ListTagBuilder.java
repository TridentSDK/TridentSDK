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

package net.tridentsdk.api.nbt.builder;

import net.tridentsdk.api.nbt.*;

/**
 * @author The TridentSDK Team
 */
public class ListTagBuilder<B> {
    private final TagContainer parent;
    private final ListTag current;
    private final B parentBuilder;
    private final TagType type;

    public ListTagBuilder(String name, TagContainer parent, B parentBuilder, TagType type) {
        this.parent = parent;
        this.parentBuilder = parentBuilder;
        this.type = type;
        this.current = new ListTag(name, type);
        parent.addTag(this.current);
    }

    public CompoundTagBuilder<ListTagBuilder<B>> beginCompoundTag(String name) {
        return new CompoundTagBuilder<>(name, this.current, this);
    }

    public B endListTag() {
        return this.parentBuilder;
    }

    public ListTagBuilder<ListTagBuilder<B>> beginListTag(String name, TagType type) {
        return new ListTagBuilder<>(name, this.current, this, type);
    }

    public ListTagBuilder<B> tag(Object value) {
        switch (this.type) {
            case BYTE:
                if (value instanceof Byte) {
                    this.current.addTag(new ByteTag(null).setValue((byte) value));
                }
                break;
            case SHORT:
                if (value instanceof Short) {
                    this.current.addTag(new ShortTag(null).setValue((short) value));
                }
                break;
            case INT:
                if (value instanceof Integer) {
                    this.current.addTag(new IntTag(null).setValue((int) value));
                }
                break;
            case LONG:
                if (value instanceof Long) {
                    this.current.addTag(new LongTag(null).setValue((long) value));
                }
                break;
            case FLOAT:
                if (value instanceof Float) {
                    this.current.addTag(new FloatTag(null).setValue((float) value));
                }
                break;
            case DOUBLE:
                if (value instanceof Double) {
                    this.current.addTag(new DoubleTag(null).setValue((double) value));
                }
                break;
            case BYTE_ARRAY:
                if (value instanceof byte[]) {
                    this.current.addTag(new ByteArrayTag(null).setValue((byte[]) value));
                }
                break;
            case STRING:
                if (value instanceof String) {
                    this.current.addTag(new StringTag(null).setValue((String) value));
                }
                break;
            case INT_ARRAY:
                if (value instanceof int[]) {
                    this.current.addTag(new IntArrayTag(null).setValue((int[]) value));
                }
                break;
            default:
                break;
        }

        return this;
    }
}
