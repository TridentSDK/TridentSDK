package net.tridentsdk.api.event.entity;

import net.tridentsdk.api.entity.Entity;
import net.tridentsdk.api.event.Cancellable;

/**
 * Called when an Entity catches fire
 */
public class EntityIgniteEvent extends EntityEvent implements Cancellable {
    public EntityIgniteEvent(Entity entity) {
        super(entity);
    }
}
