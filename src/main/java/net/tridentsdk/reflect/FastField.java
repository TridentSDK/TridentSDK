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

/*
 * @NotJavaDoc
 * NOTE: This class only applies to any field which is not private
 */
public abstract class FastField {
    private final String field;
    private final Class<?> owner;

    public FastField(Class<?> owner, String field) {
        this.field = field;
        this.owner = owner;
    }

    public String field() {
        return field;
    }

    public abstract void set(Object instance, Object value);

    public abstract <T> T get(Object instance);

    public Field toField() {
        try {
            return owner.getDeclaredField(this.field);
        } catch (NoSuchFieldException ignored) {}
        return null;
    }
}
