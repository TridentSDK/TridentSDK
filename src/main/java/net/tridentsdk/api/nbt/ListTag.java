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

import com.google.common.collect.Lists;

import java.util.*;

/**
 * @author The TridentSDK Team
 */
public class ListTag extends NBTTag implements TagContainer {
    final Collection<NBTTag> tags = new ArrayList<>();
    final TagType innerType;

    public ListTag(String name, TagType innerType) {
        super(name);
        this.innerType = innerType;
    }

    public List<NBTTag> listTags() {
        return Lists.newArrayList(this.tags);
    }

    public void clearTags() {
        this.tags.clear();
    }

    public boolean containsTag(NBTTag tag) {
        return this.tags.contains(tag);
    }

    @Override
    public void addTag(NBTTag tag) {
        if (tag.getType() == this.innerType) {
            this.tags.add(tag);
        }
    }

    public void removeTag(NBTTag tag) {
        this.tags.remove(tag);
    }

    public TagType getInnerType() {
        return this.innerType;
    }

    /* (non-Javadoc)
     * @see net.tridentsdk.api.nbt.NBTTag#getType()
     */
    @Override
    public TagType getType() {
        // TODO Auto-generated method stub
        return TagType.LIST;
    }
}
