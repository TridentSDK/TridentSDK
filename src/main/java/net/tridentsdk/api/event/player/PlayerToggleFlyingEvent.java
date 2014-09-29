package net.tridentsdk.api.event.player;

import net.tridentsdk.api.entity.living.Player;
import net.tridentsdk.api.event.Cancellable;

/**
 * Called when a player attempts to change their flying state i.e. double-taps jump
 */
public class PlayerToggleFlyingEvent extends PlayerEvent implements Cancellable {

    private final boolean toggleState;
    private boolean cancelled;

    public PlayerToggleFlyingEvent(Player player, boolean toggleState) {
        super(player);
        this.toggleState = toggleState;
    }

    /**
     * Returns the state the player is attempting to toggle into, true for flying, false for not
     * @return
     */
    public boolean getToggleState() {
        return toggleState;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
