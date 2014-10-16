package net.tridentsdk.api.entity;

import net.tridentsdk.api.Block;

/**
 * A tile or entity that can be hit by a projectile
 *
 * @author The TridentSDK Team
 */
public interface Impalable {
    /**
     * Whether or not the impalable is an entity
     *
     * @return {@code true} if the impaled object is an entity, {@code false} if it is a tile
     */
    boolean isEntity();

    /**
     * Whether or not the impalable is a block (tile)
     *
     * @return {@code true} if the impaled object is a block (tile), {@code false} if it is an entity
     */
    boolean isTile();

    /**
     * Gets the entity that was impaled by the projectile
     *
     * <p>Returns {@code null} if {@code isEntity == false}</p>
     *
     * @return the entity impaled by the projectile
     */
    Entity impaledEntity();

    /**
     * Gets the block (tile) that was impaled by the projectile
     *
     * <p>Returns {@code null} if {@code isTile == false}</p>
     *
     * @return the impaled block (tile)
     */
    Block impaledTile();

    /**
     * Gets the last projectile that impaled the object
     *
     * <p>Returns {@code null} if the current impalable was never hit by a projectile, or
     * {@code impaledEntity == null && impaledTile == null}</p>
     *
     * @return the last projectile that impaled the object
     */
    Projectile projectile();
}
