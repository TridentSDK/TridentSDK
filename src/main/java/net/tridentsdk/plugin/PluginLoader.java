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
package net.tridentsdk.plugin;

import net.tridentsdk.docs.PossiblyThreadSafe;

import java.util.Map;

/**
 * Loads plugin classes
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 */
@PossiblyThreadSafe
public interface PluginLoader {
    /**
     * Links all of the class dependencies
     *
     * @param c the class to link
     */
    void link(Class<?> c);

    /**
     * Creates a new Java class object for the class and the class source given
     *
     * @param name   the class name
     * @param source the class bytecode
     * @return the class representing the bytes given
     */
    Class<?> defineClass(String name, byte[] source);

    /**
     * Lists the class as loaded
     *
     * @param cls the class to list
     */
    void putClass(Class<?> cls);

    /**
     * Obtains the classes loaded by this plugin loader
     *
     * @return the class mapped to the class name, class
     */
    Map<String, Class<?>> loadedClasses();

    /**
     * Unloads the classes that were loaded from this class loader
     */
    void unloadClasses();
}
