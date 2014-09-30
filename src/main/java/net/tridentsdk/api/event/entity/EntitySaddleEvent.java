package net.tridentsdk.api.event.entity;

import net.tridentsdk.api.entity.Entity;
import net.tridentsdk.api.entity.living.Player;
import net.tridentsdk.api.event.Cancellable;

/**
 * Called whenever a player saddles a pig
 */
public class EntitySaddleEvent extends EntityEvent implements Cancellable {
    private boolean cancelled;

    private final Player player;

    public EntitySaddleEvent(Entity entity, Player player) {
        super(entity);
        this.player = player;
    }

    /**
     * Gets the player that saddled this pig
     * @return
     */
    public Player getPlayer() {
        return player;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
