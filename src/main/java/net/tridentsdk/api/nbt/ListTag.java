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

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * @author The TridentSDK Team
 */
public class ListTag<V extends NBTTag> extends NBTTag {
    List<V> tags = new ArrayList<>();
    
    public List<V> listTags() {
        return Lists.newArrayList(tags);
    }
    
    public void clearTags() {
        tags.clear();
    }
    
    public void addTag(V tag) {
        tags.add(tag);
    }
    
    public void removeTag(V tag) {
        tags.remove(tag);
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
