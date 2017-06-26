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

package net.tridentsdk.meta.block;

import net.tridentsdk.base.Block;

import javax.annotation.concurrent.ThreadSafe;

/**
 * MetaData contained in a Cauldron.
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 */
@ThreadSafe
public interface CauldronMeta extends BlockMeta<Block> {
    /**
     * Gets the filled water level of the respective cauldron.
     * @return The water level.
     */
    short filledLevel();

    /**
     * Sets the filled water level of the cauldron.
     * @param level The desired water level.
     */
    void setFilledLevel(short level);

    /**
     * Gets the filled water level as a percentage.
     * @return Water level as a percentage.
     */
    double filledPercentage();
}
