package net.tridentsdk.api.event.player;

import net.tridentsdk.api.entity.living.Player;
import net.tridentsdk.api.world.World;

/**
 * Called when a player changes worlds
 */
public class PlayerChangeWorldEvent extends PlayerEvent {
    private final World to;
    private final World from;

    public PlayerChangeWorldEvent(Player player, World from, World to) {
        super(player);
        this.to = to;
        this.from = from;
    }

    public World getTo() {
        return to;
    }

    public World getFrom() {
        return from;
    }
}
