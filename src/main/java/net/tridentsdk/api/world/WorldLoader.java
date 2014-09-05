package net.tridentsdk.api.world;

public interface WorldLoader {
    public World load(String world);
    public void save(World world);

    public boolean worldExists(String world);
    public boolean chunkExists(World world, int x, int z);
    public boolean chunkExists(World world, ChunkLocation location);

    public Chunk loadChunk(World world, int x, int z);
    public Chunk loadChunk(World world, ChunkLocation location);
    public void saveChunk(Chunk chunk);
}
