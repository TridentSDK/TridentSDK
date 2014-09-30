package net.tridentsdk.api.event.player;

import net.tridentsdk.api.Gamemode;
import net.tridentsdk.api.entity.living.Player;
import net.tridentsdk.api.event.Cancellable;

/**
 * Called <i>before</i> a Player's game mode changes
 */
public class PlayerGameModeChangeEvent extends PlayerEvent implements Cancellable {
    private Gamemode gameMode;

    private boolean cancelled;

    public PlayerGameModeChangeEvent(Player player, Gamemode gameMode) {
        super(player);
        this.gameMode = gameMode;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public Gamemode getNewGameMode() {
        return gameMode;
    }

    public Gamemode getCurrentGameMode() {
        return getPlayer().getGameMode();
    }

    public void setNewGamemode(Gamemode gameMode) {
        this.gameMode = gameMode;
    }
}
