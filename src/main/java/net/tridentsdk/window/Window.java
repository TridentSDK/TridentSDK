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

package net.tridentsdk.window;

import net.tridentsdk.window.inventory.Item;

/**
 * Represents an inventory or window that is opened to players
 *
 * @author The TridentSDK Team
 */
public interface Window {
    /**
     * Obtains the ID of the window
     *
     * @return the window ID of this window
     */
    int getWindowID();

    /**
     * The available slots (not the slots taken up) in this window
     *
     * @return the window slots available
     */
    int getSize();

    /**
     * Finds the item at the specified slot in the window
     *
     * @param slot the slot to find the item
     * @return the item at that slot
     */
    Item getItem(int slot);

    /**
     * Sets the item at the slot. If the index is outside the bound, the effects are unspecified.
     *
     * @param index the index which to set the item at
     * @param value the item to set at the index
     */
    void setItem(int index, Item value);

    /**
     * Places an item into the player's inventory, where there is room, or drops onto the ground if the inventory is
     * full
     *
     * @param item the item to place
     */
    void addItem(Item item);

    /**
     * The title of the inventory
     *
     * @return the name displayed at the top of the window
     */
    String getName();

    /**
     * Obtains the contents of the inventory represented by this window
     *
     * @return the items in the window
     */
    public Item[] getItems();
}
