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

package net.tridentsdk.entity.block;

import net.tridentsdk.base.BlockSnapshot;
import net.tridentsdk.entity.Entity;

/**
 * Represents a dynamic tile
 *
 * @author TridentSDK Team
 */
public interface FallingBlock extends Entity {
	
    /**
     * The state this FallingBlock represents
     *
     * @return The BlockSnapshot of this falling block
     */
    BlockSnapshot getSnapshot();

    /**
     * Whether or not this FallingBlock should drop when it breaks
     *
     * @return Whether or not this FallingBlock will drop its item when it breaks
     */
    boolean shouldDrop();
    
    /**
     * Sets whether or not this FallingBlock should drop the block when broken.
     * @param drop Whether or not to drop the item.
     */
    void setShouldDrop(boolean drop);
    
}
