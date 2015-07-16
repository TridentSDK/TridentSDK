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

import net.tridentsdk.entity.living.Player;
import net.tridentsdk.event.Event;

/**
 * Called when an event relating to a player occurs
 *
 * <p>This is an umbrella event, do not listen to it</p>
 *
 * @author The TridentSDK Team
 */
public abstract class PlayerEvent extends Event {
	
    private final Player player;

    /**
     * @param player the player associated with the event
     */
    public PlayerEvent(Player player) {
        this.player = player;
    }

    /**
     * @return return the player associated with the event
     */
    public final Player player() {
        return this.player;
    }
    
}
