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
 * Represents a container that holds items and can be viewed or modified
 *
 * @author The TridentSDK Team
 */
public interface Window {
    /**
     * Obtains the window ID assigned by the server
     *
     * @return the window ID
     */
    int getId();

    /**
     * The items that are held by this window
     *
     * @return the items in the window
     */
    Item[] getItems();

    /**
     * The amount of slots in this inventory
     *
     * @return the slots in the inventory
     */
    int getLength();

    /**
     * The amount of items in the inventory
     *
     * @return the length of the non-null item array
     */
    int getItemLength();

    /**
     * Sets the item at the <a href="http://wiki.vg/Inventory">slot</a> in the inventory
     *
     * @param index the slot to set the item at
     * @param value the item to be set at the slot
     */
    void setSlot(int index, Item value);

    /**
     * Gets the inventory title
     *
     * @return the title of the inventory
     */
    String getName();
}
