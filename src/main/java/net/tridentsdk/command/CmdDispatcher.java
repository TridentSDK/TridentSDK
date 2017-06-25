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

import javax.annotation.concurrent.NotThreadSafe;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * A command dispatcher functions as the method wrapper for
 * command handling classes.
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
@NotThreadSafe
public class CmdDispatcher {
    /**
     * The cache of constraint types to their constraint
     * runner
     */
    private static final Map<Class<? extends Constraint>, Constraint> constraints = new HashMap<>();

    /**
     * Method accessor for the class to run the command
     */
    private final MethodAccess access;
    /**
     * The object which is used to run the instance method
     */
    private final Object container;
    /**
     * The method index
     */
    private final int idx;
    /**
     * The fallback string
     */
    @Getter
    private final String fallback;
    /**
     * Whether or not this dispatcher is an alias of the
     * actual command
     */
    @Getter
    private final boolean alias;
    /**
     * Aliases for this command
     */
    @Getter
    private final String[] aliases;
    /**
     * The command to run
     */
    @Getter
    private final Cmd cmd;
    /**
     * The command constraints
     */
    private final Constrain[] constrains;

    /**
     * Creates a new command dispatcher with the given
     * command properties.
     * @param access the method accessor for the class
     * @param container the container object
     * @param method the method to invoke
     * @param fallback the fallback string
     * @param alias the alias
     * @param aliases the aliases for the command
     * @param cmd the command properties
     * @param constraints the dispatch constraints
     */
    public CmdDispatcher(MethodAccess access, Object container, Method method, String fallback, boolean alias, String[] aliases, Cmd cmd, Constrain... constraints) {
        this.access = access;
        this.container = container;
        this.fallback = fallback;
        this.alias = alias;
        this.aliases = aliases;
        this.idx = access.getIndex(method.getName(), method.getParameterTypes());
        this.cmd = cmd;
        this.constrains = constraints;
    }

    /**
     * Runs the command handled by the method wrapped by
     * this command source.
     *
     * @param args the command arguments
     * @param source the command sender
     */
    public void run(String cmd, CmdSource source, String[] args) {
        for (Constrain constrain : this.constrains) {
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

            if (!constraint.handle(this.cmd, cmd, source, args, o)) {
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
    public boolean isContainedBy(Class<? extends CmdListener> cls) {
        return this.container.getClass().equals(cls);
    }
}