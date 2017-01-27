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
package net.tridentsdk.base;

import lombok.Getter;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

/**
 * Represents the different colors of Horses.
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
@Immutable
public enum HorseColor {
    /**
     * No extra colour (brown).
     */
    NONE(0),

    /**
     * White.
     */
    WHITE(1),

    /**
     * White field.
     */
    WHITE_FIELD(2),

    /**
     * White dots.
     */
    WHITE_DOTS(3),

    /**
     * Black dots.
     */
    BLACK_DOTS(4);

    @Getter
    private final int data;

    HorseColor(int data) {
        this.data = data;
    }

    /**
     * Gets the horse color corresponding to the given internal identification number.
     * <br>
     * If none are found, an {@link IllegalArgumentException} will be thrown.
     *
     * @param id The identification number.
     *
     * @return The horse color.
     */
    @Nonnull
    public static HorseColor of(int id) {
        for (HorseColor color : values()) {
            if (color.data == id) {
                return color;
            }
        }

        throw new IllegalArgumentException("no horse color with id = " + id);
    }
}