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
package net.tridentsdk.meta.entity.living.animal;

import lombok.Getter;

import javax.annotation.Nonnull;

public enum ParrotType {

    /**
     * A red and blue parrot.
     */
    RED_BLUE(0),

    /**
     * A blue parrot.
     */
    BLUE(1),

    /**
     * A green parrot.
     */
    GREEN(2),

    /**
     * A yellow and blue parrot.
     */
    YELLOW_BLUE(3),

    /**
     * A silver parrot.
     */
    SILVER(4);

    @Getter
    private final int data;

    ParrotType(int data) {
        this.data = data;
    }

    /**
     * Gets the parrot type corresponding to the given internal identification number.
     * <br>
     * If none are found, an {@link IllegalArgumentException} will be thrown.
     *
     * @param id The identification number.
     *
     * @return The parrot type.
     */
    @Nonnull
    public static ParrotType of(int id) {
        for (ParrotType type : values()) {
            if (type.data == id) {
                return type;
            }
        }

        throw new IllegalArgumentException("no parrot type with id = " + id);
    }

}
