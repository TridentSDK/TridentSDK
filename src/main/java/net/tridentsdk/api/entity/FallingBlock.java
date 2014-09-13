package net.tridentsdk.api.entity;

/**
 * Represents a dynamic tile
 * 
 * @author TigerReborn
 */
public interface FallingBlock extends Entity {
    
    /**
     * The state this FallingBlock represents
     * 
     * @return the BlockState of this falling block
     */
    Object getState();  /* TODO: Change return type to valid implementation of BlockState */
    
    /**
     * Whether or not this FallingBlock should drop when it breaks
     * 
     * @return whether or not this FallingBlock should drop its item when it breaks
     */
    boolean shouldDrop();
}
