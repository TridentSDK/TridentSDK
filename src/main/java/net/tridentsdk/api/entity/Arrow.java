package net.tridentsdk.api.entity;

/**
 * Represents an Arrow
 * 
 * @author TigerReborn
 */
public interface Arrow extends Projectile {
    
    /**
     * Represents whether or not you can pickup this Arrow
     * 
     * @return whether you can pickup this arrow or not
     */
    boolean canPickup();

}
