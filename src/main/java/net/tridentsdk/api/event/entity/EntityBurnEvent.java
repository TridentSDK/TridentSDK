package net.tridentsdk.api.event.entity;

import net.tridentsdk.api.entity.LivingEntity;
import net.tridentsdk.api.event.Cancellable;

/**
 * Called when an entity catches fire
 */
public abstract class EntityBurnEvent extends EntityEvent implements Cancellable {
    private boolean cancelled;
    private int fireTicks;

    public EntityBurnEvent(LivingEntity entity, int fireTicks) {
        super(entity);
        this.fireTicks = fireTicks;
    }

    /**
     * Gets how long this entity will be on fire for, in ticks
     *
     * @return
     */
    public int getFireTicks() {
        return fireTicks;
    }

    /**
     * Sets how long this entity will be on fire for, in ticks
     *
     * @param fireTicks
     */
    public void setFireTicks(int fireTicks) {
        this.fireTicks = fireTicks;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
