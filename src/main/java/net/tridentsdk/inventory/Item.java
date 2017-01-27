/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2016 The TridentSDK Team
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
package net.tridentsdk.inventory;

import net.tridentsdk.Impl;
import net.tridentsdk.base.Substance;
import net.tridentsdk.meta.ItemMeta;

import javax.annotation.concurrent.Immutable;

/**
 * Represents an item or a stack of items that is held in
 * the inventory.
 *
 * @author TridentSDK
 * @since 0.3-alpha-DP
 */
@Immutable
public interface Item {
    /**
     * Creates a new item with the given substance
     * parameter.
     *
     * @param substance the item's substance
     * @return the new item
     */
    static Item newItem(Substance substance) {
        return newItem(substance, 0);
    }

    /**
     * Creates a new item with the given substance
     * parameter.
     *
     * @param substance the item's substance
     * @return the new item
     */
    static Item newItem(Substance substance, int count) {
        return newItem(substance, count, (byte) 0);
    }

    /**
     * Creates a new item with the given substance
     * parameter.
     *
     * @param substance the item's substance
     * @return the new item
     */
    static Item newItem(Substance substance, int count, byte dmg) {
        return newItem(substance, count, dmg, null);
    }

    /**
     * Creates a new item with the given substance
     * parameter.
     *
     * @param substance the item's substance
     * @return the new item
     */
    static Item newItem(Substance substance, int count, byte damage, ItemMeta meta) {
        return Impl.get().newItem(substance, count, damage, meta);
    }

    /**
     * Obtains the substance type of the item.
     *
     * @return the item's substance
     */
    Substance getSubstance();

    /**
     * Obtains the amount of items that are in this stack.
     *
     * @return the item count
     */
    int getCount();

    /**
     * Obtains the 4-bit extra item data contained by this
     * item.
     *
     * @return the item damage
     */
    byte getDamage();

    /**
     * Obtains extra item metadata such as enchantments and
     * potion variations (if applicable).
     *
     * @return the item metadata
     */
    ItemMeta getMeta();
}