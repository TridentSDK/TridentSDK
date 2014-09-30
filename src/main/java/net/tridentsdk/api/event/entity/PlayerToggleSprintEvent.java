package net.tridentsdk.api.event.entity;

import net.tridentsdk.api.entity.living.Player;
import net.tridentsdk.api.event.Cancellable;
import net.tridentsdk.api.event.player.PlayerEvent;

/**
 * Called when a player tries to start sprinting, even if hunger would otherwise prevent them from sprinting
 */
public class PlayerToggleSprintEvent extends PlayerEvent implements Cancellable {
    private final boolean toggle;
    private boolean cancelled;

    public PlayerToggleSprintEvent(Player player, boolean toggle) {
        super(player);
        this.toggle = toggle;
    }

    /**
     *
     * @return true if the player is trying to sprint
     */
    public boolean sprintOn() {
        return toggle;
    }

    /**
     *
     * @return true if the player is trying to stop sprinting
     */
    public boolean sprintOff() {
        return !toggle;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
