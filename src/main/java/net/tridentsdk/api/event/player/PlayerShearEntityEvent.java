package net.tridentsdk.api.event.player;

import net.tridentsdk.api.entity.Item;
import net.tridentsdk.api.entity.LivingEntity;
import net.tridentsdk.api.entity.living.Player;
import net.tridentsdk.api.event.Cancellable;

/**
 * Called when a player shears an entity
 */
public class PlayerShearEntityEvent extends PlayerEvent implements Cancellable {
    private final LivingEntity sheared;
    private Item drop;
    private boolean cancelled;

    public PlayerShearEntityEvent(Player player, LivingEntity sheared, Item drop) {
        super(player);
        this.sheared = sheared;
        this.drop = drop;
    }

    /**
     * Gets the entity that was sheared
     * @return
     */
    public LivingEntity getSheared() {
        return sheared;
    }

    /**
     * Gets the item that shearing this entity will drop
     * @return
     */
    public Item getDrop() {
        return drop;
    }

    /**
     * Sets the item that shearing this entity will drop
     * @param drop
     */
    public void setDrop(Item drop) {
        this.drop = drop;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }


}
