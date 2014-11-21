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
package net.tridentsdk.plugin.cmd;

import net.tridentsdk.api.entity.living.Player;

public abstract class Command {

    /**
     * Called when this cmd is invoked by a player
     *
     * @param player player executing the command
     * @param arguments may be null
     * @param alias the alias of the command
     */
    public void handlePlayer(Player player, String arguments, String alias) {
        // Method intentionally left blank
    }

    /**
     * Called when this cmd is invoked by the console
     *
     * @param sender the command sender
     * @param arguments may be null
     * @param alias the alias for the command
     */
    public void handleConsole(ConsoleSender sender, String arguments, String alias) {
        // Method intentionally left blank
    }

    /**
     * Called when this cmd is invoked by a player, console, or other sender
     *
     * @param sender the command sender
     * @param arguments may be null
     * @param alias the command alias
     */
    public void handle(CommandIssuer sender, String arguments, String alias) {
        // Method intentionally left blank
    }

    /**
     * Called if this cmd is overriden by another
     */
    public void notifyOverriden() {
        // Method intentionally left blank

    }

}
