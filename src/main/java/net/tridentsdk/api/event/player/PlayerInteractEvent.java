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
package net.tridentsdk.api.event.player;

import net.tridentsdk.api.Block;
import net.tridentsdk.api.entity.living.Player;
import net.tridentsdk.api.event.Cancellable;

/**
 * Called when a player interacts with something
 *
 * @author The TridentSDK Team
 */
public class PlayerInteractEvent extends PlayerEvent implements Cancellable {
    private boolean cancelled;

    private Block block;

    /**
     * TODO add action detection and blockface(?)
     *
     * @param player the player associated with the event
     * @param block  the block that was interacted with (null if none)
     */
    public PlayerInteractEvent(Player player, Block block) {
        super(player);
        this.setBlock(block);
    }

    /**
     * @return return the interacted block (null means no block was interacted with)
     */
    public Block getBlock() {
        return this.block;
    }

    /**
     * @param block the block that was interacted with
     */
    public void setBlock(Block block) {
        this.block = block;
    }

    @Override
    public boolean isIgnored() {
        return cancelled;
    }

    @Override
    public void cancel(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
