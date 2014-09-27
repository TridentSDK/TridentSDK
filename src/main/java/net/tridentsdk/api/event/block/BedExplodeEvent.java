package net.tridentsdk.api.event.block;

import net.tridentsdk.api.Block;
import net.tridentsdk.api.event.Cancellable;

public class BedExplodeEvent extends BlockEvent implements Cancellable {
    private float strength;
    private boolean cancelled;

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    public BedExplodeEvent(Block block, float strength) {
        super(block);
        this.strength = strength;
    }

    public float getStrength() {

        return strength;
    }

    public void setStrength(float strength) {
        this.strength = strength;
    }
}
