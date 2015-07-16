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

import net.tridentsdk.Position;
import net.tridentsdk.util.Vector;

/**
 * A basic structure in minecraft, a material bearing piece set at a given location
 *
 * @author The TridentSDK Team
 */
public interface Block {
    /**
     * Gets the substance the tile is made of
     *
     * @return the type the tile was set
     */
    Substance getSubstance();

    /**
     * Sets the substance the tile is made of
     *
     * @param material the substance the tile should be set to
     */
    void setSubstance(Substance material);

    /**
     * Sets the substance the tile is made of and its tile data
     *
     * @param material the substance the tile should be set to
     * @param data the data the tile should be set to
     */
    void setSubstanceAndMeta(Substance material, byte data);

    /**
     * Returns the Location of the Block
     *
     * @return Location of the Block
     */
    Position getPosition();

    /**
     * Gets the tile data
     *
     * @return the data of the tile
     */
    byte getMeta();

    /**
     * Sets the tile data
     *
     * @param data the data to set the tile
     */
    void setMeta(byte data);

    /**
     * Returns a block immediately to the direction specified
     *
     * @param vector the direction to look for the block adjacent to the current
     * @return the block adjacent to the current
     */
    Block getBlockRelative(Vector vector);
}
