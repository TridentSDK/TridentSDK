package net.tridentsdk.api.entity;

import net.tridentsdk.api.inventory.ItemStack;

/**
 * Represents an entity that can be equipped
 * 
 * @author TigerReborn
 */
public interface Equippable extends Entity{
    
    /**
     * This entity's equipment
     * 
     * @return this entity's equipment
     */
    ItemStack[] getEquipment();
}
