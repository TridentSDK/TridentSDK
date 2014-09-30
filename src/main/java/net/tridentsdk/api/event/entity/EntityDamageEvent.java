package net.tridentsdk.api.event.entity;

import net.tridentsdk.api.entity.Entity;
import net.tridentsdk.api.entity.LivingEntity;
import net.tridentsdk.api.entity.living.Player;
import net.tridentsdk.api.event.Cancellable;

public class EntityDamageEvent extends EntityEvent implements Cancellable {
    private final Cause cause;
    private boolean cancel;
    private double damage;

    /**
     * @param player the player associated with this event
     * @param damage the amount of damage dealt to the player
     */

    public EntityDamageEvent(Entity entity, double damage, Cause cause) {
        super(entity);
        this.setDamage(damage);
        this.cause = cause;
    }

    public Cause getCause() {
        return this.cause;
    }

    /**
     * @return return the amount of damage dealt
     */

    public double getDamage() {
        return this.damage;
    }

    @Override
    public LivingEntity getEntity() {
        return (LivingEntity) super.getEntity();
    }

    /**
     * Change the damage value dealt
     *
     * @param damage the amount of damage dealt
     */

    public void setDamage(double damage) {
        this.damage = damage;
    }

    @Override
    public boolean isCancelled() {
        return this.cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    public enum Cause {
        STARVATION,
        FIRE,
        FALL,
        EXPLOSION,
        HIT,
        ENDER_PEARL,
        PROJECTILE,
        LIGHTNING,
        DROWNING,
        SUFFOCATION,
        ANVIL,
        CONTACT,
        LAVA,
        POISON,
        WITHER,
        VOID
    }
}
