package net.tridentsdk.api.event.block;

import net.tridentsdk.api.Block;
import net.tridentsdk.api.Material;
import net.tridentsdk.api.Orientation;
import net.tridentsdk.api.event.Cancellable;

/**
 * Called whenever a piston extends or retracts
 */
public class BlockPistonEvent extends BlockEvent implements Cancellable {

    // TODO: separate this because a piston pushing can make multiple blocks move

    private final Orientation direction;
    private final boolean retract;
    private final Block influenced;
    private boolean cancelled;

    public BlockPistonEvent(Block block, Orientation direction, boolean retract, Block influenced) {
        super(block);
        this.direction = direction;
        this.retract = retract;
        this.influenced = influenced;

    }

    /**
     * Returns the direction that the piston is facing, for example if the piston head face of a block is on the north
     * end of a block, then the Direction that this event returns will be north, does not change depending on whether
     * this piston is extending or retracting, so a block may actually be moving south if this is returns north
     *
     * @return
     */
    public Orientation getDirection() {
        return direction;
    }

    /**
     * Returns true if this piston is retracting
     *
     * @return
     */
    public boolean isRetracting() {
        return retract;
    }

    /**
     * Returns true if this piston is extending, convenience for !isRetracting()
     *
     * @return
     */
    public boolean isExtending() {
        return !retract;
    }

    /**
     * Gets the block that is being moved by this piston, if any
     *
     * @return the block being moved, may be null if air, or retracting from a block without this piston being sticky
     */
    public Block getInfluencedBlock() {
        return influenced;
    }

    public boolean isSticky() {
        return getBlock().getType() == Material.PISTON_STICKY_BASE;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
