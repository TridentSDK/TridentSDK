package net.tridentsdk.meta.block;

import net.tridentsdk.base.Block;
import net.tridentsdk.inventory.Inventory;

/**
 * TODO Write Description
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 */
public interface ChestMeta extends BlockMeta<Block> {

    Inventory inventory();

}
