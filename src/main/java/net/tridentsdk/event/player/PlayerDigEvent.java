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

package net.tridentsdk.event.player;

import net.tridentsdk.base.BlockOrientation;
import net.tridentsdk.entity.living.Player;
import net.tridentsdk.event.Cancellable;

/**
 * Occurs when a player damages a block and causes it to begin breaking.
 *
 * @author The TridentSDK Team
 */
public class PlayerDigEvent extends PlayerEvent implements Cancellable {
	
    private final BlockOrientation face;
    private final short status;

    private boolean cancelled;

    public PlayerDigEvent(Player player, BlockOrientation face, short status) {
        super(player);

        this.face = face;
        this.status = status;
    }

    public BlockOrientation getFace() {
        return this.face;
    }

    public short getStatus() {
        return this.status;
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
