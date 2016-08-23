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

import net.tridentsdk.util.Misc;

import javax.annotation.concurrent.Immutable;

/**
 * This class contains the set of all the possible level
 * types that a world can generate as.
 *
 * @author TridentSDK
 * @since 0.3-alpha-DP
 */
@Immutable
public enum LevelType {
    /**
     * The default level type
     *
     * <p>NBT Value: {@code default}</p>
     */
    DEFAULT("default"),
    /**
     * Generates a world using the default flat generator
     *
     * <p>NBT Value: {@code flat}</p>
     */
    FLAT("flat"),
    /**
     * Generates a world with enlarged biomes
     *
     * <p>NBT Value: {@code largeBiomes}</p>
     */
    LARGE_BIOMES("largeBiomes"),
    /**
     * Generates a world with amplified hills and valleys
     *
     * <p>NBT Value: {@code amplified}</p>
     */
    AMPLIFIED("amplified"),
    /**
     * Generates a world to test the world types and data
     *
     * <p>NBT Value: {@code default_1_1}</p>
     */
    DEBUG("default_1_1"); // wtf?

    /**
     * This is the NBT value for the level type
     */
    private final String s;

    /**
     * Creates a new level type using the given string as
     * its raw NBT value.
     *
     * @param s the NBT value
     */
    LevelType(String s) {
        this.s = s;
    }

    /**
     * Obtains the level type which uses the given string as
     * its NBT format, ignoring case.
     *
     * @param s the level type's NBT string form
     * @return the level type, if found
     * @throws IllegalArgumentException if the level type is
     *         not found
     */
    public static LevelType from(String s) {
        for (LevelType type : values()) {
            if (type.toString().equalsIgnoreCase(s)) return type;
        }

        throw new IllegalArgumentException(String.format(Misc.NBT_BOUND_FAIL, "n.t.w.o.LevelType"));
    }

    /**
     * This method obtains the level type as the NBT string
     * value.
     *
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.s;
    }
}