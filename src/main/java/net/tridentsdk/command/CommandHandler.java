/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2017 The TridentSDK Team
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
package net.tridentsdk.command;

import com.esotericsoftware.reflectasm.MethodAccess;
import java.lang.reflect.Parameter;
import lombok.Getter;
import net.tridentsdk.command.constraint.ConstraintCommandDispatcher;
import net.tridentsdk.command.constraint.ConstraintsAnnotations;
import net.tridentsdk.command.params.ParamsAnnotations;
import net.tridentsdk.command.params.ParamsCommandDispatcher;
import net.tridentsdk.doc.Policy;
import net.tridentsdk.logger.Logger;

import javax.annotation.concurrent.NotThreadSafe;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import net.tridentsdk.ui.chat.ChatColor;
import net.tridentsdk.ui.chat.ChatComponent;

/**
 * A command handler is a registry for command listeners,
 * which in turn are notified of dispatched commands from
 * the server and passed to their respective listeners.
 *
 * <p>Methods in this class may only be called from the
 * plugin thread.</p>
 *
 * @author TridentSDK
 * @since 0.3-alpha-DP
 */
@NotThreadSafe
public class CommandHandler {
    /**
     * Initializer state which is set to 1 to indicate that
     * it was first used by and only by the server
     */
    private static final AtomicInteger init = new AtomicInteger();
    /**
     * Command method parameter order
     */
    private static final Class<?>[] PARAMS = { String.class, CommandSource.class, String[].class };
    /**
     * Hashtable of command names to their respective
     * runner
     */
    private final Map<String, CommandDispatcher> dispatchers = new HashMap<>();
    /**
     * The amount of non-alias commands present
     */
    @Getter
    private int cmdCount;

    /**
     * Creates a new command handler. Cannot be used by
     * plugins.
     */
    public CommandHandler() {
        if (!init.compareAndSet(0, 1)) {
            throw new IllegalStateException("Use Server#getCmdHandler()");
        }
    }

    /**
     * Registers the given instance of the command listener
     * to the registry.
     *
     * <p>Command names are always registered as lowercase.
     * </p>
     *
     * @param fallback the fallback name if already taken
     * @param listener the listener to register
     */
    @Policy("handle on plugin thread")
    public void register(String fallback, CommandListener listener) {
        Class<? extends CommandListener> cls = listener.getClass();
        MethodAccess access = MethodAccess.get(cls);
        Method[] methods = cls.getDeclaredMethods();

        for (Method method : methods) {
            Command cmd = method.getAnnotation(Command.class);
            if (cmd == null) {
                continue;
            }

            Class<?>[] parameterTypes = method.getParameterTypes();

            String methodSignature = getMethodSignature(method);

            String name = cmd.name().toLowerCase();
            if (!isValidCommandName(name)) {
                Logger.get(CommandHandler.class).error("Error registering command \"" + name + "\" from " + methodSignature + ": command may not have a space in it");
                continue;
            }

            if (this.dispatchers.containsKey(name)) {
                String newCmd = fallback + '$' + name;
                Logger.get(CommandHandler.class).warn("Error registering command \"" + name + "\" from " + methodSignature + ": command with that name already registered");
                Logger.get(CommandHandler.class).warn("Setting to: " + newCmd);
                name = newCmd;
            }

            CommandDispatcher dispatcher;
            if (Arrays.equals(parameterTypes, PARAMS)) {
                dispatcher = new ConstraintCommandDispatcher(access, listener, method, fallback, cmd, method.getAnnotationsByType(ConstraintsAnnotations.Constrain.class));
            } else if (parameterTypes.length >= 2 && parameterTypes[0] == CommandSource.class && parameterTypes[1] == String[].class) {
                try {
                    dispatcher = new ParamsCommandDispatcher(access, listener, method, fallback, cmd);
                } catch (Throwable ex) {
                    ex.printStackTrace();
                    Logger.get(CommandHandler.class).error("Error registering command \"" + name + "\" from " + methodSignature + ": " + ex.getMessage());
                    continue;
                }
            } else {
                Logger.get(CommandHandler.class).error("Error registering command \"" + name + "\" from " + methodSignature + ": does not match any expected method signature");
                continue;
            }

            for (String alias : cmd.aliases()) {
                if (!isValidCommandName(alias)) {
                    Logger.get(CommandHandler.class).error("Error registering command \"" + alias + "\" from " + methodSignature + ": command may not have a space in it");
                    continue;
                } else if (this.dispatchers.containsKey(alias)) {
                    String newCmd = fallback + '$' + alias;
                    Logger.get(CommandHandler.class).warn("Command with name \"" + alias + "\" from " + methodSignature + " already registered for alias");
                    Logger.get(CommandHandler.class).warn("Setting to: " + newCmd);
                    alias = newCmd;
                }

                this.dispatchers.put(alias, dispatcher);
            }
            this.dispatchers.put(name, dispatcher);
            this.cmdCount++;
        }
    }

    public static String getMethodSignature(Method method) {
        String methodSignature = method.getDeclaringClass().getName() + "#" + method.getName() + "(";
        boolean first = true;
        for (Class<?> param : method.getParameterTypes()) {
            if (!first)
                methodSignature += ", ";
            methodSignature += getClassName(param);
            first = false;
        }
        methodSignature += ")";
        return methodSignature;
    }

    private static String getClassName(Class<?> cls) {
        if (cls.isArray())
            return getClassName(cls.getComponentType()) + "[]";
        return cls.getSimpleName();
    }

    private static boolean isValidCommandName(String s) {
        for (char c : s.toCharArray()) {
            if (Character.isWhitespace(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Removes a command listener from the registry, thus
     * causing the handled command to respond as unknown.
     *
     * @param listener the listener to remove
     */
    @Policy("handle on plugin thread")
    public void unregister(Class<? extends CommandListener> listener) {
        boolean removed = this.dispatchers.entrySet().removeIf(e -> e.getValue().isContainedBy(listener));
        if (removed) {
            this.cmdCount--;
        }
    }

    /**
     * Dispatches the given command to its respective
     * listener.
     *
     * @param cmd the command string to dispatch, without a
     * "/"
     * @param source the source to use
     * @return {@code true} if the command was successfully
     * dispatched, {@code false} otherwise, or if there is
     * no such command
     */
    @Policy("handle on plugin thread")
    public boolean dispatch(String cmd, CommandSource source) {
        String[] split = cmd.split("\\s+");
        CommandDispatcher runner = this.dispatchers.get(split[0].toLowerCase());
        if (runner == null) {
            return false;
        }

        String[] args = new String[split.length - 1];
        if (args.length > 0) {
            System.arraycopy(split, 1, args, 0, args.length);
        }

        try {
            runner.run(split[0], source, args);
        } catch (Exception ex) {
            ex.printStackTrace();
            source.sendMessage(ChatComponent.create().setColor(ChatColor.RED).setText("An error occurred while executing the command."));
        }
        return true;
    }

    /**
     * Obtains an immutable copy of the dispatchers map.
     *
     * @return a copy of the dispatchers map
     */
    public Map<String, CommandDispatcher> getDispatchers() {
        return Collections.unmodifiableMap(this.dispatchers);
    }
}
