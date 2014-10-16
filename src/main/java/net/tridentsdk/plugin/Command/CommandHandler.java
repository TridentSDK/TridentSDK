package net.tridentsdk.plugin.Command;

import net.tridentsdk.api.CommandIssuer;
import net.tridentsdk.api.ConsoleSender;
import net.tridentsdk.api.entity.living.Player;
import net.tridentsdk.plugin.PluginLoadException;
import net.tridentsdk.plugin.TridentPlugin;
import net.tridentsdk.plugin.annotation.CommandDescription;
import net.tridentsdk.plugin.annotation.SubCommandDescription;
import org.apache.commons.lang.ArrayUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

            if(contents.length != 1) {
                if(command.hasSubCommand(contents[1])) {
                    command.invokeSubCommand(contents[1],args.substring(contents[1].length()),issuer,
                            contents[0] + " " + contents[1]);
                    return;
                } else {
                    invokeCommand(issuer, contents[0],command,args);
                    return;
                }
            } else {
                invokeCommand(issuer, contents[0], command, args);
                return;
            }
        }

        for(Map.Entry<String,CommandData> entry: commands.entrySet()) {
            if(entry.getValue().hasAlias(label)) {
                String args = message.substring(label.length());
                CommandData command = entry.getValue();
                if(contents.length != 1) {
                    if(command.hasSubCommand(contents[1])) {
                        command.invokeSubCommand(contents[1],args.substring(contents[1].length()),issuer,
                                contents[0] + " " + contents[1]);
                        return;
                    } else {
                        invokeCommand(issuer, contents[0],command,args);
                        return;
                    }
                } else {
                    invokeCommand(issuer, contents[0], command, args);
                    return;
                }
            }
        }
    }

    private void invokeCommand(CommandIssuer issuer, String content, CommandData command, String args) {
        if(issuer instanceof Player) {
            command.getCommand().handlePlayer(
                    (Player) issuer,args, content);
        } else if (issuer instanceof ConsoleSender) {
            command.getCommand().handleConsole(
                    (ConsoleSender) issuer, args, content);
        }
        command.getCommand().handle(issuer, args, content);
    }

    public int addCommand (Command command, TridentPlugin plugin) throws PluginLoadException {

        CommandDescription description = command.getClass().getAnnotation(CommandDescription.class);

        if(description == null) {
            throw new PluginLoadException("Error in registering commands: Class does not have annotation " +
                    "\"CommandDescription\"!");
        }

        String name = description.name();
        int priority = description.priority();
        String [] aliases = description.aliases();
        String permission = description.permission();
        Class<? extends SubCommandHandler> [] subCommands = description.subCommands();
        
        if(name == null || name.equals("")) {
            throw new PluginLoadException("Command does not declare a valid name!");
        }
        CommandData data = new CommandData(name, priority, aliases, permission, command);
        if(commands.containsKey(name.toLowerCase())) {
            if(commands.get(name.toLowerCase()).getPriority() > priority) {
                // put the new, more important command in place and notify the old command that it has been overriden
                commands.put(name.toLowerCase(),data)
                        .getCommand().notifyOverriden();
            }
            else {
                // don't register this command and notify it has been overriden
                command.notifyOverriden();
                return -1;
            }
        }

        if(subCommands.length != 0) {
            for(Class<? extends SubCommandHandler> subCommand: subCommands) {
                SubCommandHandler subCommandClass;
                try {
                    subCommandClass = subCommand.getConstructor().newInstance();
                } catch (InstantiationException e) {
                    throw new PluginLoadException("SubCommandHandler is not a concrete class, and cannot be instantiated!");
                } catch (IllegalAccessException e) {
                    throw new PluginLoadException("SubCommandHandler does not have a publicly visible, default constructor");
                } catch (InvocationTargetException e) {
                   throw new PluginLoadException(e);
                } catch (NoSuchMethodException e) {
                    throw new PluginLoadException("SubCommandHandler does not have default constructor!");
                }
                subCommandClass.owner = plugin;
                Method [] methods = subCommand.getMethods();
                for(Method method: methods) {
                    SubCommandDescription annotation = method.getAnnotation(SubCommandDescription.class);
                    if(annotation != null) {
                        String subCommandName = annotation.name();
                        String subCommandPermission = annotation.permission();
                        Class<? extends CommandIssuer> [] targets = annotation.targets();

                        if(subCommandName == null || subCommandName.equals("")) {
                            throw new PluginLoadException("Subcommand does not have a valid name!");
                        }
                        if(data.hasSubCommand(subCommandName)) {
                            throw new PluginLoadException("SubCommand has name of another subcommand!");
                        }

                        Class<?>[] params = method.getParameterTypes();
                        if(params.length != 3) {
                            throw new PluginLoadException("Subcommand does not have correct method signature");
                        }
                        if(!params[0].isAssignableFrom(CommandIssuer.class) ||
                                !params[1].equals(String.class) ||
                                !params[2].equals(String.class)) {
                            throw new PluginLoadException("Subcommand does not have correct method signature");
                        }
                        if(!method.isAccessible()) {
                            throw new PluginLoadException("Subcommand method is not visible!");
                        }
                        data.addSubCommand(new SubCommandData(subCommandPermission,subCommandName.toLowerCase(),targets,
                                method, subCommandClass));
                    }
                }

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
        private HashMap<String,SubCommandData> subCommands = new HashMap<>();

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

        public void addSubCommand(SubCommandData data) {
            subCommands.put(data.getName(), data);
        }

        public boolean hasSubCommand(String name) {
            return subCommands.containsKey(name.toLowerCase());
        }

        public void invokeSubCommand(String name, String args, CommandIssuer issuer, String alias) {
            SubCommandData data = subCommands.get(name.toLowerCase());
            if (ArrayUtils.contains(data.getTargets(), CommandIssuer.class)) {
                try {
                    data.getMethod().invoke(data.getHandler(),issuer,args,alias);
                } catch (IllegalAccessException e) {
                    throw new PluginLoadException("Subcommand method is not public!");
                } catch (InvocationTargetException e) {
                    throw new PluginLoadException("Subcommand does not have correct argument parameters");
                }
            } else {
                for(Class<?> clazz: data.getTargets()) {
                    if(issuer.getClass().isAssignableFrom(clazz)) {
                        try {
                            data.getMethod().invoke(data.getHandler(),issuer,args,alias);
                        } catch (IllegalAccessException e) {
                            throw new PluginLoadException("Subcommand method is not public!");
                        } catch (InvocationTargetException e) {
                            throw new PluginLoadException("Subcommand does not have correct argument parameters");
                        }
                    }
                }
            }
        }
    }

    private class SubCommandData {
        private String permission;
        private String name;
        private Class<? extends CommandIssuer>[] targets;
        private SubCommandHandler handler;
        private Method encapsulated;

        private SubCommandData(String permission, String name, Class<? extends CommandIssuer>[] targets,
                               Method encapsulated, SubCommandHandler handler) {
            this.permission = permission;
            this.name = name;
            this.targets = targets;
            this.encapsulated = encapsulated;
            this.handler = handler;
        }

        public String getName() {
            return name;
        }

        public Class<? extends CommandIssuer>[] getTargets() {
            return targets;
        }

        public SubCommandHandler getHandler() {
            return handler;
        }

        public Method getMethod() {
            return encapsulated;
        }
    }
}
