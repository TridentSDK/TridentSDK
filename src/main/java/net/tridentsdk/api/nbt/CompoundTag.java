/*
 * Copyright (C) 2014 The TridentSDK Team
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.tridentsdk.api.nbt;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

/**
 * @author The TridentSDK Team
 */
public class CompoundTag extends NBTTag {
    Map<String, NBTTag> tags = new HashMap<>(); //Hashmap for quick lookup with names
    
    public List<NBTTag> listTags() {
        return Lists.newArrayList(tags.values());
    }
    
    public NBTTag getTag(String name) {
        return tags.containsKey(name) ? tags.get(name) : new NullTag();
    }
    
    public void addTag(NBTTag tag) {
        tags.put(tag.getName(), tag);
    }
    
    public void removeTag(String name) {
        tags.remove(name);
    }
    
    /* (non-Javadoc)
     * @see net.tridentsdk.api.nbt.NBTTag#getType()
     */
    @Override
    public TagType getType() {
        return TagType.COMPOUND_TAG;
    }

}
