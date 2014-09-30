package net.tridentsdk.api.event.player;

import net.tridentsdk.api.Block;
import net.tridentsdk.api.BlockFace;
import net.tridentsdk.api.entity.living.Player;
import net.tridentsdk.api.event.Cancellable;
import net.tridentsdk.api.event.block.BlockPlaceEvent;
import net.tridentsdk.api.inventory.ItemStack;

/**
 * Called when a player empties a bucket
 */
public class PlayerBucketEmptyEvent extends BlockPlaceEvent implements Cancellable {

    private final ItemStack blockInHand;

    public PlayerBucketEmptyEvent(Player player, Block block, Block blockClicked,
                                  BlockFace faceClicked, ItemStack blockInHand) {
        super(player, block, blockClicked, faceClicked);
        this.blockInHand = blockInHand;
    }

    public ItemStack getBlockInHand() {
        return blockInHand;
    }
}
