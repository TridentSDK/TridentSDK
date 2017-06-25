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
import lombok.Getter;
import net.tridentsdk.doc.Policy;
import net.tridentsdk.logger.Logger;

import javax.annotation.concurrent.NotThreadSafe;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

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
public class CmdHandler {
    /**
     * Initializer state which is set to 1 to indicate that
     * it was first used by and only by the server
     */
    private static final AtomicInteger init = new AtomicInteger();
    /**
     * Command method parameter order
     */
    private static final Class<?>[] PARAMS = { String.class, CmdSource.class, String[].class };

    /**
     * Hashtable of command names to their respective
     * runner
     */
    private final Map<String, CmdDispatcher> dispatchers = new HashMap<>();
    /**
     * The amount of non-alias commands present
     */
    @Getter
    private int cmdCount;

    /**
     * Creates a new command handler. Cannot be used by
     * plugins.
     */
    public CmdHandler() {
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
     * @param listener the listener to register
     */
    @Policy("handle on plugin thread")
    public void register(String fallback, CmdListener listener) {
        Class<? extends CmdListener> cls = listener.getClass();
        MethodAccess access = MethodAccess.get(cls);
        Method[] methods = cls.getDeclaredMethods();

        for (Method m : methods) {
            Cmd annotation = m.getAnnotation(Cmd.class);
            if (annotation == null) {
                continue;
            }

            String name = annotation.name().toLowerCase();
            if (!Arrays.equals(m.getParameterTypes(), PARAMS)) {
                Logger.get(CmdHandler.class).error("Error registering command \"" + name + "\" in " +
                                cls.getName() + ": method must have params (String, CmdSender, String[])");
                continue;
            }

            if (name.contains(" ")) {
                Logger.get(CmdHandler.class).error("Error registering command \"" + name + "\" in " +
                                cls.getName() + ": command may not have a space in it");
                continue;
            }

            if (this.dispatchers.containsKey(name)) {
                String newCmd = fallback + '$' + name;
                Logger.get(CmdHandler.class).warn("Command with name \"" + name + "\" in " + cls.getName() + " already registered");
                Logger.get(CmdHandler.class).warn("Setting to: " + newCmd);
                name = newCmd;
            }

            Alias aliases = m.getAnnotation(Alias.class);
            if (aliases != null) {
                for (String a : aliases.value()) {
                    if (this.dispatchers.containsKey(a)) {
                        String newCmd = fallback + '$' + a;
                        Logger.get(CmdHandler.class).warn("Command with name \"" + a + "\" in " + cls.getName() + " already registered for alias");
                        Logger.get(CmdHandler.class).warn("Setting to: " + newCmd);
                        a = newCmd;
                    }

                    this.dispatchers.put(a, new CmdDispatcher(access, listener, m,
                            fallback, true, aliases.value(),
                            annotation, m.getAnnotationsByType(Constrain.class)));
                }
            }

            this.dispatchers.put(name, new CmdDispatcher(access, listener, m,
                    fallback, false, aliases == null ? new String[0] : aliases.value(),
                    annotation, m.getAnnotationsByType(Constrain.class)));
            this.cmdCount++;
        }
    }

    /**
     * Removes a command listener from the registry, thus
     * causing the handled command to respond as unknown.
     *
     * @param listener the listener to remove
     */
    @Policy("handle on plugin thread")
    public void unregister(Class<? extends CmdListener> listener) {
        this.dispatchers.entrySet().removeIf(e -> {
            boolean by = e.getValue().isContainedBy(listener);
            if (by && !e.getValue().isAlias()) {
                this.cmdCount--;
            }

            return by;
        });
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
    public boolean dispatch(String cmd, CmdSource source) {
        String[] split = cmd.split(" ");
        CmdDispatcher runner = this.dispatchers.get(split[0].toLowerCase());
        if (runner == null) {
            return false;
        }

        String[] args = new String[split.length - 1];
        if (args.length > 0) {
            System.arraycopy(split, 1, args, 0, args.length);
        }

        runner.run(split[0], source, args);
        return true;
    }

    /**
     * Obtains an immutable copy of the dispatchers map.
     *
     * @return a copy of the dispatchers map
     */
    public Map<String, CmdDispatcher> getDispatchers() {
        return Collections.unmodifiableMap(this.dispatchers);
    }
}