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
import net.tridentsdk.api.event.Cancellable;

/**
 * Called when a block's redstone state is updated, called on each individual section of wire when they change, etc.
 */
public class BlockRedstoneEvent extends BlockEvent implements Cancellable {

    private final int strength;
    private final Block causer;
    private final Cause cause;
    private boolean cancelled;

    /**
     * @param block    Block which redstone state was updated
     * @param strength Integer representing the strength (power level) of the redstone
     * @param causer   Block which caused the redstone update
     * @param cause    Cause for the redstone update
     */
    public BlockRedstoneEvent(Block block, int strength, Block causer, Cause cause) {
        super(block);
        this.strength = strength;
        this.causer = causer;
        this.cause = cause;
    }

    /**
     * Returns the block which caused the redstone update
     *
     * @return Block which caused the redstone updaye
     */
    public Block getCauser() {
        return this.causer;
    }

    /**
     * Returns the cause of the redstone update
     *
     * @return Cause of the redstone update
     */
    public Cause getCause() {
        return this.cause;
    }

    /**
     * Returns the strength (power level) of the redstone
     *
     * @return Integer representing the power level of the redstone
     */
    public int getStrength() {
        return this.strength;
    }

    /**
     * Return if the event is cancelled
     *
     * @return true if cancelled
     */
    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    /**
     * Set if the event is cancelled
     *
     * @param cancel Boolean cancellation state of event
     */
    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    /**
     * Representing the cause of a redstone update
     */
    public enum Cause {
        LEVER,
        BUTTON,
        WIRE,
        TORCH,
        PRESSURE_PLATE,
        HOOK,
        TRAP_CHEST,
        SENSOR,
        REPEATER,
        COMPARATOR
    }
}
