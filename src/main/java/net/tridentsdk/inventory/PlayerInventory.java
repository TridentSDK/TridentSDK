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

import javax.annotation.Nonnull;

/**
 * Represents an inventory that is held by a player.
 *
 * @author TridentSDK
 * @since 0.4-alpha
 */
public interface PlayerInventory extends Inventory {
    /**
     * Obtains the item in the player's main hand.
     *
     * @return the item in the main hand, or an
     * {@link net.tridentsdk.base.Substance#AIR} item if it
     * doesn't hold anything
     */
    @Nonnull
    Item getHeldItem();

    /**
     * Obtains the item in the player's off hand, or
     * otherwise the non-main hand.
     *
     * @return the item in the non-main hand, or an
     * {@link net.tridentsdk.base.Substance#AIR} item if it
     * doesn't hold anything
     */
    @Nonnull
    Item getOffHeldItem();

    /**
     * Returns a number 0-8 left to right depending on
     * whichever hotbar slot the player has selected.
     *
     * @return the selected hotbar slot
     */
    int getSelectedSlot();
}