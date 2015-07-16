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

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import net.tridentsdk.Handler;
import net.tridentsdk.Trident;
import net.tridentsdk.entity.living.Player;
import net.tridentsdk.factory.Factories;
import net.tridentsdk.plugin.PluginLoadException;
import net.tridentsdk.plugin.TridentPlugin;
import net.tridentsdk.plugin.annotation.CommandDescription;
import net.tridentsdk.util.TridentLogger;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * Handles commands passed from the server
 *
 * <p>To access this handler, use this code:
 * <pre><code>
 *     CommandHandler handler = Handler.forCommands();
 * </code></pre></p>
 *
 * @author The TridentSDK Team
 */
public class CommandHandler {
    // TODO: Make this a dictionary tree for fast lookup
    private static final Map<String, CommandData> COMMANDS = Factories.collect().createMap();

    /**
     * Do not instantiate
     *
     * <p>To access this handler, use this code:
     * <pre><code>
     *     CommandHandler handler = Handler.forCommands();
     * </code></pre></p>
     */
    public CommandHandler() {
        if (!Trident.isTrident())
            TridentLogger.error(new IllegalAccessException("Only TridentSDK is allowed to make a new command handler"));
    }

    /**
     * Handles the message sent, without the preceding "/"
     *
     * @param message the command executed
     */
    public void handleCommand(final String message, final CommandIssuer issuer) {
        if (message.isEmpty()) {
            return;
        }

        final String[] contents = message.split(" ");
        final String label = contents[0].toLowerCase();
        final String args = message.substring(label.length());
        final Set<CommandData> cmdData = findCommand(label);

        if (!cmdData.isEmpty()) {
            Handler.forPlugins().executor().addTask(() -> {
                for (CommandData data : cmdData) {
                    handleCommand(data.getCommand(), issuer, args, contents);
                }
            });
        } else {
            // Command not found
            if(issuer instanceof Player) {
                ((Player) issuer).sendMessage("Command not found!");
            } else {
                issuer.sendRaw("Command not found");
            }
        }
    }

    private Set<CommandData> findCommand(String label) {
        Set<CommandData> dataSet = Sets.newHashSet();
        CommandData data = COMMANDS.get(label);

        if (data != null) {
            dataSet.add(data);
        }

        dataSet.addAll(COMMANDS.values().stream().filter(d -> d.hasAlias(label)).collect(Collectors.toList()));

        return dataSet;
    }

    private void handleCommand(Command cmd, CommandIssuer issuer, String args, String[] contents) {
        if (issuer instanceof Player)
            cmd.handlePlayer((Player) issuer, args, contents[0]);
        else if (issuer instanceof ServerConsole)
            cmd.handleConsole((ServerConsole) issuer, args, contents[0]);

        cmd.handle(issuer, args, contents[0]);
    }

    public int registerCommand(TridentPlugin plugin, Command command) throws
            PluginLoadException {
        CommandDescription description = command.getClass().getAnnotation(CommandDescription.class);

        if (description == null) {
            TridentLogger.error(new PluginLoadException(
                    "Error in registering commands: Class does not have annotation " + "\"CommandDescription\"!"));
            return 0;
        }

        String name = description.name();
        int priority = description.priority();
        String[] aliases = description.aliases();
        String permission = description.permission();

        if (name == null || "".equals(name)) {
            TridentLogger.error(new PluginLoadException("cmd does not declare a valid name!"));
            return 0;
        }

        String lowerCase = name.toLowerCase();
        CommandData data = COMMANDS.get(lowerCase);
        CommandData newData = new CommandData(name, priority, aliases, permission, command, plugin);

        if (data != null) {
            if (COMMANDS.get(lowerCase).getPriority() > priority) {
                // put the new, more important cmd in place and notify the old cmd that it has been overridden
                COMMANDS.put(lowerCase, newData).getCommand().notifyOverriden();
            } else {
                // don't register this cmd and notify it has been overridden
                command.notifyOverriden();
            }
        } else {
            COMMANDS.put(name, newData);
        }

        // TODO: return something meaningful
        return 0;
    }

    public void unregisterCommand(Class<? extends Command> cls) {
        COMMANDS.entrySet().stream().filter(e -> e.getValue().getCommand().getClass().equals(cls)).forEach(e -> COMMANDS.remove(e.getKey()));
    }

    public Map<Class<? extends Command>, Command> getCommands(TridentPlugin plugin) {
        Map<Class<? extends Command>, Command> map = Maps.newHashMap();
        COMMANDS.values().stream().filter(data -> data.getPlugin().equals(plugin)).forEach(data -> map.put(data.encapsulated.getClass(), data.encapsulated));
        return map;
    }

    private static class CommandData {
    	
        private final List<String> aliases;
        private final String permission;
        private final Command encapsulated;
        private final TridentPlugin plugin;
        private final int priority;

        public CommandData(String name, int priority, String[] aliases, String permission, Command command,
                TridentPlugin plugin) {
            this.aliases = Lists.asList(name, aliases);
            this.permission = permission;
            this.encapsulated = command;
            this.plugin = plugin;
            this.priority = priority;
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

        public TridentPlugin getPlugin() {
            return plugin;
        }
        
        public String getPermission() {
        	return permission;
        }
        
    }
    
}
