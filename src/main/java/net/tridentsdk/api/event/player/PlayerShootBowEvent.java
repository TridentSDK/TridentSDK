package net.tridentsdk.api.event.player;

import net.tridentsdk.api.entity.living.Player;
import net.tridentsdk.api.entity.projectile.Arrow;
import net.tridentsdk.api.event.Cancellable;

/**
 * Called when a player shoots a bow
 */
public class PlayerShootBowEvent extends PlayerLaunchProjectileEvent implements Cancellable {
    public PlayerShootBowEvent(Player player, Arrow projectile) {
        super(player, projectile);
    }

    public Arrow getArrow() {
        return (Arrow) getProjectile();
    }


}
