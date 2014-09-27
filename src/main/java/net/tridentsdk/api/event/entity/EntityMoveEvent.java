package net.tridentsdk.api.event.entity;

import net.tridentsdk.api.entity.Entity;
import net.tridentsdk.api.event.Cancellable;

/**
 * Called when an entity moves, ignores short movements
 */
public class EntityMoveEvent extends EntityEvent implements Cancellable {
    public EntityMoveEvent(Entity entity) {
        super(entity);
    }

}
