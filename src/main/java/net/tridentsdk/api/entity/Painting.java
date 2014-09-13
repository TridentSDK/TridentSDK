package net.tridentsdk.api.entity;

/**
 * Represents a Painting
 * 
 * @author TigerReborn
 */
public interface Painting extends Hanging{
    
    /**
     * Get the motive of this Painting
     * 
     * @return the name of this Painting's motive
     */
    String getMotive();

}
