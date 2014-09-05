package net.tridentsdk.api.world;

public interface Chunk {

    public void generate();
    
    public ChunkLocation getLocation();

    public int getX();

    public int getZ();

    public World getWorld();
}
