package net.tridentsdk.api.event.block;

import net.tridentsdk.api.Block;
import net.tridentsdk.api.event.Cancellable;

/**
 * Called when a block is broken due to an explosion, handled separately than other reasons to reduce
 * lag caused by BlockBreak event listeners during explosions
 */
public class MultiBlockBreakEvent extends BlockEvent implements Cancellable {
    private boolean cancelled;

    public MultiBlockBreakEvent(Block block) {
        super(block);
    }

    @Override
    public boolean isCancelled() {

        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }
}
