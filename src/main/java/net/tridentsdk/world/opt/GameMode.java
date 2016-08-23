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
 * The set of game types that may be applied to a world in
 * order to enforce upon players.
 *
 * @author TridentSDK
 * @since 0.3-alpha-DP
 */
@Immutable
public enum GameMode {
    /**
     * Survival mode
     *
     * <p>NBT value: {@code 0}</p>
     */
    SURVIVAL(0),
    /**
     * Creative mode
     *
     * <p>NBT value: {@code 1}</p>
     */
    CREATIVE(1),
    /**
     * Adventure mode
     *
     * <p>NBT value: {@code 2}</p>
     */
    ADVENTURE(2),
    /**
     * Spectator mode
     *
     * <p>NBT value: {@code 3}</p>
     */
    SPECTATOR(3);

    /**
     * The NBT value associated with the constructed game
     * mode.
     *
     * <p>Even though the actual NBT tag type is an
     * {@code int}, we use a byte here in order to save
     * a tiny amount of memory.</p>
     */
    private final byte b;

    /**
     * Creates a new game mode with the NBT value
     * represented as an {@code int} for conciseness.
     *
     * @param i the NBT value for the game mode
     */
    GameMode(int i) {
        this.b = (byte) i;
    }

    /**
     * Obtains the NBT value of the given game mode as it
     * is held in this class.
     *
     * @return the NBT value as {@code byte}
     */
    public byte asByte() {
        return this.b;
    }

    /**
     * Obtains the NBT value of the given game mode
     * converted to the actual NBT type.
     *
     * @return the NBT value as {@code int}
     */
    public int asInt() {
        return this.b;
    }

    /**
     * Obtains the game mode from the NBT value specified
     *
     * @param i the NBT value
     * @return the game mode, if found
     * @throws IndexOutOfBoundsException if there is no game
     *         mode for the given NBT value
     */
    @Nonnull
    public static GameMode from(int i) {
        for (GameMode mode : values()) {
            if (mode.b == i) return mode;
        }

        throw new IndexOutOfBoundsException(String.format(Misc.NBT_BOUND_FAIL, "n.t.w.o.GameMode"));
    }
}