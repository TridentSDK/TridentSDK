/*
 *     TridentSDK - A Minecraft Server API
 *     Copyright (C) 2014, The TridentSDK Team
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.tridentsdk.api.reflect;

import com.esotericsoftware.reflectasm.FieldAccess;

import java.lang.reflect.Field;

/*
 * @NotJavaDoc
 * NOTE: This class only applies to any field which is not private
 */
public class FastField {
    private final FieldAccess access;
    private final String field;
    private final FastClass owner;

    FastField(FastClass owner, FieldAccess access, String field) {
        this.access = access;
        this.field = field;
        this.owner = owner;
    }

    public void set(Object instance, Object value) {
        this.access.set(instance, this.field, value);
    }

    public <T> T get(Object instance) {
        return (T) this.access.get(instance, this.field);
    }

    public Field toField() {
        try {
            return owner.toClass().getDeclaredField(this.field);
        } catch (NoSuchFieldException ignored) {
        }

        return null;
    }
}
