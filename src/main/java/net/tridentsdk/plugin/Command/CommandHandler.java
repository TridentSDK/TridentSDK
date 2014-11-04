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
package net.tridentsdk.plugin.Command;


import net.tridentsdk.api.CommandIssuer;
import net.tridentsdk.api.ConsoleSender;
import net.tridentsdk.api.Messagable;
import net.tridentsdk.api.entity.living.Player;
import net.tridentsdk.plugin.PluginLoadException;
import net.tridentsdk.plugin.annotation.CommandDescription;

import java.util.HashMap;

/**
 * Handles all commands issued in Trident
 */
public class CommandHandler {
    // Contains all registered commands on the server.
    // And yes, a hash map is the best structure for this.
    private final HashMap<String, Command> commands = new HashMap<>();

    /**
     * Handles the message sent from the player, without the preceding "/"
     *
     * @param message the message inputed
     * @param issuer the method by which the message was delivered
     */
    public void handleCommand(String message, CommandIssuer issuer) {
        if (message.isEmpty()) {
            return;
        }

        String[] contents = message.split(" ");

        String label = contents[0].toLowerCase();

        // Check if the command exists
        if (commands.containsKey(label)) {
            // Grab reference to command
            Command command = commands.get(label);

            // Assemble command arguments
            String args = message.substring(label.length());

            // Check if player console, or other issued command
            if (issuer instanceof Player) {
                command.handlePlayer((Player) issuer, args, contents[0]);
            } else if (issuer instanceof ConsoleSender) {
                command.handleConsole((ConsoleSender) issuer, args, contents[0]);
            } else {
                command.handle(issuer, args, contents[0]);
            }
        } else if (issuer instanceof Messagable) {
            // Return an error
            ((Messagable) issuer).sendMessage("Invalid command");
        }
    }

    /**
     * Adds a command to the command structure
     * 
     * @param command the command to add
     * @throws PluginLoadException if the command lacks a description or valid name
     * @return 0: all is good with the world
     *         1: new command overrides another one
     *         2: new command is overridden by another
     */
    public int addCommand(Command command) throws PluginLoadException {

        // Grabs the command description
        CommandDescription description = command.getClass().getAnnotation(CommandDescription.class);

        // Check for a lack of description
        if (description == null) {
            throw new PluginLoadException("Error in registering commands: Class does not have annotation " +
                    "\"CommandDescription\"!");
        }

        // Readability reference variables
        String name = description.name().toLowerCase();
        int priority = description.priority();

        // Check for invalid name
        if (name == null || "".equals(name)) {
            throw new PluginLoadException("Command does not declare a valid name!");
        }

        // Check if there is already a command with that name
        if (commands.containsKey(name)) {
            // Check if the new command has priority over the old
            if (commands.get(name).getClass().getAnnotation(CommandDescription.class).priority() > priority) {
                
                // put the new, more important command in place and notify the old command that it has been overriden
                this.commands.put(name.toLowerCase(), command).notifyOverriden();
                return 1;
            } else {
                // don't register this command and notify it has been overriden
                command.notifyOverriden();
                return 2;
            }
        }
        return 0;
    }
}