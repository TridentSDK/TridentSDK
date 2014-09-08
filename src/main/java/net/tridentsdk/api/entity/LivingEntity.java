package net.tridentsdk.api.entity;

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

    /**
     * Checks if the entity has died, or has 0 health. Should only apply to entities that are "live" (TODO
     * LivingEntity)
     *
     * @return {@code true} if the entity is dead, {@code false} if it is alive
     */
    boolean isDead();
}
