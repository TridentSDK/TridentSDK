package net.tridentsdk.api.entity;

/**
 * Represents a Wolf
 * 
 * @author TigerReborn
 */
public interface Wolf extends Tameable, Neutral{
    
    /**
     * Whether or not this entity is angry
     * 
     * @return whether or not this entity is angry
     */
    boolean isAngry();
    
    /**
     * The color of this entity's collar
     * 
     * @return the color of this entity's collar
     */
    Object getCollarColor();    /* TODO: Decide valid implementation for this along with Sheep and Wool  */
}
