package net.tridentsdk.api.entity;

/**
 * Represents an Enderman
 * 
 * @author TigerReborn
 */
public interface Enderman extends Neutral{
    
    /**
     * Get the block that this enderman is currently carrying
     * 
     * @return the block that this entity is carrying
     */
    Object getBlockCarried();   /* TODO: Replace Object with valid implementation of BlockState */
    
    /**
     * Gets the number of endermites spawned by this enderman. Affects spawn chance of other endermites
     * 
     * @return the number of endermites spawned by this enderman
     */
    int getEndermiteCount();

}
