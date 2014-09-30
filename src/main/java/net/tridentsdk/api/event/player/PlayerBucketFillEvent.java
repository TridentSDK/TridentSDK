package net.tridentsdk.api.event.player;

import net.tridentsdk.api.Block;
import net.tridentsdk.api.BlockFace;
import net.tridentsdk.api.entity.living.Player;
import net.tridentsdk.api.event.Cancellable;
import net.tridentsdk.api.event.block.BlockBreakEvent;
import net.tridentsdk.api.inventory.ItemStack;

/**
 * Called when a player fills a bucket
 */
public class PlayerBucketFillEvent extends BlockBreakEvent implements Cancellable {

    public PlayerBucketFillEvent(Player player, Block block, BlockFace blockFace, ItemStack itemInHand) {
        super(player, block, blockFace, itemInHand);
    }
}
