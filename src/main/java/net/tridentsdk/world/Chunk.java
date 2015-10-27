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
import net.tridentsdk.base.BoundingBox;
import net.tridentsdk.entity.Entity;
import net.tridentsdk.meta.block.Tile;

import javax.annotation.concurrent.ThreadSafe;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Represents a 16x16x256 cube of blocks which stores a partition of the world's data
 *
 * @author The TridentSDK Team
 * @since 0.3-alpha-DP
 */
@ThreadSafe
public interface Chunk {
    /**
     * Generates the chunk
     */
    void generate();

    /**
     * Loads the chunk
     *
     * @return {@code true} to signify that the chunk was successfully loaded from file, {@code false} to signify that
     * it does not exist on file, or already loaded
     */
    boolean load();

    /**
     * Checks the chunk to ensure it is loaded
     *
     * @return {@code true} to signify that the chunk is loaded, {@code false} if it is not
     */
    boolean isLoaded();

    /**
     * Unloads the chunk
     */
    void unload();

    /**
     * The entities in the chunk
     *
     * @return the entities
     */
    Set<Entity> entities();

    /**
     * Find all entities that are colliding with the bounding box
     *
     * @param exclude the entity to exclude from the search
     * @param boundingBox The bounding box to search in
     * @param predicate Predicate to filter results
     * @return List of entities inside the bounding box
     */
    ArrayList<Entity> getEntities(Entity exclude, BoundingBox boundingBox, Predicate<? super Entity> predicate);

    /**
     * Obtains the tile entities in this chunk
     *
     * @return the tile entities
     */
    Collection<Tile> tiles();

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
}