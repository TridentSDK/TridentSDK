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

package net.tridentsdk.inventory;

import javax.annotation.concurrent.ThreadSafe;

/**
 * Represents an inventory or inventory that is opened to players
 *
 * @author The TridentSDK Team
 * @since 0.3-alpha-DP
 */
@ThreadSafe
public interface Inventory {
    /**
     * Obtains the ID of the inventory
     *
     * @return the inventory ID of this inventory
     */
    int id();

    /**
     * The available slots (not the slots taken up) in this inventory
     *
     * @return the inventory slots available
     */
    int length();

    /**
     * Finds the item at the specified slot in the inventory
     *
     * @param slot the slot to find the item
     * @return the item at that slot
     */
    Item itemAt(int slot);

    /**
     * Sets the item at the slot. If the index is outside the bound, the effects are unspecified.
     *
     * @param index the index which to set the item at
     * @param value the item to set at the index
     */
    void setSlot(int index, Item value);

    /**
     * Places an item into the player's inventory, where there is room
     *
     * @param item the item to place
     * @return True if managed to place an item, false if failed
     */
    boolean putItem(Item item);

    /**
     * The title of the inventory
     *
     * @return the name displayed at the top of the inventory
     */
    String name();

    /**
     * Obtains the contents of the inventory represented by this inventory
     *
     * @return the items in the inventory
     */
    Item[] items();
}
