/*
 * Copyright (c) 2014, The TridentSDK Team
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     1. Redistributions of source code must retain the above copyright
 *        notice, this list of conditions and the following disclaimer.
 *     2. Redistributions in binary form must reproduce the above copyright
 *        notice, this list of conditions and the following disclaimer in the
 *        documentation and/or other materials provided with the distribution.
 *     3. Neither the name of the The TridentSDK Team nor the
 *        names of its contributors may be used to endorse or promote products
 *        derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL The TridentSDK Team BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package net.tridentsdk.api.reflect;

import com.esotericsoftware.reflectasm.*;

import java.lang.reflect.Field;

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

    public static FastClass get(Class<?> cls) {
        return new FastClass(cls);
    }

    public static FastClass get(Object obj) {
        return get(obj.getClass());
    }

    /**
     * Get a field from the class
     *
     * @param instance Instance of the class
     * @param name     Name of the field
     * @return FastField instance
     */
    public FastField getField(Object instance, String name) {
        return new FastField(instance, this.fieldAccess, name);
    }

    /**
     * Get a method from the class
     *
     * @param instance Instance of the class
     * @param name     Name of the method
     * @return FastMethod instance
     */
    public FastMethod getMethod(Object instance, String name) {
        return new FastMethod(instance, this.methodAccess, name);
    }

    public FastField[] getFields(Object instance) {
        Field[] fields = cls.getFields();
        FastField[] fastFields = new FastField[fields.length];

        for(int i = 0; i < fields.length; i += 1) {
            fastFields[i] = new FastField(instance, fieldAccess, fields[i].getName());
        }

        return fastFields;
    }

    public FastField[] getFields() {
        return getFields(null);
    }

    /**
     * Get the default constructor found
     *
     * @return the default FastConstructor
     */
    public FastConstructor getConstructor() {
        return new FastConstructor(this.constructorAccess);
    }

    public Class<?> toClass() {
        return this.cls;
    }
}
