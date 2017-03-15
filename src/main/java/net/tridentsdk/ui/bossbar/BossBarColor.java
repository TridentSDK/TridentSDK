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
package net.tridentsdk.ui.bossbar;

import lombok.Getter;

/**
 * Represents the different colours that a boss bar can be.
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
@Getter
public enum BossBarColor {

    /**
     * A pink boss bar.
     */
    PINK(0),

    /**
     * A blue boss bar.
     */
    BLUE(1),

    /**
     * A red boss bar.
     */
    RED(2),

    /**
     * A green boss bar.
     */
    GREEN(3),

    /**
     * A yellow boss bar.
     */
    YELLOW(4),

    /**
     * A purple boss bar.
     */
    PURPLE(5),

    /**
     * A white boss bar.
     */
    WHITE(6);

    /**
     * The internal ID used by net data.
     */
    private final int id;

    BossBarColor(int id) {
        this.id = id;
    }

    /**
     * Gets the boss bar color with the given ID.
     * An {@link IllegalArgumentException} is thrown if no color found.
     *
     * @param id The ID, the identification number.
     * @return The boss bar color.
     */
    public static BossBarColor of(int id) {
        for (BossBarColor c : values())
            if (c.id == id)
                return c;
        throw new IllegalArgumentException("no boss bar color with id = " + id);
    }

}
