package net.tridentsdk.api.entity;

import java.util.UUID;

/**
 * Represents a tameable entity
 * 
 * @author TigerReborn
 */
public interface Tameable extends Ageable{
	
	/**
	 * Whether or not this entity is tamed
	 * 
	 * @return whether or not this entity is tamed
	 */
	boolean isTamed();
	
	/**
	 * The UUID of this entity's owner
	 * 
	 * @return the UUID of the {@link net.tridentsdk.api.entity.Player}Player that owns this entity, {@code null} if untamed
	 */
	UUID getOwner();
	
	/**
	 * Whether or not this entity is sitting
	 * 
	 * @return whether or not this entity is sitting
	 */
	boolean isSitting();

}
