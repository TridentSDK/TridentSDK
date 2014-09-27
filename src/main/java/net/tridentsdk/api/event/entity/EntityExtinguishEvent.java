package net.tridentsdk.api.event.entity;

import net.tridentsdk.api.entity.Entity;
import net.tridentsdk.api.event.Cancellable;

/**
 * Called when an Entity is not longer on fire
 */
public class EntityExtinguishEvent extends EntityEvent implements Cancellable {
    public EntityExtinguishEvent(Entity entity) {
        super(entity);
    }
}
