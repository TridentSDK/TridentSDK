package net.tridentsdk.api.event.block;

import net.tridentsdk.api.Block;
import net.tridentsdk.api.event.Cancellable;

/**
 * Called when a block spreads, like grass or mycelium
 */
public class BlockSpreadEvent extends BlockGrowthEvent implements Cancellable{
    private final Block blockFrom;

    public BlockSpreadEvent(Block to, Block from) {
        super(to);
        this.blockFrom = from;
    }

    public Block getFrom () {
        return blockFrom;
    }

}
