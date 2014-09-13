package net.tridentsdk.api.entity;

/**
 * Represents a Chicken
 * 
 * @author TigerReborn
 */
public interface Chicken extends Ageable, Peaceful {
	
	/**
	 * Whether or not this Chicken is a 'Chicken Jockey', 
	 * defined by whether or not this Chicken will naturally despawn
	 * 
	 * @return whether or not this Chicken is a 'Chicken Jockey'
	 */
	boolean isChickenJockey();
	
	/**
	 * Ticks until this Chicken will lay its egg
	 * 
	 * @return the number of ticks until this Chicken will lay an egg
	 */
	int getEggTicks();
}
