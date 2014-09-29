package net.tridentsdk.api.event.entity;

import net.tridentsdk.api.entity.Entity;
import net.tridentsdk.api.entity.projectile.Arrow;
import net.tridentsdk.api.event.Cancellable;

/**
 * Called when a skeleton shoots an arrow
 */
public class EntityShootArrowEvent extends EntityLaunchProjectileEvent implements Cancellable {

    public EntityShootArrowEvent(Entity entity, Arrow projectile, Entity target) {
        super(entity, projectile, target);
    }

    public Arrow getArrow() {
        return (Arrow) super.getProjectile();
    }

}
