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

import net.tridentsdk.Coordinates;
import net.tridentsdk.entity.living.Player;
import net.tridentsdk.event.Cancellable;

/**
 * Called when a player moves their location
 *
 * @author The TridentSDK Team
 */
public class PlayerMoveEvent extends PlayerEvent implements Cancellable {
    private final Coordinates fromLoc;
    private final Coordinates toLoc;
    private boolean cancelled;

    public PlayerMoveEvent(Player player, Coordinates from, Coordinates to) {
        super(player);
        this.fromLoc = from;
        this.toLoc = to;
    }

    /**
     * Return previous location
     *
     * @return returns the previous player location
     */

    public Coordinates getFromLocation() {
        return this.fromLoc;
    }

    /**
     * Return next location
     *
     * @return returns the next player location
     */

    public Coordinates getToLocation() {
        return this.toLoc;
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
