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

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author The TridentSDK Team
 * @since 0.3-alpha-DP
 */
public class CompoundTag extends NBTTag implements TagContainer {
    // Will work for now
    final Map<String, NBTTag> tags = Collections.synchronizedMap(new LinkedHashMap<>()); //Hashmap for quick lookup with names

    public CompoundTag(String name) {
        super(name);
    }

    public List<NBTTag> listTags() {
        return Lists.newArrayList(this.tags.values());
    }

    public boolean containsTag(String name) {
        return this.tags.containsKey(name);
    }

    public NBTTag getTag(String name) {
        return this.tags.containsKey(name) ? this.tags.get(name) : new NullTag(name);
    }

    public <T extends NBTTag> T getTagAs(String name) {
        return (T) getTag(name);
    }

    @Override
    public void addTag(NBTTag tag) {
        this.tags.put(tag.name(), tag);
    }

    public void removeTag(String name) {
        this.tags.remove(name);
    }

    public void clearTags() {
        this.tags.clear();
    }

    /* (non-Javadoc)
     * @see net.tridentsdk.meta.nbt.NBTTag#type()
     */
    @Override
    public TagType type() {
        return TagType.COMPOUND;
    }
}
