package net.tridentsdk.api.event.block;

import net.tridentsdk.api.Block;
import net.tridentsdk.api.event.Cancellable;

/**
 * Called when a crop grows, like wheat or netherwart
 */
public class CropGrowthEvent extends BlockGrowthEvent implements Cancellable {
    public CropGrowthEvent(Block block) {
        super(block);
    }
}
