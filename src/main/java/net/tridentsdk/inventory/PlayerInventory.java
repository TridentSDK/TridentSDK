package net.tridentsdk.inventory;

import javax.annotation.Nonnull;

/**
 * Represents an inventory that is held by a player.
 *
 * @author TridentSDK
 * @since 0.4-alpha
 */
public interface PlayerInventory extends Inventory {
    /**
     * Obtains the item in the player's main hand.
     *
     * @return the item in the main hand, or an
     * {@link net.tridentsdk.base.Substance#AIR} item if it
     * doesn't hold anything
     */
    @Nonnull
    Item getHeldItem();

    /**
     * Obtains the item in the player's off hand, or
     * otherwise the non-main hand.
     *
     * @return the item in the non-main hand, or an
     * {@link net.tridentsdk.base.Substance#AIR} item if it
     * doesn't hold anything
     */
    @Nonnull
    Item getOffHeldItem();

    /**
     * Returns a number 0-8 left to right depending on
     * whichever hotbar slot the player has selected.
     *
     * @return the selected hotbar slot
     */
    int getSelectedSlot();
}