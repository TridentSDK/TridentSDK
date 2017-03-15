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
 * Represents the different types of Zombies.
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
@Immutable
public enum ZombieType {
    /**
     * A normal zombie.
     */
    NORMAL(0),

    /**
     * A zombie that looks like a villager of type {@link VillagerProfession#FARMER}.
     */
    VILLAGER_FARMER(1),

    /**
     * A zombie that looks like a villager of type {@link VillagerProfession#LIBRARIAN}.
     */
    VILLAGER_LIBRARIAN(2),

    /**
     * A zombie that looks like a villager of type {@link VillagerProfession#PRIEST}.
     */
    VILLAGER_PRIEST(3),

    /**
     * A zombie that looks like a villager of type {@link VillagerProfession#BLACKSMITH}.
     */
    VILLAGER_BLACKSMITH(4),

    /**
     * A zombie that looks like a villager of type {@link VillagerProfession#BUTCHER}.
     */
    VILLAGER_BUTCHER(5),

    /**
     * A husk Zombie.
     */
    HUSK(6);

    @Getter
    private final int data;

    @Getter
    private final boolean villager;

    ZombieType(int data) {
        this.data = data;
        this.villager = data >= 1 && data <= 5;
    }

    /**
     * Gets the zombie type corresponding to the given internal identification number.
     * <br>
     * If none are found, an {@link IllegalArgumentException} will be thrown.
     *
     * @param id The identification number.
     *
     * @return The zombie type.
     */
    @Nonnull
    public static ZombieType of(int id) {
        for (ZombieType type : values()) {
            if (type.data == id) {
                return type;
            }
        }

        throw new IllegalArgumentException("no zombie type with id = " + id);
    }
}