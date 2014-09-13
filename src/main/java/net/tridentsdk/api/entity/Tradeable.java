package net.tridentsdk.api.entity;

import java.util.Collection;

import net.tridentsdk.api.trade.Trade;

/**
 * Represents an entity that can trade with the Player
 * 
 * @author TigerReborn
 */
public interface Tradeable {
	
	/**
	 * The trades this entity offers
	 * 
	 * @return the trades offered by this entity
	 */
	Collection<Trade> getTrades();

}
