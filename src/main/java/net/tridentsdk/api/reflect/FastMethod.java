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
package net.tridentsdk.api.reflect;

import com.esotericsoftware.reflectasm.MethodAccess;
import net.tridentsdk.api.docs.InternalUseOnly;

/**
 * Wrapper for the provided ReflectASM method library
 *
 * @author The TridentSDK Team
 */
public class FastMethod {
    private final MethodAccess access;
    private final String name;
    private final Object instance;

    @InternalUseOnly
    public FastMethod(Object instance, MethodAccess access, String name) {
        this.access = access;
        this.name = name;
        this.instance = instance;
    }

    /**
     * Invokes the method with parameters
     *
     * @param instance the instance of the class to use, to {@code null for static methods}
     * @param args the parameter values for the method
     * @return the return type of the method, or {@code null} for {@code void} methods
     */
    public Object invoke(Object instance, Object... args) {
        return this.access.invoke(instance, this.name, args);
    }

    /**
     * Invokes the method without parameters (no-arg)
     *
     * @param instance the instance of the class to use, to {@code null for static methods}
     * @return the return type of the method, or {@code null} for {@code void} methods
     */
    public Object invoke(Object instance) {
        return this.access.invoke(instance, this.name);
    }

    /**
     * Gets the object instance used by this class
     *
     * @return the instance for method invocation
     */
    public Object getInstance() {
        return instance;
    }
}
