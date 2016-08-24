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

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

/**
 * This is a set of the possible difficulties that can be
 * applied to a world in order to determine the
 * aggressiveness of mob spawns.
 *
 * @author TridentSDK
 * @since 0.3-alpha-DP
 */
@Immutable
public enum Difficulty {
    /**
     * Peaceful
     *
     * <p>NBT value: {@code 0}</p>
     */
    PEACEFUL(0),
    /**
     * Easy
     *
     * <p>NBT value: {@code 1}</p>
     */
    EASY(1),
    /**
     * Normal
     *
     * <p>NBT value: {@code 2}</p>
     */
    NORMAL(2),
    /**
     * Hard
     *
     * <p>NBT value: {@code 3}</p>
     */
    HARD(3),
    /**
     * Hardcore
     *
     * <p>This is only here for convenience only, hardcore
     * does not have an NBT value, but rather uses the
     * {@code hardcore boolean} tag in level.dat</p>
     */
    HARDCORE(3);

    /** The NBT value of the current difficulty */
    private final byte b;

    /**
     * Creates a new difficulty given the NBT value
     *
     * @param i the NBT value
     */
    Difficulty(int i) {
        this.b = (byte) i;
    }

    /**
     * Obtains the NBT value of the given difficulty
     *
     * @return the {@code byte} form of the difficulty
     */
    public byte asByte() {
        return this.b;
    }

    /**
     * Obtains the difficulty given the NBT value
     *
     * @param i the NBT value of the difficulty to find
     * @return the difficulty, if found
     * @throws IndexOutOfBoundsException if it is not found
     */
    @Nonnull
    public static Difficulty from(int i) {
        for (Difficulty difficulty : values()) {
            if (difficulty.b == i) return difficulty;
        }

        throw new IllegalArgumentException(String.format(Misc.NBT_BOUND_FAIL, "n.t.w.o.Difficulty"));
    }
}