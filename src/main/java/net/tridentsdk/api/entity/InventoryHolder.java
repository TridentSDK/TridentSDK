package net.tridentsdk.api.entity;

import net.tridentsdk.api.inventory.*;

/**
 * Represents an Entity that holds an Inventory
 * 
 * @author TigerReborn
 */
public interface InventoryHolder extends Entity {
	
	/*
	 * TODO: Convert the return types into a valid representation of their respective objects
	 */
	
	/**
	 * The Inventory that this entity holds
	 * 
	 * @return the Inventory that this entity holds
	 */
	Inventory getInventory();
	
	/**
	 * The contents this slot
	 * 
	 *	@param slot the target slot
	 * @return the contents of the specified slot
	 */
	ItemStack getContents(int slot);
}
