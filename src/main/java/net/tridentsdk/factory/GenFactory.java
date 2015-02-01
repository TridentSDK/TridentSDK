package net.tridentsdk.factory;

import net.tridentsdk.world.Chunk;
import net.tridentsdk.world.World;
import net.tridentsdk.world.gen.ChunkTile;

/**
 * Produces world generation utilities and general classes needed to create worlds
 *
 * @author The TridentSDK Team
 */
public interface GenFactory {
    void putBlock(ChunkTile tile, Chunk world);
}
