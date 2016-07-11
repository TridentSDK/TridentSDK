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
import net.tridentsdk.base.BlockDirection;

import javax.annotation.concurrent.ThreadSafe;

/**
 * Represents the metadata of a block that has a direction
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 */
@ThreadSafe
public interface DirectionMeta extends BlockMeta<Block> {
    /**
     * Obtains the facing direction of this block
     *
     * @return the facing direction
     */
    BlockDirection direction();

    /**
     * Sets the facing direction of the block
     *
     * @param direction the direction to face
     */
    void setDirection(BlockDirection direction);
}
