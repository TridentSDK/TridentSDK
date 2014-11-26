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
package net.tridentsdk.api.factory;

import com.esotericsoftware.reflectasm.ConstructorAccess;
import com.esotericsoftware.reflectasm.MethodAccess;
import net.tridentsdk.api.reflect.FastClass;
import net.tridentsdk.api.reflect.FastConstructor;
import net.tridentsdk.api.reflect.FastField;
import net.tridentsdk.api.reflect.FastMethod;

/**
 * Provides reflection accessors to the ReflectASM library wrappers
 *
 * @author The TridentSDK Team
 */
public class ReflectFactory {
    /**
     * Acquires a class wrapper. Note this method is extremely slow.
     *
     * @param cls the default class to wrap
     * @return the wrapped class
     */
    public FastClass getClass(Class<?> cls) {
        return FastClass.get(cls);
    }

    /**
     * Acquires a class wrapper. Note this method is extremely slow.
     *
     * @param o the instance representing the class you need
     * @return the wrapped class
     */
    public FastClass getClass(Object o) {
        return FastClass.get(o);
    }

    /**
     * Acquires a wrapper for faster reflective manipulation
     *
     * @param o the instance to get the field from
     * @param name the name of the declared field
     * @return the wrapped field
     */
    public FastField getField(Object o, String name) {
        FastClass fastClass = FastClass.get(o.getClass());
        return fastClass.getField(name);
    }

    /**
     * Acquires a field wrapper in the same fashion as {@link #getMethod(Object, String)}, but for {@code static} fields
     *
     * @param cls the class to acquire the field from
     * @param name the name of the {@code static} field
     * @return the wrapped {@code static} field
     */
    public FastField getField(Class<?> cls, String name) {
        return FastClass.get(cls).getField(name);
    }

    /**
     * Acquires a method wrapper that enables faster reflective invocation
     *
     * @param o the instance used to invoke the method
     * @param name the method name
     * @return the wrapped method
     */
    public FastMethod getMethod(Object o, String name) {
        return new FastMethod(o, MethodAccess.get(o.getClass()), name);
    }

    /**
     * Acquires a method in the same fashion as {@link #getMethod(Object, String)}, but for {@code static} methods
     *
     * @param cls the class to get the method from
     * @param name the name of the method
     * @return the wrapped {@code static} method
     */
    public FastMethod getMethod(Class<?> cls, String name) {
        return new FastMethod(null, MethodAccess.get(cls), name);
    }

    /**
     * Acquires the wrapped DEFAULT constructor of the given class
     *
     * <p>Only a no-arg constructor can be wrapped</p>
     *
     * @param cls the class to get the constructor from
     * @return the wrapped default class constructor
     */
    public FastConstructor getConstruct(Class<?> cls) {
        return new FastConstructor(ConstructorAccess.get(cls));
    }
}
