package net.tridentsdk.api.entity;

/**
 * Represents a Primed TNT
 * 
 * @author TigerReborn
 */
public interface PrimeTNT extends FallingBlock {
    
    /**
     * The number of ticks until this Primed TNT explodes
     * 
     * @return the number of ticks until this Primed TNT explodes
     */
    int getFuseTicks();
    
    /**
     * Sets the number of fuse ticks
     * 
     * @param ticks the number of ticks to set
     */
    void setFuseTicks(int ticks);
}
