package net.tridentsdk.api.event.block;

import net.tridentsdk.api.Block;
import net.tridentsdk.api.event.Cancellable;

public class BlockFlowEvent extends BlockEvent implements Cancellable {
    private final Block to;
    private boolean cancelled;

    public BlockFlowEvent(Block from, Block to) {
        super(from);
        this.to = to;
    }

    public Block getTo() {
        return to;
    }

    public Block getFrom() {
        return super.getBlock();
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
}
