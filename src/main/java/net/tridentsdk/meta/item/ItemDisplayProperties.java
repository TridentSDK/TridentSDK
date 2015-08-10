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
package net.tridentsdk.meta.item;

import net.tridentsdk.meta.nbt.NBTSerializable;

import java.util.Arrays;
import java.util.List;

/**
 * Represents the display properties of an Item
 */
public interface ItemDisplayProperties extends NBTSerializable {
    /**
     * Returns display name of the item, {@code null} if non-existent
     * @return display name of the item
     */
    String displayName();

    /**
     * Sets the display name
     * @param displayName Display name you wish to set the item to
     */
    void setDisplayName(String displayName);

    /**
     * Returns lore of the item, {@code null} if non-existent
     * @return lore of the item
     */
    List<String> lore();

    /**
     * Sets the lore of the item
     * @param lore Lore you wish to set the item to
     */
    void setLore(List<String> lore);

    /**
     * Set the lore of the item
     * @param lore Lore you wish to set it to
     */
    default void setLore(String... lore) {
        setLore(Arrays.asList(lore));
    }
}
