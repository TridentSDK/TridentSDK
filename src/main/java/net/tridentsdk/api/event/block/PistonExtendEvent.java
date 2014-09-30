package net.tridentsdk.api.event.block;

import com.google.common.collect.ImmutableList;
import net.tridentsdk.api.Block;
import net.tridentsdk.api.Orientation;
import net.tridentsdk.api.event.Cancellable;

import java.util.List;

public class PistonExtendEvent extends BlockPistonEvent implements Cancellable {
    private final ImmutableList<Block> blocksInfluenced;
    private boolean cancelled;

    public PistonExtendEvent(Block block, Orientation direction, List<Block> influenced) {
        super(block, direction, false, influenced.get(0));

        blocksInfluenced = ImmutableList.copyOf(influenced);
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    /**
     * Returns an ImmutableList of the blocks that are being pushed by this piston, may be empty
     * @return
     */
    public List<Block> getBlocksInfluenced() {
        return blocksInfluenced;
    }
}
