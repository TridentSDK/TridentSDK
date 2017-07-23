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
package net.tridentsdk.command.constraint;

import com.esotericsoftware.reflectasm.MethodAccess;

import javax.annotation.concurrent.NotThreadSafe;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import net.tridentsdk.command.Command;
import net.tridentsdk.command.CommandDispatcher;
import net.tridentsdk.command.CommandListener;
import net.tridentsdk.command.CommandSource;

/**
 * A command dispatcher functions as the method wrapper for
 * command handling classes.
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
@NotThreadSafe
public class ConstraintCommandDispatcher extends CommandDispatcher {
    private static final Map<Class<? extends Constraint>, Constraint> constraints = new HashMap<>();

    private final MethodAccess access;
    private final Object container;
    private final int idx;
    @Getter private final String plugin;
    @Getter private final Command command;
    private final ConstraintsAnnotations.Constrain[] constrains;

    /**
     * Creates a new command dispatcher with the given
     * command properties.
     * @param access the method accessor for the class
     * @param container the container object
     * @param method the method to invoke
     * @param plugin the plugin name
     * @param command the command properties
     * @param constraints the dispatch constraints
     */
    public ConstraintCommandDispatcher(MethodAccess access, Object container, Method method, String plugin, Command command, ConstraintsAnnotations.Constrain... constraints) {
        this.access = access;
        this.container = container;
        this.idx = access.getIndex(method.getName(), method.getParameterTypes());
        this.plugin = plugin;
        this.command = command;
        this.constrains = constraints;
    }

    /**
     * Runs the command handled by the method wrapped by
     * this command source.
     *
     * @param cmd the command to run
     * @param source the command sender
     * @param args the command arguments
     */
    public void run(String cmd, CommandSource source, String[] args) {
        for (ConstraintsAnnotations.Constrain constrain : this.constrains) {
            Constraint constraint = constraints.computeIfAbsent(constrain.value(), k -> {
                try {
                    return k.getConstructor().newInstance();
                } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            });

            Object o = null;
            if (constrain.type() == ConstraintType.NONE) {
                o = null;
            } else if (constrain.type() == ConstraintType.INT) {
                o = constrain.integer();
            } else if (constrain.type() == ConstraintType.STRING) {
                o = constrain.str();
            } else if (constrain.type() == ConstraintType.SOURCE) {
                o = constrain.src();
            }

            if (!constraint.handle(this.command, cmd, source, args, o)) {
                return;
            }
        }

        this.access.invoke(this.container, this.idx, cmd, source, args);
    }

    /**
     * Obtains whether or not the given class is what this
     * command is contained by.
     *
     * @param cls the class to check
     * @return {@code true} if the class is the class which
     * contains the command
     */
    public boolean isContainedBy(Class<? extends CommandListener> cls) {
        return this.container.getClass().equals(cls);
    }
}
