package net.tridentsdk.effect;

import net.tridentsdk.base.Position;
import net.tridentsdk.entity.living.Player;

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
     * @param position The position of the effect
     */
    void apply(Position position);

    /**
     * Execute the effect at the given location for specified player
     *
     * @param player The player to send the effect to
     * @param position The position of the effect
     */
    void apply(Player player, Position position);

}
