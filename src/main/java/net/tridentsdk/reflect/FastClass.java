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

import java.lang.reflect.Field;

import com.esotericsoftware.reflectasm.ConstructorAccess;
import com.esotericsoftware.reflectasm.FieldAccess;
import com.esotericsoftware.reflectasm.MethodAccess;

import net.tridentsdk.reflect.asm.AsmFastConstructor;
import net.tridentsdk.reflect.asm.AsmFastField;
import net.tridentsdk.reflect.asm.AsmFastMethod;
import net.tridentsdk.reflect.java.JavaFastConstructor;
import net.tridentsdk.reflect.java.JavaFastField;
import net.tridentsdk.reflect.java.JavaFastMethod;

/**
 * Accessors to the members of a class, does not actually have a ReflectASM
 * equivalent
 *
 * @author The TridentSDK Team
 * @since 0.3-alpha-DP
 */
public class FastClass {
    private final Class<?> cls;
    private final FieldAccess fieldAccess;
    private final MethodAccess methodAccess;
    private final ConstructorAccess constructorAccess;

    private FastClass(Class<?> cls) {
        this.cls = cls;
        FieldAccess tempField = null;
        try {
            tempField = FieldAccess.get(cls);
        } catch (RuntimeException ex) {}
        this.fieldAccess = tempField;
        MethodAccess tempMethod = null;
        try {
            tempMethod = MethodAccess.get(cls);
        } catch (RuntimeException ex) {}
        this.methodAccess = tempMethod;
        ConstructorAccess tempConstr = null;
        try {
            tempConstr = ConstructorAccess.get(cls);
        } catch (RuntimeException ex) {}
        this.constructorAccess = tempConstr;
    }

    /**
     * Creates a new FastClass from a Java class
     *
     * @param cls
     *            the class to use
     * @return the member accessors for the class
     */
    public static FastClass get(Class<?> cls) {
        return new FastClass(cls);
    }

    /**
     * Creates a new FastClass from the class object of the object
     *
     * @param obj
     *            the object's class to use
     * @return the member accessors for the class
     */
    public static FastClass get(Object obj) {
        return get(obj.getClass());
    }

    /**
     * Get a field from the class
     *
     * @param name
     *            Name of the field
     * @return FastField instance
     */
    public FastField fieldBy(String name) {
        return this.fieldAccess == null || getFieldIndex(name) == -1 ? new JavaFastField(cls, name) : new AsmFastField(cls, name, this.fieldAccess);
    }

    private int getFieldIndex(String name) {
        try {
            return fieldAccess.getIndex(name);
        } catch (Exception ex) {
            return -1;
        }
    }

    /**
     * Get a method from the class.
     *
     * @param name
     *            Name of the method.
     * @return FastMethod instance
     */
    public FastMethod methodBy(String name, Class<?>... args) {
        return this.methodAccess == null || getMethodIndex(name, args) == -1 ? new JavaFastMethod(cls, name, args) : new AsmFastMethod(cls, name, args, this.methodAccess);
    }

    private int getMethodIndex(String name, Class<?>... args) {
        try {
            return methodAccess.getIndex(name, args);
        } catch (Exception ex) {
            return -1;
        }
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
            fastFields[i] = fieldBy(fields[i].getName());
        }

        return fastFields;
    }

    /**
     * Get the default constructor found
     *
     * @param args
     *            The constructor arguments. If any provided, it uses
     *            reflection.
     *
     * @return the default FastConstructor
     */
    public FastConstructor constructor(Class<?>... args) {
        return constructorAccess == null || args.length > 0 ? new JavaFastConstructor(cls, args) : new AsmFastConstructor(this.constructorAccess);
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
