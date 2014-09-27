package net.tridentsdk.api.event.block;

import net.tridentsdk.api.Block;
import net.tridentsdk.api.event.Cancellable;

/**
 * Called when something lights a block on fire
 */
public class BlockIgniteEvent extends BlockEvent implements Cancellable {
    private final Cause cause;
    private boolean cancelled;

    public BlockIgniteEvent(Block block, Cause cause) {
        super(block);
        this.cause = cause;
    }

    @Override
    public boolean isCancelled() {

        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    public Cause getCause() {
        return cause;
    }

    public enum Cause {
        FIRE_SPREAD,
        PLAYER,
        LIGHTENING,
        LAVA,
        FIREBALL
    }
}
