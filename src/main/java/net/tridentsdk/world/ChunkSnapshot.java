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

/**
 * Represents the state of a chunk at a certain point in time
 *
 * @author The TridentSDK Team
 * @since 0.3-alpha-DP
 */
public interface ChunkSnapshot extends Chunk {
    /**
     * Loads the data of the snapshot into the specified chunk
     *
     * @param chunk the chunk to reset the data contained in this snapshot to
     */
    void load(Chunk chunk);

    /**
     * Updates the chunk from this snapshot with the data contained in this snapshot
     */
    void load();
}
