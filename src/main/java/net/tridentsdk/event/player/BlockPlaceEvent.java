package net.tridentsdk.event.player;

import lombok.Getter;
import lombok.Setter;
import net.tridentsdk.base.Block;
import net.tridentsdk.entity.living.Player;
import net.tridentsdk.event.Cancellable;
import net.tridentsdk.inventory.Item;

import javax.annotation.concurrent.NotThreadSafe;

/**
 * This event is called whenever specifically a player puts
 * down a block.
 */
@NotThreadSafe
public class BlockPlaceEvent extends PlayerEvent implements Cancellable {
    /**
     * The previous block occupying the spot to place the
     * block
     */
    @Getter
    private final Block previous;
    /**
     * The item in the player's hand that will be placed
     */
    @Getter
    private final Item itemToPlace;

    @Setter
    @Getter
    private boolean cancelled;

    public BlockPlaceEvent(Player player, Block previous, Item place) {
        super(player);
        this.previous = previous;
        this.itemToPlace = place;
    }
}