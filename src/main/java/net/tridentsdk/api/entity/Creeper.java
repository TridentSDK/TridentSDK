package net.tridentsdk.api.entity;

/**
 * Represents a Creeper
 * 
 * @author TigerReborn
 */
public interface Creeper extends Hostile{
    
    /**
     * Whether or not this creeper is powered (Struck by lightning)
     * 
     * @return whether or not this creeper is powered
     */
    boolean isPowered();
    
    /**
     * Gets this creeper's explosion radius
     * 
     * @return this creeper's explosion radius
     */
    float getExplosionRadius();
    
    /**
     * Set whether or not this creeper is powered
     * 
     * @param powered whether the creeper should be powered or not
     */
    void setPowered(boolean powered);
    
    /**
     * Sets this creeper's explosion radius
     * 
     * @param rad radius to change to
     */
    void setExplosionRadius(float rad);
}
