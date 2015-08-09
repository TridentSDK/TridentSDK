package net.tridentsdk.meta.block;

import net.tridentsdk.base.Block;
import net.tridentsdk.inventory.Item;

/**
 * TODO Write Description
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 */
public interface FurnaceMeta extends BlockMeta<Block> {

    Item sourceSlot();

    Item fuelSlot();

    Item resultSlot();

    int burnTicks();

    void setSourceSlot(Item source);

    void setFuelSlot(Item fuel);

    void setResultSlot(Item result);

    void setBurnTicks(int burnTicks);

}
