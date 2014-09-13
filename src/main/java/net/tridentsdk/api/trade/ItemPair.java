package net.tridentsdk.api.trade;

import net.tridentsdk.api.inventory.ItemStack;

/**
 * Represents a pair of ItemStacks designated in a trade
 * 
 * @author TigerReborn
 */
public class ItemPair {
	
	private final ItemStack one, two;
	
	/**
	 * Construct a ItemPair with only one input ItemStack
	 * The second ItemStack will be considered null and thus will be treated as a single input when trading
	 * 
	 * @param one
	 */
	public ItemPair(ItemStack one){
		this(one, null);
	}
	
	/**
	 * Construct a ItemPair with two input ItemStacks
	 * When used in a trade, the pair will be treated as a double input when trading
	 * 
	 * @param one
	 */
	public ItemPair(ItemStack one, ItemStack two){
		this.one = one;
		this.two = two;
	}
	
	public ItemStack getFirstInput(){
		return one;
	}
	
	public ItemStack getSecondInput(){
		return two;
	}
	
}
