package net.tridentsdk.api.world;

import net.tridentsdk.api.Block;
import net.tridentsdk.api.Location;

public interface World {

    public String getName();

    public Chunk getChunkAt(int x, int z, boolean generateIfNotFound);

    public void generateChunk(int x, int z);

    public Block getBlockAt(Location location);
}
