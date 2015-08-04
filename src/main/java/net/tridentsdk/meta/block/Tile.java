package net.tridentsdk.meta.block;

import net.tridentsdk.entity.living.Player;

/**
 * Represents an updatable block that contains special data
 *
 * @author The TridentSDK TeaM
 * @since 0.4-alpha
 */
public interface Tile {
    /**
     * Causes the tile to be updated to the player provided
     *
     * @param player the player to update
     */
    void update(Player player);

    /**
     * Performs the tick logic for the tile
     */
    default void tick() {
    }
}
