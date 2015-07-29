package net.tridentsdk.effect;

import net.tridentsdk.entity.LivingEntity;

/**
 * Represents all visual, sound, particle and entity effects
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 */
public interface Effect<T> {

    /**
     * Execute the effect at the given location for specified player
     *
     * @param entity The entity to apply the effect on
     */
    void apply(LivingEntity entity);

    /**
     * The type of the effect
     *
     * @return The type of the effect
     */
    T type();

}
