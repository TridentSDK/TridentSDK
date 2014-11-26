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

import net.tridentsdk.api.entity.living.Player;
import net.tridentsdk.api.event.Ignorable;

public class PlayerChatEvent extends PlayerEvent implements Ignorable {
    private boolean cancel;

    private String message;

    /**
     * @param player  the player associated with this event
     * @param message the message sent by the player
     */

    public PlayerChatEvent(Player player, String message) {
        super(player);
        this.setMessage(message);
    }

    /**
     * @return return the message sent
     */

    public String getMessage() {
        return this.message;
    }

    /**
     * @param message the message associated with this event
     */

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean isIgnored() {
        return cancel;
    }

    @Override
    public void ignore(boolean cancel) {
        this.cancel = cancel;
    }
}
