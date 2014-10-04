package net.tridentsdk.api.event.entity;

import net.tridentsdk.api.Block;
import net.tridentsdk.api.entity.LivingEntity;
import net.tridentsdk.api.event.Cancellable;

/**
 * Called when an Entity is set on fire by a block
 */
public class EntityBurnByBlockEvent extends EntityBurnEvent implements Cancellable {
    private final Block causer;

    public EntityBurnByBlockEvent(LivingEntity entity, int fireTicks, Block causer) {
        super(entity, fireTicks);
        this.causer = causer;
    }

    /**
     * Gets the block that set this entity on fire
     *
     * @return
     */
    public Block getBurner() {
        return causer;
    }
}
