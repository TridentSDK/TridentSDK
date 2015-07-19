package net.tridentsdk.registry;

import net.tridentsdk.entity.living.Player;

import java.util.UUID;

/**
 * The player registry
 *
 * @author The TridentSDK Team
 */
public interface Players extends Registry<Player> {
    /**
     * Obtains a player from the player's UUID
     *
     * @param uuid the player's uuid
     * @return the player with that UUID, or null if the player is not online
     */
    Player fromUuid(UUID uuid);

    /**
     * Obtains a potentially offline player
     *
     * @param uuid the uuid to find
     * @return a non-null player that could be offline
     */
    Player offline(UUID uuid);
}
