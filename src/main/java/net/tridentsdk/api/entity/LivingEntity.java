package net.tridentsdk.api.entity;

import net.tridentsdk.api.Location;

public interface LivingEntity extends Entity {

    /**
     * Makes the specified entity invisible to the current entity
     *
     * @param entity the entity to make invisible to this entity
     */
    void hide(Entity entity);

    /**
     * Un-hides the entity that was hidden from view, or does nothing of already visible
     *
     * @param entity the entity to make visible to this entity
     */
    void show(Entity entity);

    double getHealth();

    double getMaxHealth();

    long getRemainingAir();

    String getDisplayName();

    Location getEyeLocation();

    boolean canPickupItems();

    void setRemainingAir(long ticks);

    void setHealth(double health);

    void setMaxHealth(double maxHealth);

    /**
     * Checks if the entity has died, or has 0 health. Should only apply to entities that are "live" (TODO
     * LivingEntity)
     *
     * @return {@code true} if the entity is dead, {@code false} if it is alive
     */
    boolean isDead();
}
