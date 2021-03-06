/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2017 The TridentSDK Team
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
import net.tridentsdk.ui.chat.ChatComponent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

/**
 * Represents the collection of items that may be held in
 * the inventory of a container capable of holding items,
 * such as armor stands, players, entities, and chests.
 *
 * <p>Inventory slot numbers are usually dependent on the
 * type of inventory that is being used.</p>
 *
 * <p>Objects supporting an inventory typically use
 * {@code getInventory()} or {@code getInv()} to grab this
 * object. One can create an unattached inventory by using
 * {@link #newInventory(InventoryType, int)}</p>
 *
 * @author TridentSDK
 * @since 0.3-alpha-DP
 */
@ThreadSafe
public interface Inventory {
    /**
     * Creates a new free/unattached inventory that is not
     * associated with any particular inventory holder.
     *
     * @param type the inventory type to create
     * @param slots the amount of slots in the inventory
     * @return the new inventory
     */
    static Inventory newInventory(InventoryType type, int slots) {
        return Impl.get().newInventory(type, slots);
    }

    /**
     * Adds the given item to the inventory the specified
     * number of times to the next available inventory
     * slot.
     *
     * <p>This method returns whether or not the inventory
     * was full and thus could not add the item.</p>
     *
     * <p>The quantity acts independently of the item's
     * count, thus if the item had a count of 3 and the
     * quantity 4, then the amount of items added would be
     * 4.</p>
     *
     * @param item the item to add
     * @param quantity the quantity of that item to add,
     * numbers above the valid stack size are valid but will
     * take up more slots
     * @return {@code true} if the operation succeeded,
     * {@code false} if some or all of the items failed to
     * be added
     */
    boolean add(Item item, int quantity);

    /**
     * Adds an item the specified number of times to the
     * given inventory slot.
     *
     * <p>If there was a previous item at this inventory
     * slot that does not match the type/data of the new
     * item (i.e. it can't increment the stack count), then
     * that item will be returned.</p>
     *
     * <p>The quantity acts independently of the item's
     * count, thus if the item had a count of 3 and the
     * quantity 4, then the amount of items added would be
     * 4. A quantity exceeding the stack size for the given
     * item always complete successfully, only adding the
     * max stack size.</p>
     *
     * @param slot the slot which to add the item
     * @param item the item to add to the inventory
     * @param quantity the quantity of items to add
     * @return non-null value of the item after adding if it
     * completed successfully, otherwise {@code null} if it
     * failed
     */
    @Nullable
    Item add(int slot, Item item, int quantity);

    /**
     * Removes the specified quantity of items from the
     * specified slot in the inventory.
     *
     * <p>Having an a quantity above that existing in the
     * inventory is not invalid and will clear only that
     * slot successfully.</p>
     *
     * @param slot the slot to remove items
     * @param quantity the quantity of that item to remove
     * @return the item left over after applying this
     * operation, or
     * {@link net.tridentsdk.base.Substance#AIR} if none
     */
    @Nonnull
    Item remove(int slot, int quantity);

    /**
     * Obtains the item at the given slot in the inventory.
     *
     * @param slot the slot which to get the item from
     * @return the item, or
     * {@link net.tridentsdk.base.Substance#AIR} if none
     */
    @Nonnull
    Item get(int slot);

    /**
     * Obtains the size of this inventory.
     *
     * @return the slot size of the inventory
     */
    int getSize();

    /**
     * Obtains the type of inventory represented by this
     * particular instance.
     *
     * @return the inventory type
     */
    InventoryType getType();

    /**
     * Obtains the title of the inventory, which may use
     * chat formatting specifications to add effects.
     *
     * @return the inventory title
     */
    ChatComponent getTitle();

    /**
     * Sets the title of the inventory using the given chat
     * format specification.
     *
     * @param title the title which to set the inventory
     */
    void setTitle(ChatComponent title);
}