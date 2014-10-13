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
public class CompoundTagBuilder<B> {
    private final CompoundTag current;
    private final B parentBuilder;
    private TagContainer parent;

    protected CompoundTagBuilder(CompoundTag tag, B parentBuilder) {
        this.parentBuilder = parentBuilder;
        this.current = tag;
    }

    public CompoundTagBuilder(String name, TagContainer parent, B parentBuilder) {
        this.parent = parent;
        this.parentBuilder = parentBuilder;
        this.current = new CompoundTag(name);
        parent.addTag(this.current);
    }

    public CompoundTagBuilder<CompoundTagBuilder<B>> beginCompoundTag(String name) {
        return new CompoundTagBuilder<>(name, this.current, this);
    }

    public B endCompoundTag() {
        return this.parentBuilder;
    }

    public ListTagBuilder<CompoundTagBuilder<B>> beginListTag(String name, TagType type) {
        return new ListTagBuilder<>(name, this.current, this, type);
    }

    public CompoundTagBuilder<B> compoundTag(CompoundTag value) {
        this.current.addTag(value);
        return this;
    }

    public CompoundTagBuilder<B> listTag(ListTag tag) {
        this.current.addTag(tag);
        return this;
    }

    public CompoundTagBuilder<B> nullTag(String name) {
        this.current.addTag(new NullTag(name));
        return this;
    }

    public CompoundTagBuilder<B> byteArrayTag(String name, byte... value) {
        this.current.addTag(new ByteArrayTag(name).setValue(value));
        return this;
    }

    public CompoundTagBuilder<B> byteTag(String name, byte value) {
        this.current.addTag(new ByteTag(name).setValue(value));
        return this;
    }

    public CompoundTagBuilder<B> doubleTag(String name, double value) {
        this.current.addTag(new DoubleTag(name).setValue(value));
        return this;
    }

    public CompoundTagBuilder<B> floatTag(String name, float value) {
        this.current.addTag(new FloatTag(name).setValue(value));
        return this;
    }

    public CompoundTagBuilder<B> intArrayTag(String name, int... value) {
        this.current.addTag(new IntArrayTag(name).setValue(value));
        return this;
    }

    public CompoundTagBuilder<B> intTag(String name, int value) {
        this.current.addTag(new IntTag(name).setValue(value));
        return this;
    }

    public CompoundTagBuilder<B> longTag(String name, long value) {
        this.current.addTag(new LongTag(name).setValue(value));
        return this;
    }

    public CompoundTagBuilder<B> shortTag(String name, short value) {
        this.current.addTag(new ShortTag(name).setValue(value));
        return this;
    }

    public CompoundTagBuilder<B> stringTag(String name, String value) {
        this.current.addTag(new StringTag(name).setValue(value));
        return this;
    }
}
