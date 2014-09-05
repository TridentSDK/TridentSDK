package net.tridentsdk.api.world;

import net.tridentsdk.api.Block;
import net.tridentsdk.api.Location;

public interface World {

    public String getName();

    public Chunk getChunkAt(ChunkLocation loc, boolean generateIfNotFound);
    
    public Chunk getChunkAt(int x, int z, boolean generateIfNotFound);

    public Chunk generateChunk(int x, int z);
    
    public Chunk generateChunk(ChunkLocation location);

    public Block getBlockAt(Location location);
    
    public ChunkSnapshot getChunkSnapshot();
    
}
