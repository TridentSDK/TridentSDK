package net.tridentsdk.api.event.entity;

import net.tridentsdk.api.entity.Entity;
import net.tridentsdk.api.entity.Projectile;
import net.tridentsdk.api.event.Cancellable;

/**
 * Called when an entity launches a projectile
 */
public class EntityLaunchProjectileEvent extends EntityEvent implements Cancellable {
    private final Projectile projectile;
    private final Entity target;
    private boolean cancelled;

    public EntityLaunchProjectileEvent(Entity entity, Projectile projectile,Entity target) {

        super(entity);
        this.projectile = projectile;
        this.target = target;
    }

    public Projectile getProjectile() {
        return projectile;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    /**
     * Gets the Entity the skeleton was targeting when it fired
     * @return
     */
    public Entity getTarget() {
        return target;
    }
}
