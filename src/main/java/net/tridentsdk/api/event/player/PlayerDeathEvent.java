package net.tridentsdk.api.event.player;

import net.tridentsdk.api.entity.living.Player;

public class PlayerDeathEvent extends PlayerEvent{

	/**
	 * TODO add cause of death
	 * 
	 * @param player the player associated with this event (that died)
	 */
	
	public PlayerDeathEvent(final Player player) {
		super(player);
	}

}
