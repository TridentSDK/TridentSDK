package net.tridentsdk.api.trade;

import net.tridentsdk.api.inventory.ItemStack;

/**
 * Represents a Trade offered by an {@link net.tridentsdk.api.entity.Tradeable}
 * 
 * @author TigerReborn
 */
public interface Trade {

	/**
	 * Whether or not this trade rewards xp
	 * 
	 * @return Whether or not this trade should reward xp
	 */
	boolean rewardExp();
	
	/**
	 * How many times this trade can be fulfilled
	 * 
	 * @return the number of times this trade can be fulfilled
	 */
	int maxUses();
	
	/**
	 * How many times this trade has been fulfilled
	 * 
	 * @return how many times this trade has been fulfilled
	 */
	int uses();
	
	/**
	 * The itemstack given as a result of this trade
	 * 
	 * @return the itemstack that is given as a result of fulfilling this trade
	 */
	ItemStack offer();
	
	/**
	 * The itemstacks required to be input in order to fulfilled this trade
	 * This accepts both implementations of ItemPair, including a null second offer
	 * 
	 * @return the input that will fulfill this trade
	 */
	ItemPair input();
	
}
