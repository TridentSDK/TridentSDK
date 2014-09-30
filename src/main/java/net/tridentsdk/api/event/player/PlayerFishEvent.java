package net.tridentsdk.api.event.player;

import net.tridentsdk.api.entity.living.Player;
import net.tridentsdk.api.event.Cancellable;
import net.tridentsdk.api.inventory.ItemStack;

/**
 * Called when a player's fishing state changes,e.g. throws line, catches a fish, catches an entity, etc.
 */
public class PlayerFishEvent extends PlayerEvent implements Cancellable {
    private final State state;
    private int exp;
    private ItemStack item;
    private boolean cancelled;

    public PlayerFishEvent(Player player, State state, int exp, ItemStack item) {
        super(player);
        this.state = state;
        this.exp = exp;
        this.item = item;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public State getState() {
        return state;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public ItemStack getItem() {
        return item;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }

    public enum State {
        FISHING,
        FAILED_ATTEMPT,
        CAUGHT_FISH,
        CAUGHT_ENTITY,
        IN_GROUND
    }
}
