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
package net.tridentsdk.meta.entity.living.monster;

import lombok.Getter;

import javax.annotation.Nonnull;

public enum EvokerSpellType {

    /**
     * No spell
     */
    NONE(0),

    /**
     * Summon Vex spell
     */
    SUMMON_VEX(1),

    /**
     * Attack spell
     */
    ATTACK(2),

    /**
     * WOLOLO spell (yes, this is actually what its called internally)
     */
    WOLOLO(3);

    @Getter
    private final int data;

    EvokerSpellType(int data) {
        this.data = data;
    }

    /**
     * Gets the Evoker spell type corresponding to the given internal identification number.
     * <br>
     * If none are found, an {@link IllegalArgumentException} will be thrown.
     *
     * @param id The identification number.
     *
     * @return The evoker spell type.
     */
    @Nonnull
    public static EvokerSpellType of(int id) {
        for (EvokerSpellType type : values()) {
            if (type.data == id) {
                return type;
            }
        }

        throw new IllegalArgumentException("no Evoker spell type with id = " + id);
    }

}
