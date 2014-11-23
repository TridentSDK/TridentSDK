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

<<<<<<< Updated upstream
    FastMethod(MethodAccess access, String name) {
=======
    @InternalUseOnly
    public FastMethod(Object instance, MethodAccess access, String name) {
>>>>>>> Stashed changes
        this.access = access;
        this.name = name;
    }

<<<<<<< Updated upstream
    public Object invoke(Object instance, Object... args) {
        return this.access.invoke(instance, this.name, args);
    }

    public Object invoke(Object instance) {
        return this.access.invoke(instance, this.name);
=======
    /**
     * Invokes the method with parameters
     *
     * @param args the method parameters
     * @return the return value after calling the method
     */
    public Object invoke(Object... args) {
        return this.access.invoke(this.instance, this.name, args);
    }

    /**
     * Invokes a no-arg method
     *
     * @return the return value after calling the method
     */
    public Object invoke() {
        return this.access.invoke(this.instance, this.name);
>>>>>>> Stashed changes
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
