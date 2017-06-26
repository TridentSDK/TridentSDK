/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2014 The TridentSDK Team
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
package net.tridentsdk.meta.item;

import java.util.List;

import net.tridentsdk.base.Color;

public interface ExplosionMeta {

    public static enum ExplosionType {
        /**
         * Represents a small firework explosion.
         */
        SMALL,
        /**
         * Represents a large firework explosion.
         */
        LARGE,
        /**
         * Represents a star-shaped firework explosion.
         */
        STAR,
        /**
         * Represents a creeper-shaped firework explosion.
         */
        CREEPER,
        /**
         * Represents a burst-shaped firework explosion.
         */
        BURST,
        /**
         * <p>Represents an unknown firework explosion.</p>
         *
         * <p><em>ID is preserved in case of future Minecraft effects.</em></p>
         */
        UNKNOWN
    }

    /**
     * Gets the main colors of this Explosion.
     *
     * @return The main colors.
     */
    List<Color> colors();

    /**
     * Gets the fade colors of this Explosion.
     *
     * @return The fade colors.
     */
    List<Color> fadeColors();

    /**
     * Gets this Explosion's type.
     *
     * @return The type.
     */
    ExplosionType type();

    /**
     * Gets whether or not this explosion has the Flicker effect applied to it.
     *
     * @return True if Flicker is enabled; false otherwise.
     */
    boolean isFlicker();

    /**
     * Gets whether or not this explosion has the Trail effect applied to it.
     *
     * @return True if Trail is enabled; false otherwise.
     */
    boolean isTrail();
}
