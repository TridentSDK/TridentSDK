package net.tridentsdk.api.entity;

/**
 * Represents an Experience Orb
 * 
 * @author TigerReborn
 */
public interface ExperienceOrb extends Entity{
    
    /**
     * Sets the age of this Experience Orb entity
     * 
     * @param age the age to set
     */
    void setAge(int age);
    
    /**
     * Represents the age of this Experience Orb entity
     * 
     * @return the age of this Experience Orb entity
     */
    int getAge();
    
    /**
     * Represents the health of this Experience Orb entity.
     * 
     * @return the health of this Experience Orb entity
     */
    short getHealth();
    
    /**
     * Sets the health for this Experience Orb entity
     * 
     * @param health the value to set the health to
     */
    void setHealth(short health);
    
}
