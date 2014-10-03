/*
 * Copyright (c) 2014, The TridentSDK Team
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     1. Redistributions of source code must retain the above copyright
 *        notice, this list of conditions and the following disclaimer.
 *     2. Redistributions in binary form must reproduce the above copyright
 *        notice, this list of conditions and the following disclaimer in the
 *        documentation and/or other materials provided with the distribution.
 *     3. Neither the name of the The TridentSDK Team nor the
 *        names of its contributors may be used to endorse or promote products
 *        derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL The TridentSDK Team BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package net.tridentsdk.api.event.block;

import net.tridentsdk.api.Block;
import net.tridentsdk.api.BlockFace;
import net.tridentsdk.api.Material;
import net.tridentsdk.api.entity.living.Player;
import net.tridentsdk.api.event.Cancellable;

/**
 * Called whenever a block is placed
 */
public class BlockPlaceEvent extends BlockEvent implements Cancellable {
    private final Player player;
    private final Block blockClicked;
    private final BlockFace faceClicked;

    private boolean cancel;

    /**
     * @param player Player who placed this block
     * @param block Block that was placed
     * @param blockClicked Block
     * @param faceClicked BlockFace
     */
    public BlockPlaceEvent(Player player, Block block, Block blockClicked, BlockFace faceClicked) {
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
    public BlockFace getFaceClicked() {
        return faceClicked;
    }

    /**
     * Get the Material of the placed block
     *
     * @return Material of the placed block
     */
    public Material getTypePlaced() {
        return getBlock().getMaterial();
    }

    /**
     * Return if the event is cancelled
     *
     * @return true if cancelled
     */
    @Override
    public boolean isCancelled() {
        return cancel;
    }

    /**
     * Set if the event is cancelled
     *
     * @param cancel Boolean cancellation state of event
     */
    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    /**
     * Gets the block clicked on to place this block
     *
     * @return Block that was clicked
     */
    public Block getBlockClicked() {
        return blockClicked;
    }

    /**
     * Returns the player associated with this event
     *
     * @return Player who placed the block
     */
    public Player getPlayer() {
        return this.player;
    }
}
