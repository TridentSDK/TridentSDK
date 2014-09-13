package net.tridentsdk.api.entity;

/**
 * Represents a living entity that can wear a saddle
 * 
 * @author TigerReborn
 */
public interface Saddleable extends LivingEntity {
	
	/**
	 * Whether this entity is saddled or not
	 * 
	 * @return whether or not this entity has a saddle
	 */
	boolean isSaddled();
	
	/**
	 * Set whether or not this entity is saddled
	 * 
	 * @param saddled whether this entity should be saddled or not
	 */
	void setSaddled(boolean saddled);
	
}
