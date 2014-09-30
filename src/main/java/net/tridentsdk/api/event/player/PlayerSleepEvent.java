package net.tridentsdk.api.event.player;

import net.tridentsdk.api.Block;
import net.tridentsdk.api.entity.living.Player;
import net.tridentsdk.api.event.Cancellable;

/**
 * Called when a player tries to get in a bed
 */
public class PlayerSleepEvent extends PlayerEvent implements Cancellable {
    private final Block bed;
    private boolean cancelled;

    public PlayerSleepEvent(Player player, Block bed) {
        super(player);
        this.bed = bed;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    /**
     * Gets the bed that the player tried to enter
     * @return
     */
    public Block getBed() {
        return bed;
    }
}
