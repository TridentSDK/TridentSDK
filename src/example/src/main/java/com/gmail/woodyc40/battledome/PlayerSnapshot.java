package com.gmail.woodyc40.battledome;

import net.tridentsdk.Position;
import net.tridentsdk.entity.living.Player;

import java.util.UUID;

/**
 * Stores the current state of the player to be restored later
 *
 * @author Pierre C
 */
public class PlayerSnapshot {
    private final int gameId;
    private final Position home;
    private final UUID uuid;

    private PlayerSnapshot(Player player, int gameId) {
        this.gameId = gameId;
        this.home = player.getPosition();
        this.uuid = player.getUniqueId();
    }

    public static PlayerSnapshot take(Player player, int gameId) {
        return new PlayerSnapshot(player, gameId);
    }

    public int gameId() {
        return this.gameId;
    }

    public UUID uuid() {
        return this.uuid;
    }

    public void restore(Player player) {
        player.teleport(this.home);
    }
}
