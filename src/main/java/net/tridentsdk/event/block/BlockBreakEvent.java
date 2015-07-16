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

package net.tridentsdk.event.block;

import net.tridentsdk.base.Block;
import net.tridentsdk.base.BlockOrientation;
import net.tridentsdk.entity.living.Player;
import net.tridentsdk.event.Cancellable;
import net.tridentsdk.window.inventory.Item;

/**
 * Called whenever a Block is broken
 *
 * @author The TridentSDK Team
 */
public class BlockBreakEvent extends BlockEvent implements Cancellable {
    private final Player player;
    private final BlockOrientation blockFace;
    private final Item itemInHand;
    private boolean cancelled;

    /**
     * @param player     Player associated with this event
     * @param block      Block associated with this event
     * @param blockFace  BlockFace
     * @param itemInHand ItemStack
     */
    public BlockBreakEvent(Player player, Block block, BlockOrientation blockFace, Item itemInHand) {
        super(block);
        this.player = player;
        this.blockFace = blockFace;
        this.itemInHand = itemInHand;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    /**
     * Returns the item in the player's hand
     *
     * @return ItemStack in the player's hand
     */
    public Item getItemInHand() {
        return this.itemInHand;
    }

    /**
     * Returns the block face clicked to break this block
     *
     * @return BlockFace of the clicked block
     */
    public BlockOrientation getClickedFace() {
        return this.blockFace;
    }

    /**
     * Get the player associated with this event
     *
     * @return Player associated with this event
     */
    public Player getPlayer() {
        return this.player;
    }
}
