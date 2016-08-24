/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2016 The TridentSDK Team
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
package net.tridentsdk.world.opt;

/**
 * The options for creating a new world.
 *
 * <p>These options will be transferred over to their
 * appropriate opt classes when the world has finished
 * generating.</p>
 *
 * @author TridentSDK
 * @since 0.4-alpha
 */
public class WorldCreateSpec {
    private static final WorldCreateSpec DEFAULT = new WorldCreateSpec(true);

    /**
     * Whether this spec uses the default world options
     */
    private final boolean def;

    // Use static factories
    private WorldCreateSpec(boolean def) {
        this.def = def;
    }

    /**
     * Uses the default world options to build the world.
     *
     * @return the default world specification
     */
    public static WorldCreateSpec defaultOpts() {
        return DEFAULT;
    }

    /**
     * Determines whether this option specification is uses
     * all default values or not.
     *
     * @return {@code true} for default values
     */
    public boolean isDefault() {
        return this.def;
    }
}