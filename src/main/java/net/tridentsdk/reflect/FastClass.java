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

import com.esotericsoftware.reflectasm.ConstructorAccess;
import com.esotericsoftware.reflectasm.FieldAccess;
import com.esotericsoftware.reflectasm.MethodAccess;

import java.lang.reflect.Field;

/**
 * Accessors to the members of a class, does not actually have a ReflectASM equivalent
 *
 * @author The TridentSDK Team
 */
public class FastClass {
    private final Class<?> cls;

    private final FieldAccess fieldAccess;
    private final MethodAccess methodAccess;
    private final ConstructorAccess constructorAccess;

    private FastClass(Class<?> cls) {
        this.cls = cls;
        this.fieldAccess = FieldAccess.get(cls);
        this.methodAccess = MethodAccess.get(cls);
        this.constructorAccess = ConstructorAccess.get(cls);
    }

    /**
     * Creates a new FastClass from a Java class
     *
     * @param cls the class to use
     * @return the member accessors for the class
     */
    public static FastClass get(Class<?> cls) {
        return new FastClass(cls);
    }

    /**
     * Creates a new FastClass from the class object of the object
     *
     * @param obj the object's class to use
     * @return the member accessors for the class
     */
    public static FastClass get(Object obj) {
        return get(obj.getClass());
    }

    /**
     * Get a field from the class
     *
     * @param name Name of the field
     * @return FastField instance
     */
    public FastField fieldBy(String name) {
        return new FastField(this, this.fieldAccess, name);
    }

    /**
     * Get a method from the class
     *
     * @param name Name of the method
     * @return FastMethod instance
     */
    public FastMethod methodBy(Object object, String name) {
        return new FastMethod(object, this.methodAccess, name);
    }

    /**
     * Get all fields from the class
     *
     * @return the fast field representation of field members from the class
     */
    public FastField[] fields() {
        Field[] fields = this.cls.getDeclaredFields();
        FastField[] fastFields = new FastField[fields.length];

        for (int i = 0; i < fields.length; i += 1) {
            fastFields[i] = new FastField(this, this.fieldAccess, fields[i].getName());
        }

        return fastFields;
    }

    /**
     * Get the default constructor found
     *
     * @return the default FastConstructor
     */
    public FastConstructor constructor() {
        return new FastConstructor(this.constructorAccess);
    }

    /**
     * As the vanilla reflection class form
     *
     * @return the class that is used to derive all access from
     */
    public Class<?> asClass() {
        return this.cls;
    }
}
