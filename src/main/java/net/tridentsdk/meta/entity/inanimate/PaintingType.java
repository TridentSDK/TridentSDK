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
package net.tridentsdk.meta.entity.inanimate;

import lombok.Getter;

import javax.annotation.Nonnull;

/**
 * Represents a specific painting type.
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
public enum PaintingType {

    /**
     * The Kebab painting (1 block x 1 block)
     */
    KEBAB("Kebab"),

    /**
     * The Aztec painting (1 block x 1 block)
     */
    AZTEC("Aztec"),

    /**
     * The Alban painting (1 block x 1 block)
     */
    ALBAN("Alban"),

    /**
     * The Aztec2 painting (1 block x 1 block)
     */
    AZTEC2("Aztec2"),

    /**
     * The Bomb painting (1 block x 1 block)
     */
    BOMB("Bomb"),

    /**
     * The Plant painting (1 block x 1 block)
     */
    PLANT("Plant"),

    /**
     * The Wasteland painting (1 block x 1 block)
     */
    WASTELAND("Wasteland"),

    /**
     * The Wanderer painting (1 block x 2 blocks)
     */
    POOL("Pool"),

    /**
     * The Courbet painting (2 blocks x 1 block)
     */
    COURBET("Courbet"),

    /**
     * The Sea painting (2 blocks x 1 block)
     */
    SEA("Sea"),

    /**
     * The Sunset painting (2 blocks x 1 block)
     */
    SUNSET("Sunset"),

    /**
     * The Creebet painting (2 blocks x 1 block)
     */
    CREEBET("Creebet"),

    /**
     * The Wanderer painting (1 block x 2 blocks)
     */
    WANDERER("Wanderer"),

    /**
     * The Graham painting (1 block x 2 blocks)
     */
    GRAHAM("Graham"),

    /**
     * The Match painting (2 blocks x 2 blocks)
     */
    MATCH("Match"),

    /**
     * The Bust painting (2 blocks x 2 blocks)
     */
    BUST("Bust"),

    /**
     * The Stage painting (2 blocks x 2 blocks)
     */
    STAGE("Stage"),

    /**
     * The Void painting (2 blocks x 2 blocks)
     */
    VOID("Void"),

    /**
     * The Skull and Roses painting (2 blocks x 2 blocks)
     */
    SKULL_AND_ROSES("SkullAndRoses"),

    /**
     * The Wither painting (2 blocks x 2 blocks)
     */
    WITHER("Wither"),

    /**
     * The Fighers painting (4   blocks x 2 blocks)
     */
    FIGHTERS("Fighters"),

    /**
     * The Pointer painting (4 blocks x 4 blocks)
     */
    POINTER("Pointer"),

    /**
     * The Pig Scene painting (4 blocks x 4 blocks)
     */
    PIG_SCENE("Pigscene"),

    /**
     * The Burning Skull painting (4 blocks x 4 blocks)
     */
    BURNING_SKULL("BurningSkull"),

    /**
     * The Skeleton painting (4 blocks x 3 blocks)
     */
    SKELETON("Skeleton"),

    /**
     * The Donkey Kong painting (4 blocks x 3 blocks)
     */
    DONKEY_KONG("DonkeyKong");

    @Getter
    private final String data;

    PaintingType(String data) {
        this.data = data;
    }

    /**
     * Gets the painting type corresponding to the given internal identification number.
     * <br>
     * If none are found, an {@link IllegalArgumentException} will be thrown.
     *
     * @param name The painting name.
     *
     * @return The painting type.
     */
    @Nonnull
    public static PaintingType of(String name   ) {
        for (PaintingType type : values()) {
            if (type.data.equalsIgnoreCase(name)) {
                return type;
            }
        }

        throw new IllegalArgumentException("no painting type with name = " + name);
    }

}
