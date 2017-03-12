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

import javax.annotation.concurrent.Immutable;

/**
 * Represents the different types of Horse armor.
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
@Immutable
public enum HorseArmor {
    /**
     * Leather horse armor.
     */
    LEATHER(3),

    /**
     * Iron horse armor.
     */
    IRON(5),

    /**
     * Gold horse armor.
     */
    GOLD(7),

    /**
     * Diamond horse armor.
     */
    DIAMOND(11);

    @Getter
    private final int armor;

    HorseArmor(int armor) {
        this.armor = armor;
    }
}