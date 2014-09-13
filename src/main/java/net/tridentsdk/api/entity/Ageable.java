package net.tridentsdk.api.entity;
/**
 * Represents a LivingEntity that has an age and has the ability to bread
 * 
 * @author TigerReborn
 */
public interface Ageable extends LivingEntity {
	/**
	 * The current age of this entity, in ticks
	 * 
	 * @return the age of this entity
	 */
	int getAge();
	
	/**
	 * Set the current age of this entity, in ticks
	 * 
	 * @param ticks the age to set
	 */
	void setAge(int ticks);
	
	/**
	 * Whether or not this entity can breed or not, 
	 * where the ability to breed represents whether or not this entity can become "in love"
	 * 
	 * @return whether or not this entity can be bred
	 */
    boolean canBreed();
    
    /**
     * Whether or not this entity is "in love", 
     * such that it will actively display the particle effect for breeding hearts and search for a mate
     * 
     * @return whether or not this entity is in love
     */
    boolean isInLove();
}
