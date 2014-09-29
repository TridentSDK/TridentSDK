package net.tridentsdk.api.event.block;

import net.tridentsdk.api.Block;
import net.tridentsdk.api.Orientation;
import net.tridentsdk.api.event.Cancellable;

public class PistonRetractEvent extends BlockPistonEvent implements Cancellable {
    private boolean cancelled;

    public PistonRetractEvent(Block block, Orientation direction, Block influenced) {
        super(block, direction,true, influenced);
    }
}
