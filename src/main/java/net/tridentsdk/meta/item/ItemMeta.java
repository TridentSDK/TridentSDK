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

import java.util.Set;

import net.tridentsdk.meta.nbt.NBTSerializable;

/**
 * This type represents additional metadata attached to an Item.
 */
public interface ItemMeta extends NBTSerializable {

    public static enum HiddenModifierFlag {

        /**
         * Flag representing enchantments being hidden.
         */
        ENCHANTMENTS,
        /**
         * Flag representing attributes being hidden.
         */
        ATTRIBUTES,
        /**
         * Flag representing the 'Unbreakable' NBT Tag on items with Durability.
         */
        UNBREAKABLE,
        /**
         * Flag representing the 'CanDestroy' NBT Tag on tools.
         */
        CAN_DESTROY,
        /**
         * Flag representing the 'CanPlaceOn' NBT Tag on blocks.
         */
        CAN_PLACE_ON,
        /**
         * Flag representing any other NBT values (future Minecraft versions).
         */
        OTHER;

        public int modifier() {
            return (int) Math.pow(2, this.ordinal());
        }

    }

    /**
     * Gets the display flags of this item. If one is in the set, it is hidden
     * from view in the client.
     *
     * @return A Set containing flags.
     */
    Set<HiddenModifierFlag> flags();

    /**
     * Sets the display value of a specified flag.
     *
     * @param flag
     *            The flag to be set.
     * @param shown
     *            If true, the client can see the flag's value, otherwise it is
     *            hidden.
     */
    void setFlag(HiddenModifierFlag flag, boolean shown);

    /**
     * Gets the display properties of this item
     *
     * @return Display properties of this item
     */
    ItemDisplayProperties displayProperties();

    /**
     * Sets the display properties of this item
     *
     * @param properties
     *            the new display properties
     */
    void setDisplayProperties(ItemDisplayProperties properties);
}
