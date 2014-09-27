package net.tridentsdk.api.event.block;

import net.tridentsdk.api.Block;
import net.tridentsdk.api.event.Cancellable;

/**
 * Called when something lights a block on fire
 */
public class BlockIgniteEvent extends BlockEvent implements Cancellable{
    private boolean cancelled;
    private final Cause cause;

    public enum Cause {
        FIRE_SPREAD,
        PLAYER,
        LIGHTENING,
        LAVA,
        FIREBALL
    }

    @Override
    public boolean isCancelled() {

        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    public BlockIgniteEvent(Block block, Cause cause) {
        super(block);
        this.cause = cause;
    }

    public Cause getCause() {
        return cause;
    }
}
