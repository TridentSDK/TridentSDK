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

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the different types of Horse markings.
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
public enum HorseMarkings {

    /**
     * White markings.
     */
    WHITE(0),

    /**
     * Creamy markings.
     */
    CREAMY(1),

    /**
     * Chestnut markings.
     */
    CHESTNUT(2),

    /**
     * Brown markings.
     */
    BROWN(3),

    /**
     * Black markings.
     */
    BLACK(4),

    /**
     * Gray markings.
     */
    GRAY(5),

    /**
     * Dark brown markings.
     */
    DARK_BROWN(6);

    @Getter
    private final int data;

    HorseMarkings(int data) {
        this.data = data;
    }

    private static final Map<Integer, HorseMarkings> dataToMarkings = new HashMap<>();

    static {
        for (HorseMarkings markings : values()) {
            dataToMarkings.put(markings.data, markings);
        }
    }

    /**
     * Gets the horse markings corresponding to the given internal identification number.
     * <br>
     * If none are found, an {@link IllegalArgumentException} will be thrown.
     *
     * @param id The identification number.
     *
     * @return The horse markings.
     */
    public static HorseMarkings of(int id) {
        HorseMarkings markings = dataToMarkings.get(id);
        if (markings == null) {
            throw new IllegalArgumentException("no horse markings with id = " + id);
        }
        return markings;
    }

}

