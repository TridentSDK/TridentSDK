package net.tridentsdk.api.entity;

import net.tridentsdk.api.util.PartRotation;

/**
 * Represents an Armor Stand
 * 
 * @author TigerReborn
 */
public interface ArmorStand extends Equippable {
    
    /**
     * Gets the slot properties of this Armor Stand
     * 
     * @return this armor stand's slot properties
     */
    SlotProperties getSlotProperties();
    
    /**
     * Whether or not this Armor Stand is invisible
     * 
     * @return whether or not this Armor Stand is invisible
     */
    boolean isInvisible();
    
    /**
     * Whether or not this Armor Stand should display its baseplate
     * 
     * @return whether or not this Armor Stand should display its baseplate
     */
    boolean displayBaseplate();
    
    /**
     * Whether or not this Armor Stand should display its arms
     * 
     * @return whether or not this Armor Stand should display its arms
     */
    boolean displayArms();
    
    /**
     * Whether or not this Armor Stand will fall or not
     * 
     * @return whether or not this Armor Stand will fall or not
     */
    boolean useGravity();
    
    /**
     * Returns the pose for this Armor Stand
     * 
     * @return the post of this Armor Stand
     * @deprecated Uses magic numbers for indexing, exists until another way is pushed
     */
    @Deprecated
    PartRotation[] getPose();
    
    /**
     * Whether or not this Armor Stand is small
     * 
     * @return whether or not this Armor Stand is small
     */
    boolean isTiny();

}
