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
package net.tridentsdk.api.event.player;

import net.tridentsdk.api.entity.Item;
import net.tridentsdk.api.entity.LivingEntity;
import net.tridentsdk.api.entity.living.Player;
import net.tridentsdk.api.event.Cancellable;

/**
 * Called when a player shears an entity
 */
public class PlayerShearEntityEvent extends PlayerEvent implements Cancellable {
    private final LivingEntity sheared;
    private Item drop;
    private boolean cancelled;

    public PlayerShearEntityEvent(Player player, LivingEntity sheared, Item drop) {
        super(player);
        this.sheared = sheared;
        this.drop = drop;
    }

    /**
     * Gets the entity that was sheared
     */
    public LivingEntity getSheared() {
        return this.sheared;
    }

    /**
     * Gets the item that shearing this entity will drop
     */
    public Item getDrop() {
        return this.drop;
    }

    /**
     * Sets the item that shearing this entity will drop
     */
    public void setDrop(Item drop) {
        this.drop = drop;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
