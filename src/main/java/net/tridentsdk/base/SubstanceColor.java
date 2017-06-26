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

package net.tridentsdk.base;

/**
 * Represents a color in minecraft
 *
 * @author The TridentSDK Team
 * @since 0.3-alpha-DP
 */
public enum SubstanceColor {
    WHITE(0), ORANGE(1), MAGENTA(2), LIGHT_BLUE(3),
    YELLOW(4), LIME(5), PINK(6),
    GRAY(7), SILVER(8), CYAN(9),
    PURPLE(10), BLUE(11), BROWN(12),
    GREEN(13), RED(14), BLACK(15);

    int value;

    SubstanceColor(int i) {
        value = i;
    }

    /**
     * Gets a substance color from the byte value
     *
     * @param v the byte value
     * @return the substance color with that value, or {@code null} if it is out of bounds (bound: 0 <= x <= 15)
     */
    public static SubstanceColor of(byte v) {
        for (SubstanceColor color : values()) {
            if (color.asInt() == v) {
                return color;
            }
        }

        return null;
    }

    /**
     * Gets the color value as an int
     *
     * @return the value given to the color by the protocol
     */
    public int asInt() {
        return value;
    }
}
