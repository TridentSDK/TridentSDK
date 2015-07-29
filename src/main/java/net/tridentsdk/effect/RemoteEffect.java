package net.tridentsdk.effect;

import net.tridentsdk.entity.living.Player;
import net.tridentsdk.util.Vector;

/**
 * Represents effects that can be executed without an entity
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 */
public interface RemoteEffect<T> extends Effect<T> {

    /**
     * Execute the effect at the given location for all nearby players
     *
     * @param vector The vector of the effect
     */
    void apply(Vector vector);

    /**
     * Execute the effect at the given location for specified player
     *
     * @param player The player to send the effect to
     * @param vector The vector of the effect
     */
    void apply(Player player, Vector vector);

    /**
     * Set the position of the effect
     *
     * @param vector The vector of the effect
     */
    void setPosition(Vector vector);

}
