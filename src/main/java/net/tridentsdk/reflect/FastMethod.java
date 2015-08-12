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

package net.tridentsdk.reflect;

import net.tridentsdk.docs.InternalUseOnly;

/**
 * Wrapper for the provided ReflectASM method library
 *
 * @author The TridentSDK Team
 * @since 0.3-alpha-DP
 */
public abstract class FastMethod {
    private final Class<?> owner;
    private final String name;
    private final Class<?>[] args;

    @InternalUseOnly
    public FastMethod(Class<?> owner, String name, Class<?>[] args) {
        this.owner = owner;
        this.name = name;
        this.args = args;
    }

    /**
     * Gets the method's name.
     *
     * @return The method's name.
     */
    public String name() {
        return name;
    }

    /**
     * Gets the object instance used by this class
     *
     * @return the instance for method invocation
     */
    public Class<?> owner() {
        return owner;
    }

    /**
     * Gets the method's args.
     *
     * @return The arguments.
     */
    public Class<?>[] args() {
        return args;
    }

    /**
     * Invokes the method.
     *
     * @param instance
     *            the instance of the class to use, to
     *            {@code null for static methods}
     * @param args
     *            The arguments to be used.
     * @return the return type of the method, or {@code null} for {@code void}
     *         methods
     */
    public abstract Object invoke(Object instance, Object... args);
}
