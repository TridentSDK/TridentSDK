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
import net.tridentsdk.api.Material;
import net.tridentsdk.api.Orientation;
import net.tridentsdk.api.event.Cancellable;

/**
 * Called whenever a piston extends or retracts
 */
public abstract class BlockPistonEvent extends BlockEvent implements Cancellable {
    private final Orientation direction;
    private final boolean retract;
    private final Block influenced;
    private boolean cancelled;

    public BlockPistonEvent(Block block, Orientation direction, boolean retract, Block influenced) {
        super(block);
        this.direction = direction;
        this.retract = retract;
        this.influenced = influenced;

    }

    /**
     * Returns the direction that the piston is facing, for example if the piston head face of a block is on the north
     * end of a block, then the Direction that this event returns will be north, does not change depending on whether
     * this piston is extending or retracting, so a block may actually be moving south if this is returns north
     *
     * @return
     */
    public Orientation getDirection() {
        return direction;
    }

    /**
     * Returns true if this piston is retracting
     *
     * @return
     */
    public boolean isRetracting() {
        return retract;
    }

    /**
     * Returns true if this piston is extending, convenience for !isRetracting()
     *
     * @return
     */
    public boolean isExtending() {
        return !retract;
    }

    /**
     * Gets the block that is being moved by this piston, if any
     *
     * <p>If this is a piston extend event, this will return the first block in the series of blocks
     * being pushed.</p>
     *
     * @return the block being moved, may be null if air, or retracting from a block without this piston being sticky
     */
    public Block getInfluencedBlock() {
        return influenced;
    }

    public boolean isSticky() {
        return getBlock().getType() == Material.PISTON_STICKY_BASE;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
