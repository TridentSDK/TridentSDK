package net.tridentsdk.plugin.Command;




import net.tridentsdk.api.CommandIssuer;
import net.tridentsdk.api.ConsoleSender;
import net.tridentsdk.api.entity.living.Player;
import net.tridentsdk.plugin.PluginLoadException;
import net.tridentsdk.plugin.annotation.CommandDescription;

import java.util.HashMap;
import java.util.Map;

public class CommandHandler {

    // TODO: Make this a dictionary tree for fast lookup
    private HashMap<String,CommandData> commands = new HashMap<>();

    /**
     * Handles the message sent from the player, without the preceding "/"
     * @param message
     */
    public void handleCommand (String message, CommandIssuer issuer) {
        if(message.length() == 0) {
            return;
        }

        String[] contents = message.split(" ");

        String label = contents[0].toLowerCase();

        if(commands.containsKey(label)) {
            CommandData command = commands.get(label);
            String args = message.substring(label.length());

            if(issuer instanceof Player) {
                command.getCommand().handlePlayer(
                        (Player) issuer,args, contents[0]);
            } else if (issuer instanceof ConsoleSender) {
                command.getCommand().handleConsole(
                        (ConsoleSender) issuer, args, contents[0]);
            }
            command.getCommand().handle(issuer, args, contents[0]);
        }

        for(Map.Entry<String,CommandData> entry: commands.entrySet()) {
            if(entry.getValue().hasAlias(label)) {
                CommandData command = entry.getValue();
                String args = message.substring(label.length());
                if(issuer instanceof Player) {
                    command.getCommand().handlePlayer(
                            (Player) issuer,args, contents[0]);
                } else if (issuer instanceof ConsoleSender) {
                    command.getCommand().handleConsole(
                            (ConsoleSender) issuer, args, contents[0]);
                }
                command.getCommand().handle(issuer, args, contents[0]);
            }
        }
    }

    public int addCommand (Command command) throws PluginLoadException {

        CommandDescription description = command.getClass().getAnnotation(CommandDescription.class);

        if(description == null) {
            throw new PluginLoadException("Error in registering commands: Class does not have annotation " +
                    "\"CommandDescription\"!");
        }

        String name = description.name();
        int priority = description.priority();
        String [] aliases = description.aliases();
        String permission = description.permission();
        
        if(name == null || name.equals("")) {
            throw new PluginLoadException("Command does not declare a valid name!");
        }
        
        if(commands.containsKey(name.toLowerCase())) {
            if(commands.get(name.toLowerCase()).getPriority() > priority) {
                // put the new, more important command in place and notify the old command that it has been overriden
                commands.put(name.toLowerCase(), new CommandData(name, priority, aliases, permission, command))
                        .getCommand().notifyOverriden();
            }
            else {
                // don't register this command and notify it has been overriden
                command.notifyOverriden();
            }
        }
        // TODO: return something meaningful
        return 0;
    }

    private class CommandData {
        private String permission;
        private int priority;
        private String[] aliases;
        private String name;
        private Command encapsulated;

        public CommandData(String name, int priority, String[] aliases, String permission, Command command) {
            this.priority = priority;
            this.name = name;
            this.aliases = aliases;
            this.permission = permission;
            this.encapsulated = command;
        }

        public Command getCommand() {
            return encapsulated;
        }

        public boolean hasAlias(String alias) {
            for(String string: aliases) {
                if(alias.equalsIgnoreCase(string)) {
                    return true;
                }
            }
            return false;
        }

        public int getPriority() {
            return priority;
        }
    }
}
