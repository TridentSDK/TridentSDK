package net.tridentsdk.entity.living;

import net.tridentsdk.entity.Entity;

/**
 * This is a specific set of entities including mobs and
 * monsters which have health.
 *
 * @author TridentSDK
 * @since 0.3-alpha-DP
 */
public interface EntityLiving extends Entity {
    /**
     * Sets whether or not this entity is sprinting.
     *
     * @param sprinting {@code true} to start sprinting,
     * {@code false} to stop sprinting
     */
    void setSprinting(boolean sprinting);

    /**
     * Checks to see whether or not the entity is currently
     * sprinting.
     *
     * @return {@code true} if the entity is sprinting,
     * {@code false} if the player is not
     */
    boolean isSprinting();

    /**
     * Sets this entity's crouching/sneaking state.
     *
     * @param crouching {@code true} to begin crouching,
     * {@code false} to stop crouching
     */
    void setCrouching(boolean crouching);

    /**
     * Checks this entity's crouch/sneak state.
     *
     * @return {@code true} if the entity is crouching,
     * {@code false} if the player is not
     */
    boolean isCrouching();
}