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

import javax.annotation.concurrent.ThreadSafe;

/**
 * This class represents a Minecraft voxel unit that players
 * may manipulate.
 *
 * @author TridentSDK
 * @since 0.3-alpha-DP
 */
@ThreadSafe
public interface Block {
    /**
     * Obtains the substance of which this block is made.
     *
     * @return the block substance
     */
    Substance getSubstance();

    /**
     * Set the substance of the block
     *
     * @param substance The new substance
     */
    void setSubstance(Substance substance);

    /**
     * Obtains the block damage data value.
     *
     * @return the damage value for the block
     */
    byte getData();

    /**
     * Sets the block damage data value.
     *
     * @param data the damage value which to set the block
     */
    void setData(byte data);

    /**
     * Obtains the position at which this block is located
     *
     * @return the block position
     */
    Position getPosition();
}