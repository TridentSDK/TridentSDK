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
 * Represents the different types of Villagers.
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
public enum VillagerProfession {

    /**
     * A farmer villager.
     */
    FARMER(0),

    /**
     * A librarian villager.
     */
    LIBRARIAN(1),

    /**
     * A priest villager.
     */
    PRIEST(2),

    /**
     * A blacksmith villager.
     */
    BLACKSMITH(3),

    /**
     * A butcher villager.
     */
    BUTCHER(4);

    @Getter
    private final int data;

    VillagerProfession(int data) {
        this.data = data;
    }

    private static final Map<Integer, VillagerProfession> dataToType = new HashMap<>();

    /**
     * Gets the villager profession corresponding to the given internal identification number.
     * <br>
     * If none are found, an {@link IllegalArgumentException} will be thrown.
     *
     * @param id The identification number.
     *
     * @return The villager profession.
     */
    public static VillagerProfession of(int id) {
        VillagerProfession profession = dataToType.get(id);
        if (profession == null) {
            throw new IllegalArgumentException("no villager profession with id = " + id);
        }
        return profession;
    }

    static {
        for (VillagerProfession type : values()) {
            dataToType.put(type.data, type);
        }
    }

}
