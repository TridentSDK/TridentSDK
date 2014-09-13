package net.tridentsdk.api.entity;

import net.tridentsdk.api.Block;

/**
 * Represents a hanging entity
 * 
 * @author TigerReborn
 */
public interface Hanging extends Entity {
    
    /**
     * The block this entity was placed on
     * 
     * @return the Block this entity was placed on
     */
    Block getBlockPlacedOn();

}
