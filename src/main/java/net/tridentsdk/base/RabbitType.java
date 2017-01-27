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
 * Represents the different types of Rabbits.
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
@Immutable
public enum RabbitType {
    /**
     * A brown rabbit.
     */
    BROWN(0),

    /**
     * A white rabbit.
     */
    WHITE(1),

    /**
     * A black rabbit.
     */
    BLACK(2),

    /**
     * A black and white rabbit.
     */
    BLACK_AND_WHITE(3),

    /**
     * A golden rabbit.
     */
    GOLD(4),

    /**
     * A salt and pepper rabbit.
     */
    SALT_AND_PEPPER(5),

    /**
     * A killer bunny.
     */
    KILLER_BUNNY(99);

    @Getter
    private final int data;

    RabbitType(int data) {
        this.data = data;
    }

    /**
     * Gets the rabbit type corresponding to the given internal identification number.
     * <br>
     * If none are found, an {@link IllegalArgumentException} will be thrown.
     *
     * @param id The identification number.
     *
     * @return The rabbit type.
     */
    @Nonnull
    public static RabbitType of(int id) {
        for (RabbitType type : values()) {
            if (type.data == id) {
                return type;
            }
        }

        throw new IllegalArgumentException("no rabbit type with id = " + id);
    }
}