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

import net.tridentsdk.api.*;

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
    GameMode getDefaultGamemode();
    
    /**
     * Gets the type of a world
     * 
     * @return The type of a world
     */
    LevelType getLevelType();
    
    /**
     * Gets the set boolean for the given gamerule
     * 
     * @return The set boolean for the given gamerule
     */
    boolean getGamerule(String rule);
    
    /**
     * Gets the time in a world
     * 
     * @return The time in a world
     */
    long getTime();
    
    /**
     * Gets the spawn location of a world
     * 
     * @return The spawn location in a world
     */
    Location getSpawn();
    
    /**
     * Checks if it is raining in a world
     * 
     * @return True if it is raining in a world
     */
    boolean isRaining();
    
    /**
     * Gets the number of ticks before raining is toggled
     * 
     * @return The number of ticks before raining is toggled
     */
    int getRainTime();
    
    /**
     * Checks if it is thundering in a world
     * 
     * @return True if it is thundering in a world
     */
    boolean isThundering();
    
    /**
     * Gets the number of ticks before thundering is toggled
     * 
     * @return The number of ticks before thundering is toggled
     */
    int getThunderTime();
    
    /**
     * Checks if structures are generated in a world (Stronghold, villages, dungeons)
     * 
     * @return True if structures are generated in a world (Stronghold, villages, dungeons)
     */
    boolean canGenerateStructures();
    
    /**
     * Gets the size of the worldborder
     * 
     * @return The size of the worldborder
     */
    int getBorderSize();
    
    /**
     * Gets the location where the worldborder is centered
     * 
     * @return The location where the worldborder is centered
     */
    Location getBorderCenter();
    
    /**
     * Gets to what size a border is contracting, 60000000 by default
     * 
     * @return To what size a border is contracting, 60000000 by default
     */
    int getBorderSizeContraction();
    
    /**
     * Gets the time the border has to contract to the contraction target
     * 
     * @return The time the border has to contract to the contraction target
     */
    int getBorderSizeContractionTime();
    
    
}