package net.tridentsdk.api.event.player;

import net.tridentsdk.api.entity.Player;

public class PlayerJoinEvent extends PlayerEvent{

	/**
	 * 
	 * @param player the player associated with this event
	 */
	
	public PlayerJoinEvent(final Player player) {
		super(player);
	}

}
