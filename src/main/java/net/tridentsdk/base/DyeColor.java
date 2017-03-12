/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2017 The TridentSDK Team
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
package net.tridentsdk.base;

import lombok.Getter;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

/**
 * Represents the different colors of Dye, Wool, and Sheep.
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
@Immutable
public enum DyeColor {
    /**
     * Black dye.
     */
    BLACK(0),

    /**
     * Red dye.
     */
    RED(1),

    /**
     * Dark green dye.
     */
    DARK_GREEN(2),

    /**
     * Brown dye.
     */
    BROWN(3),

    /**
     * Dark blue dye.
     */
    DARK_BLUE(4),

    /**
     * Dark purple dye.
     */
    DARK_PURPLE(5),

    /**
     * Cyan dye.
     */
    CYAN(6),

    /**
     * Light gray dye.
     */
    LIGHT_GRAY(7),

    /**
     * Dark gray dye.
     */
    DARK_GRAY(8),

    /**
     * Pink dye.
     */
    PINK(9),

    /**
     * Light green dye.
     */
    LIGHT_GREEN(10),

    /**
     * Yellow dye.
     */
    YELLOW(11),

    /**
     * Light blue dye.
     */
    LIGHT_BLUE(12),

    /**
     * Magenta dye.
     */
    MAGENTA(13),

    /**
     * Orange dye.
     */
    ORANGE(14),

    /**
     * White dye.
     */
    WHITE(15);

    @Getter
    private final int data;

    DyeColor(int data) {
        this.data = data;
    }


    /**
     * Gets the dye color corresponding to the given internal identification number.
     * <br>
     * If none are found, an {@link IllegalArgumentException} will be thrown.
     *
     * @param id The identification number.
     *
     * @return The dye color.
     */
    @Nonnull
    public static DyeColor of(int id) {
        for (DyeColor color : values()) {
            if (color.data == id) {
                return color;
            }
        }

        throw new IllegalArgumentException("no dye color with id = " + id);
    }
}