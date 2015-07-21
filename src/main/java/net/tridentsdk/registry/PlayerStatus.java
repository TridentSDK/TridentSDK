package net.tridentsdk.registry;

import java.net.InetSocketAddress;
import java.util.UUID;

/**
 * Represents players with a special status, such as banned, ip-banned, operator, or whitelisted
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 */
public interface PlayerStatus {
    /**
     * Checks the ban status of the player with the given UUID
     *
     * @param uuid the uuid to check
     * @return {@code true} if the player is banned
     */
    boolean isBanned(UUID uuid);

    /**
     * Checks if the IP has been banned from the server
     *
     * @param address the IP to check
     * @return {@code true} if the IP has been banned
     */
    boolean isIpBanned(InetSocketAddress address);

    /**
     * Checks if the player owning the UUID has been given operator status
     *
     * @param uuid the uuid to check
     * @return {@code true} if the UUID is opped
     */
    boolean isOpped(UUID uuid);

    /**
     * Checks if the player owning the UUID is whitelisted
     *
     * @param uuid the uuid to check
     * @return {@code true} if the player is whitelisted
     */
    boolean isWhitelisted(UUID uuid);

    /**
     * Bans the player given the UUID
     *
     * @param uuid the uuid of the player to ban
     */
    void ban(UUID uuid);

    /**
     * IP bans the given address
     *
     * @param address the IP to ban
     */
    void ipBan(InetSocketAddress address);

    /**
     * Ops the given UUID
     *
     * @param uuid the UUID to give op to
     */
    void op(UUID uuid);

    /**
     * Whitelists the UUID provided
     *
     * @param uuid the to give operator status
     */
    void whitelist(UUID uuid);
}
