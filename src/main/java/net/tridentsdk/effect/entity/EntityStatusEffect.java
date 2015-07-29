package net.tridentsdk.effect.entity;

import net.tridentsdk.effect.Effect;
import net.tridentsdk.entity.Entity;

/**
 * Represents an entity effect
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 */
public interface EntityStatusEffect extends Effect<EntityStatusEffectType> {

    /**
     * Set the entity to which the status effect will be applied
     *
     * @param entity The entity to which the status effect will be applied
     */
    void setEntity(Entity entity);

    /**
     * Returns the entity to which the status effect will be applied
     *
     * @return The entity to which the status effect will be applied
     */
    Entity entity();

}
