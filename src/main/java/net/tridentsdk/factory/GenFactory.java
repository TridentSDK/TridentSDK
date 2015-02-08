package net.tridentsdk.factory;

import net.tridentsdk.world.Chunk;
import net.tridentsdk.world.gen.TempGenBlock;

/**
 * Produces world generation utilities and general classes needed to create worlds
 *
 * @author The TridentSDK Team
 */
public interface GenFactory {
    void putBlock(TempGenBlock tile, Chunk world);
}
