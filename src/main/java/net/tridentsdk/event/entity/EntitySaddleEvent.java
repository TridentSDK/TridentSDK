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

package net.tridentsdk.event.entity;

import net.tridentsdk.entity.Entity;
import net.tridentsdk.entity.living.Player;

/**
 * Called whenever a player saddles a pig
 *
 * @author The TridentSDK Team
 */
public class EntitySaddleEvent extends EntityEvent {
	
    private final Player player;
    private boolean cancelled;

    public EntitySaddleEvent(Entity entity, Player player) {
        super(entity);
        this.player = player;
    }

    /**
     * Gets the player that saddled this pig
     */
    public Player getPlayer() {
        return this.player;
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
