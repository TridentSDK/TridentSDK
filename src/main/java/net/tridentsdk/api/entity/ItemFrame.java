package net.tridentsdk.api.entity;

import net.tridentsdk.api.inventory.ItemStack;

/**
 * Represents an ItemFrame
 * 
 * @author TigerReborn
 */
public interface ItemFrame extends Hanging{
    
    /**
     * Get the current ItemStack this ItemFrame has
     * 
     * @return the current ItemStack this ItemFrame has
     */
    ItemStack getCurrentItem();
    
    /**
     * Get the rotation of this ItemFrame's ItemStack
     * This is the number of times this has been rotated 45 degrees
     * 
     * @return the rotation of this ItemFrame's ItemStack
     */
    byte getItemStackRotation();

}
