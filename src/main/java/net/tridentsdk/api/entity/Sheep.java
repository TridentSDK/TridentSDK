package net.tridentsdk.api.entity;

/**
 * Represents a Sheep
 * 
 * @author TigerReborn
 */
public interface Sheep extends Ageable, Peaceful {
	
	/**
	 * The color of this sheep's wool
	 * 
	 * @return the color of this sheep's wool
	 */
	Object getColor();  /* TODO: Decide valid implementation of color for Sheep/Wool */
	
	/**
	 * Whether or not this sheep can be sheared
	 * 
	 * @return whether or not this sheep can be sheared
	 */
	boolean isShearable();

}
