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

import net.tridentsdk.base.Block;
import net.tridentsdk.entity.Entity;

import javax.annotation.concurrent.ThreadSafe;
import java.util.Set;

/**
 * Represents a 16x16x256 cube of blocks which stores a partition of the world's data
 *
 * @author The TridentSDK Team
 * @since 0.3-alpha-DP
 */
@ThreadSafe
public interface Chunk {
    /**
     * The entities in the chunk
     *
     * @return the entities
     */
    Set<Entity> entities();

    /**
     * Generates the chunk
     */
    void generate();

    /**
     * The location of the chunk
     *
     * @return the chunk's location
     */
    ChunkLocation location();

    /**
     * Shortcut for: {@code location().x()}
     *
     * @return the chunk's x coordinate
     */
    int x();

    /**
     * Shortcut for: {@code location().z()}
     *
     * @return the chunk's z coordinate
     */
    int z();

    /**
     * The world which contains this chunk
     *
     * @return the containing world
     */
    World world();

    /**
     * Obtains a block relative to the chunk
     *
     * @param relX the relative x 0-15
     * @param y    the y coordinate
     * @param relZ the relative z 0-15
     * @return the block at that coordinate
     */
    Block blockAt(int relX, int y, int relZ);

    /**
     * Obtains a snapshot of the state of the chunk
     *
     * @return the chunk's frozen state which can be restored
     */
    ChunkSnapshot snapshot();

    /**
     * Unloads the chunk
     */
    void unload();
}
