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
import net.tridentsdk.event.Cancellable;

/**
 * Called when a block's redstone state is updated, called on each individual section of wire when they change, etc.
 *
 * @author The TridentSDK Team
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
     * @return Block which caused the redstone update
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

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
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
