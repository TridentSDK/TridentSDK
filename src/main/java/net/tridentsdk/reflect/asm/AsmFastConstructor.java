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

package net.tridentsdk.reflect.asm;

import com.esotericsoftware.reflectasm.ConstructorAccess;

import net.tridentsdk.reflect.FastConstructor;

/**
 * Instance creator using ASM
 *
 * @author The TridentSDK Team
 * @since 0.3-alpha-DP
 */
public class AsmFastConstructor extends FastConstructor {
    private final ConstructorAccess access;

    /**
     * Creates a new instance accessor to assemble bytecode for fast class
     * creation
     *
     * @param access
     *            the underlying access to the construction ASM facilities
     */
    public AsmFastConstructor(ConstructorAccess access) {
        this.access = access;
    }

    /**
     * Creates a new instance of the constructor's class
     *
     * @param <T>
     *            the class type
     * @return the instance of T
     */
    @Override
    public <T> T newInstance(Object... params) {
        return access == null ? null : (T) this.access.newInstance();
    }
}
