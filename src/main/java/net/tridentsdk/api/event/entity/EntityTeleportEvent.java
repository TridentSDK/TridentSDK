package net.tridentsdk.api.event.entity;

import net.tridentsdk.api.entity.Entity;
import net.tridentsdk.api.event.Cancellable;

/**
 * Called when an entity teleports, e.g. enderman
 */
public class EntityTeleportEvent extends EntityMoveEvent implements Cancellable {

    public EntityTeleportEvent(Entity entity) {
        super(entity);
    }
}
