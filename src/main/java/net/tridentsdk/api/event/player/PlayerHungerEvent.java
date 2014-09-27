package net.tridentsdk.api.event.player;

import net.tridentsdk.api.entity.Item;
import net.tridentsdk.api.entity.living.Player;
import net.tridentsdk.api.event.Cancellable;

/**
 * Called when a Player's hunger level changes
 */
public class PlayerHungerEvent extends PlayerEvent implements Cancellable {
    private double feed;
    private final Item food;
    private boolean cancelled;

    public PlayerHungerEvent(Player player, Item food, double feed) {
        super(player);
        this.food = food;
        this.feed = feed;
    }

    public double getFeed() {
        return feed;
    }

    public void setFeed(double feed) {
        this.feed = feed;
    }

    public Item getFood() {
        return food;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
}
