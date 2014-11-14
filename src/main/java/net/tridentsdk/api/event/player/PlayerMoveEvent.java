/*
 *     TridentSDK - A Minecraft Server API
 *     Copyright (C) 2014, The TridentSDK Team
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.tridentsdk.api.event.player;

import net.tridentsdk.api.Coordinates;
import net.tridentsdk.api.entity.living.Player;
import net.tridentsdk.api.event.CancelHandle;

public class PlayerMoveEvent extends PlayerEvent implements CancelHandle {

    private final Coordinates fromLoc;
    private final Coordinates toLoc;
    private boolean cancel;

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
    public boolean isCancelled() {
        return this.cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }
}
