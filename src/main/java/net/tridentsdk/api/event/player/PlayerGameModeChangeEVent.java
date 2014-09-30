package net.tridentsdk.api.event.player;

import net.tridentsdk.api.GameMode;
import net.tridentsdk.api.entity.living.Player;
import net.tridentsdk.api.event.Cancellable;

/**
 * Called <i>before</i> a Player's game mode changes
 */
public class PlayerGameModeChangeEVent extends PlayerEvent implements Cancellable {
    private GameMode gameMode;

    private boolean cancelled;

    public PlayerGameModeChangeEVent(Player player, GameMode gameMode) {
        super(player);
        this.gameMode = gameMode;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public GameMode getNewGameMode() {
        return gameMode;
    }

    public GameMode getCurrentGameMode() {
        return getPlayer().getGameMode();
    }

    public void setNewGamemode(GameMode gameMode) {
        this.gameMode = gameMode;
    }
}
