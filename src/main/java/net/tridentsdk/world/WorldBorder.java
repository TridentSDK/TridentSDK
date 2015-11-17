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
package net.tridentsdk.world;

import net.tridentsdk.util.Pair;

import javax.annotation.concurrent.ThreadSafe;

/**
 * Access to the world border properties
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 * @deprecated the world border is deprecated because the units which are used are unclear and are subject to change at
 *             any time. Use at your own risk.
 */
@ThreadSafe
@Deprecated
public interface WorldBorder {
    /**
     * Gets the size of the worldborder
     *
     * @return The size of the worldborder
     */
    int size();

    /**
     * Modifies the border square's length and width
     *
     * @param mod a positive integer to indicate an expansion by that amount, a negative to indicate a regression by
     *            that amount
     * @param time the time over which the border will expand or contract, 0 to apply immediately
     */
    void modify(int mod, int time);

    /**
     * Gets the position where the worldborder is centered
     *
     * <p>The returned immutable pair of integers represents the X and Y coordinates respectively</p>
     *
     * @return The position where the worldborder is centered
     */
    Pair<Integer, Integer> center();

    /**
     * Sets the center X and Z coordinates for the border
     *
     * @param x the X coordinate
     * @param z the Z coordinate
     */
    void setCenter(int x, int z);

    /**
     * Gets to what size a border is contracting, 60000000 by default
     *
     * @return To what size a border is contracting, 60000000 by default
     */
    int sizeContraction();

    /**
     * Gets the time the border has to contract to the contraction target
     *
     * @return The time the border has to contract to the contraction target
     */
    int contractionTime();
}