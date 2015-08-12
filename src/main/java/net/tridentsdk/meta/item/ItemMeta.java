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

import java.util.Map;
import java.util.Set;

import net.tridentsdk.base.Enchantment;
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
     * Gets a mapping of all enchantments to their levels applied to this item.
     *
     * @return The enchantments applied to this item.
     */
    Map<Enchantment, Short> enchantments();

    /**
     * Adds the specified enchantment to the item with a specific level.
     *
     * @param enchantment
     *            The enchantment.
     * @param level
     *            The level.
     *
     * @see #addSafeEnchantment(Enchantment, int)
     */
    void addEnchantment(Enchantment enchantment, int level);

    /**
     * <p>Attempts to safely add a specified enchantment to the item with a
     * specific level.<p>
     *
     * <p>An enchantment is considered to be 'safe' if it can be applied to an
     * item through an Enchanting Table.</p>
     *
     * @param enchantment
     *            The enchantment.
     * @param level
     *            The level.
     * @return True if and only if the enchantment was safely applied.
     *
     * @see #addEnchantment(Enchantment, int)
     */
    boolean addSafeEnchantment(Enchantment enchantment, int level);

    /**
     * Removes the specified enchantment from the item if it exists on it.
     *
     * @param enchantment
     *            The enchantment.
     */
    void removeEnchantment(Enchantment enchantment);

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
