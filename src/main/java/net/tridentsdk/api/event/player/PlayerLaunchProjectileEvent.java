package net.tridentsdk.api.event.player;

import net.tridentsdk.api.entity.Entity;
import net.tridentsdk.api.entity.Projectile;
import net.tridentsdk.api.entity.living.Player;
import net.tridentsdk.api.event.Cancellable;

/**
 * Called when a player launches a projectile e.g. an arrow or snowball
 */
public class PlayerLaunchProjectileEvent extends PlayerEvent implements Cancellable {
    private final Entity projectile;

    private boolean cancelled;

    public PlayerLaunchProjectileEvent(Player player, Projectile projectile) {
        super(player);
        this.projectile = projectile;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public Entity getProjectile() {

        return projectile;
    }
}
