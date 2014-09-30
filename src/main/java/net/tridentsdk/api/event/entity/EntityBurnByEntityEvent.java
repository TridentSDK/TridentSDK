package net.tridentsdk.api.event.entity;

import net.tridentsdk.api.entity.Entity;
import net.tridentsdk.api.entity.LivingEntity;
import net.tridentsdk.api.event.Cancellable;

/**
 * Called when an entity gets set on fire by another entity
 */
public class EntityBurnByEntityEvent extends EntityBurnEvent implements Cancellable {
    private final Entity causer;

    public EntityBurnByEntityEvent(LivingEntity entity, int fireTicks, Entity causer) {
        super(entity, fireTicks);
        this.causer = causer;
    }

    /**
     * Gets the entity that set this entity on fire
     *
     * @return
     */
    public Entity getBurner() {
        return causer;
    }
}
