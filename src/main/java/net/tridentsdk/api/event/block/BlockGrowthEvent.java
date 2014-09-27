package net.tridentsdk.api.event.block;


import net.tridentsdk.api.Block;
import net.tridentsdk.api.event.Cancellable;

/**
 * Called when a crop grows or a block spreads
 */
public abstract class BlockGrowthEvent extends BlockEvent implements Cancellable{
    private boolean cancelled;

    @Override
    public boolean isCancelled() {

        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
    public BlockGrowthEvent(Block block) {
        super(block);
    }
}
