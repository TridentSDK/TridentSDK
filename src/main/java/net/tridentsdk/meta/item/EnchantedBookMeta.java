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

import net.tridentsdk.base.Enchantment;

public interface EnchantedBookMeta {
    /**
     * Gets a mapping of all enchantments to their levels stored in this
     * Enchanted Book.
     *
     * @return The enchantments stored in this Enchanted Book.
     */
    Map<Enchantment, Short> storedEnchantments();

    /**
     * Adds the specified enchantment to the stored enchantment with a specific
     * level.
     *
     * @param enchantment
     *            The enchantment.
     * @param level
     *            The level.
     */
    void addStoredEnchantment(Enchantment enchantment, int level);

    /**
     * Removes the specified enchantment from the item if it exists on it.
     *
     * @param enchantment
     *            The enchantment.
     */
    void removeStoredEnchantment(Enchantment enchantment);

    /**
     * Gets this EnchantedBook's cost to apply in an anvil in experience levels.
     *
     * @return The cost to apply this enchanted book to an item.
     */
    int repairCost();
}
