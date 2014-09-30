/*
 * Copyright (c) 2014, The TridentSDK Team
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     1. Redistributions of source code must retain the above copyright
 *        notice, this list of conditions and the following disclaimer.
 *     2. Redistributions in binary form must reproduce the above copyright
 *        notice, this list of conditions and the following disclaimer in the
 *        documentation and/or other materials provided with the distribution.
 *     3. Neither the name of the The TridentSDK Team nor the
 *        names of its contributors may be used to endorse or promote products
 *        derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL The TridentSDK Team BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package net.tridentsdk.api.world;

import net.tridentsdk.api.Block;
import net.tridentsdk.api.Difficulty;
import net.tridentsdk.api.Gamemode;
import net.tridentsdk.api.Location;

public interface World {
    
    /**
     * Gets the name of the world
     * 
     * @return the name of the world
     */
    String getName();
    
    /**
     * Gets the chunk on the given location, and generates the chunk if it does not exist.
     * 
     * @return The chunk on the given location
     */
    Chunk getChunkAt(ChunkLocation loc, boolean generateIfNotFound);
    
    /**
     * Gets the chunk on the given x and z , and generates the chunk if it does not exist
     * 
     * @return The chunk on the given location
     */
    Chunk getChunkAt(int x, int z, boolean generateIfNotFound);
    
    /**
     * Generates the chunk on the given location
     * 
     * @return The generated chunk
     */
    Chunk generateChunk(int x, int z);
    
    /**
     * Generates the chunk on the given location
     * 
     * @return The generated chunk
     */
    Chunk generateChunk(ChunkLocation location);
    
    /**
     * Gets the block on the given location
     * 
     * @return The block on the given location
     */
    Block getBlockAt(Location location);
    
    /**
     * Gets the ChunkSnapshot
     * 
     * @return The ChunkSnapshot
     */
    ChunkSnapshot getChunkSnapshot();
    
    /**
     * Gets the dimension of a world
     * 
     * @return The dimension of a world
     */
    Dimension getDimension();
    
    /**
     * Gets the difficulty set in a world
     * 
     * @return The difficulty set in a world
     */
    Difficulty getDifficulity();
    
    /**
     * Gets the default gamemode in a given chunk
     * 
     * @return The default gamemode in a given chunk
     */
    Gamemode getDefaultGamemode();
    
    /**
     * Gets the type of a world
     * 
     * @return The type of a world
     */
    LevelType getLevelType();
}
