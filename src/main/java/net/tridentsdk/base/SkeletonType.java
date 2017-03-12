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
 * Represents the different types of Skeletons.
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
@Immutable
public enum SkeletonType {
    /**
     * A normal skeleton.
     */
    NORMAL(0),

    /**
     * A wither skeleton.
     */
    WITHER(1),

    /**
     * A stray skeleton.
     */
    STRAY(2);

    @Getter
    private final int data;

    SkeletonType(int data) {
        this.data = data;
    }

    /**
     * Gets the skeleton type corresponding to the given internal identification number.
     * <br>
     * If none are found, an {@link IllegalArgumentException} will be thrown.
     *
     * @param id The identification number.
     *
     * @return The skeleton type.
     */
    @Nonnull
    public static SkeletonType of(int id) {
        for (SkeletonType type : values()) {
            if (type.data == id) {
                return type;
            }
        }

        throw new IllegalArgumentException("no skeleton type with id = " + id);
    }
}