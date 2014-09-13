package net.tridentsdk.api.entity;

/**
 * Represents a Horse
 * 
 * @author TigerReborn
 */
public interface Horse extends Tameable, Saddleable, InventoryHolder, Peaceful {
	
	/**
	 * What breed of horse this is
	 * 
	 * @return the HorseType that represents this breed
	 */
	HorseType getBreed();
	
	/**
	 * Whether or not this horse is grazing
	 * 
	 * @return if this horse is grazing or not
	 */
    boolean isGrazing();
    
    /**
     * The temper of this horse, higher temper dictates that the horse is easier to tame
     * 
     * @return the temper of this horse. Range of 0-100
     */
    int getTemper();
    
    /**
     * Whether or not this horse has a chest
     * 
     * @return false if this horse's breed is not a donkey or mule, or this horse has no chest
     */
    boolean hasChest();
    
    /**
     * The variant of this horse, will return an invalid variant if this horse is not of a HORSE breed
     * 
     * @return the variant of this horse
     */
    HorseVariant getVariant();
}
