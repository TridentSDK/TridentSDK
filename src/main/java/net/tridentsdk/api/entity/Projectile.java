package net.tridentsdk.api.entity;

import net.tridentsdk.api.Block;

/**
 * Represents a Projectile
 * 
 * @author TigerReborn
 */
public interface Projectile extends Entity {
    
    /**
     * Represents the shooter of this Projectile, if applicable
     * 
     * @return the shooter of this Projectile
     */
    Entity getProjectileSource();
    
    /**
     * Represents the current tile (Block) that this Projectile is located in
     * 
     * @return the current tile this Projectile is in
     */
    Block getCurrentTile();
}
