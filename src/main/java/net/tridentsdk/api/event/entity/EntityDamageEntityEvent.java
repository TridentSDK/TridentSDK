package net.tridentsdk.api.event.entity;

import net.tridentsdk.api.entity.Entity;
import net.tridentsdk.api.event.Cancellable;

/**
 * Called when an entity damages another entity
 */
public class EntityDamageEntityEvent extends EntityDamageEvent implements Cancellable {
    private final Entity damager;

    public EntityDamageEntityEvent(Entity entity, Entity damager, double damage, Cause cause) {
        super(entity, damage, cause);
        this.damager = damager;
    }

    public Entity getDamager() {
        return damager;
    }
}
