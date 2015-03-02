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
import net.tridentsdk.base.Substance;
import net.tridentsdk.entity.living.Player;
import net.tridentsdk.event.Cancellable;

/**
 * Called whenever a block is placed
 *
 * @author The TridentSDK Team
 */
public class BlockPlaceEvent extends BlockEvent implements Cancellable {
    private final Player player;
    private final Block blockClicked;
    private final BlockOrientation faceClicked;

    private boolean cancelled;

    /**
     * @param player       Player who placed this block
     * @param block        Block that was placed
     * @param blockClicked Block
     * @param faceClicked  BlockFace
     */
    public BlockPlaceEvent(Player player, Block block, Block blockClicked, BlockOrientation faceClicked) {
        super(block);
        this.player = player;
        this.blockClicked = blockClicked;
        this.faceClicked = faceClicked;
    }

    /**
     * Gets the block face of the block that was clicked on to place this block
     *
     * @return BlockFace of the clicked block
     */
    public BlockOrientation faceClicked() {
        return this.faceClicked;
    }

    /**
     * Get the Material of the placed block
     *
     * @return Material of the placed block
     */
    public Substance typePlaced() {
        return this.block().substance();
    }

    @Override
    public boolean isIgnored() {
        return cancelled;
    }

    @Override
    public void cancel(boolean cancelled) {
        this.cancelled = cancelled;
    }

    /**
     * Gets the block clicked on to place this block
     *
     * @return Block that was clicked
     */
    public Block blockClicked() {
        return this.blockClicked;
    }

    /**
     * Returns the player associated with this event
     *
     * @return Player who placed the block
     */
    public Player player() {
        return this.player;
    }
}
