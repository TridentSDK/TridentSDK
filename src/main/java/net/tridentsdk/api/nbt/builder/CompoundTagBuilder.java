/*
 * Copyright (c) 2014, The TridentSDK Team
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 *     2. Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 *     3. Neither the name of TridentSDK nor the names of its
 *   contributors may be used to endorse or promote products derived from
 *   this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package net.tridentsdk.api.nbt.builder;

import net.tridentsdk.api.nbt.ByteArrayTag;
import net.tridentsdk.api.nbt.ByteTag;
import net.tridentsdk.api.nbt.CompoundTag;
import net.tridentsdk.api.nbt.DoubleTag;
import net.tridentsdk.api.nbt.FloatTag;
import net.tridentsdk.api.nbt.IntArrayTag;
import net.tridentsdk.api.nbt.IntTag;
import net.tridentsdk.api.nbt.LongTag;
import net.tridentsdk.api.nbt.ShortTag;
import net.tridentsdk.api.nbt.StringTag;
import net.tridentsdk.api.nbt.TagContainer;
import net.tridentsdk.api.nbt.TagType;

/**
 * @author The TridentSDK Team
 */
public class CompoundTagBuilder<B> {
    private TagContainer parent;
    private CompoundTag current;
    private B parentBuilder;
    
    protected CompoundTagBuilder(CompoundTag tag, B parentBuilder) {
        this.parentBuilder = parentBuilder;
        current = tag;
    }
        
    public CompoundTagBuilder(String name, TagContainer parent, B parentBuilder) {
        this.parent = parent;
        this.parentBuilder = parentBuilder;
        current = new CompoundTag(name);
        parent.addTag(current);
    }
    
    public CompoundTagBuilder<CompoundTagBuilder<B>> beginCompoundTag(String name) {
        return new CompoundTagBuilder<CompoundTagBuilder<B>>(name, current, this);
    }
    
    public B endCompoundTag() {
        return parentBuilder;
    }
    
    public ListTagBuilder<CompoundTagBuilder<B>> beginListTag(String name, TagType type) {
        return new ListTagBuilder<CompoundTagBuilder<B>>(name, current, this, type);
    }
    
    public CompoundTagBuilder<B> byteArrayTag(String name, byte[] value) {
        current.addTag(new ByteArrayTag(name).setValue(value));
        return this;
    }
    
    public CompoundTagBuilder<B> byteTag(String name, byte value) {
        current.addTag(new ByteTag(name).setValue(value));
        return this;
    }
    
    public CompoundTagBuilder<B> doubleTag(String name, double value) {
        current.addTag(new DoubleTag(name).setValue(value));
        return this;
    }
    
    public CompoundTagBuilder<B> floatTag(String name, float value) {
        current.addTag(new FloatTag(name).setValue(value));
        return this;
    }
    
    public CompoundTagBuilder<B> intArrayTag(String name, int[] value) {
        current.addTag(new IntArrayTag(name).setValue(value));
        return this;
    }
    
    public CompoundTagBuilder<B> intTag(String name, int value) {
        current.addTag(new IntTag(name).setValue(value));
        return this;
    }
    
    public CompoundTagBuilder<B> longTag(String name, long value) {
        current.addTag(new LongTag(name).setValue(value));
        return this;
    }
    
    public CompoundTagBuilder<B> shortTag(String name, short value) {
        current.addTag(new ShortTag(name).setValue(value));
        return this;
    }
    
    public CompoundTagBuilder<B> stringTag(String name, String value) {
        current.addTag(new StringTag(name).setValue(value));
        return this;
    }
    
}
