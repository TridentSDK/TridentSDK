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
package net.tridentsdk.plugin.cmd;


import net.tridentsdk.api.entity.living.Player;
import net.tridentsdk.plugin.PluginLoadException;
import net.tridentsdk.plugin.annotation.CommandDescription;

import java.util.HashMap;
import java.util.Map;

public final class CommandHandler {
    // TODO: Make this a dictionary tree for fast lookup
    private static final HashMap<String, CommandData> COMMANDS = new HashMap<>();

    /**
     * Handles the message sent from the player, without the preceding "/"
     *
     * @param message the command executed
     */
    public static void handleCommand(String message, CommandIssuer issuer) {
        if (message.isEmpty()) {
            return;
        }

        String[] contents = message.split(" ");

        String label = contents[0].toLowerCase();

        CommandData cmdData = COMMANDS.get(label);
        if (cmdData != null) {
            String args = message.substring(label.length());

            if (issuer instanceof Player) {
                cmdData.getCommand().handlePlayer(
                        (Player) issuer, args, contents[0]);
            } else if (issuer instanceof ConsoleSender) {
                cmdData.getCommand().handleConsole(
                        (ConsoleSender) issuer, args, contents[0]);
            }
            cmdData.getCommand().handle(issuer, args, contents[0]);
        }

        for (Map.Entry<String, CommandData> entry : COMMANDS.entrySet()) {
            if (entry.getValue().hasAlias(label)) {
                CommandData command = entry.getValue();
                String args = message.substring(label.length());
                if (issuer instanceof Player) {
                    command.getCommand().handlePlayer(
                            (Player) issuer, args, contents[0]);
                } else if (issuer instanceof ConsoleSender) {
                    command.getCommand().handleConsole(
                            (ConsoleSender) issuer, args, contents[0]);
                }
                command.getCommand().handle(issuer, args, contents[0]);
            }
        }
    }

    public static int addCommand(Command command) throws PluginLoadException {

        CommandDescription description = command.getClass().getAnnotation(CommandDescription.class);

        if (description == null) {
            throw new PluginLoadException("Error in registering commands: Class does not have annotation " +
                    "\"CommandDescription\"!");
        }

        String name = description.name();
        int priority = description.priority();
        String[] aliases = description.aliases();
        String permission = description.permission();

        if (name == null || "".equals(name)) {
            throw new PluginLoadException("cmd does not declare a valid name!");
        }

        if (COMMANDS.containsKey(name.toLowerCase())) {
            if (COMMANDS.get(name.toLowerCase()).getPriority() > priority) {
                // put the new, more important cmd in place and notify the old cmd that it has been overriden
                COMMANDS.put(name.toLowerCase(), new CommandData(name, priority, aliases, permission, command))
                        .getCommand().notifyOverriden();
            } else {
                // don't register this cmd and notify it has been overriden
                command.notifyOverriden();
            }
        }
        // TODO: return something meaningful
        return 0;
    }

    private static class CommandData {
        private final String permission;
        private final int priority;
        private final String[] aliases;
        private final String name;
        private final Command encapsulated;

        public CommandData(String name, int priority, String[] aliases, String permission, Command command) {
            this.priority = priority;
            this.name = name;
            this.aliases = aliases;
            this.permission = permission;
            this.encapsulated = command;
        }

        public Command getCommand() {
            return this.encapsulated;
        }

        public boolean hasAlias(String alias) {
            for (String string : this.aliases) {
                if (alias.equalsIgnoreCase(string)) {
                    return true;
                }
            }
            return false;
        }

        public int getPriority() {
            return this.priority;
        }
    }
}
