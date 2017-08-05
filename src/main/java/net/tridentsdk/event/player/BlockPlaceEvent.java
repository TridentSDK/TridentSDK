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
package net.tridentsdk.event.player;

import lombok.Getter;
import lombok.Setter;
import net.tridentsdk.base.Block;
import net.tridentsdk.entity.living.EntityPlayer;
import net.tridentsdk.event.Cancellable;
import net.tridentsdk.inventory.Item;

import javax.annotation.concurrent.NotThreadSafe;

/**
 * This event is called whenever specifically a player puts
 * down a block.
 */
@NotThreadSafe
public class BlockPlaceEvent extends PlayerEvent implements Cancellable {
    /**
     * The previous block occupying the spot to place the
     * block
     */
    @Getter
    private final Block previous;
    /**
     * The item in the player's hand that will be placed
     */
    @Getter
    private final Item itemToPlace;

    @Setter
    @Getter
    private boolean cancelled;

    public BlockPlaceEvent(EntityPlayer player, Block previous, Item place) {
        super(player);
        this.previous = previous;
        this.itemToPlace = place;
    }
}