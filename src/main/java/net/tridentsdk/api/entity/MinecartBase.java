package net.tridentsdk.api.entity;

/**
 * Represents the generic Minecart
 * 
 * @author TigerReborn
 */
public interface MinecartBase extends Vehicle {
    
    /**
     * Represents this Minecart's display tile, in the form of a BlockState
     * 
     * @return the display tile of this Minecart
     */
    Object getDisplayTile();    /* TODO: Change return type to valid implementation of BlockState */
    
    /**
     * Set this Minecart's display tile to the specified block state
     * 
     * @param blockState the state to set this to
     */
    void setDisplayTile(Object blockState);    /* TODO: Change param type to valid implementation of BlockState */
    
    /**
     * Get the offset for this Minecart's display tile
     * 
     * @return the offset for this Minecart's display tile
     */
    int getDisplayTileOffset();
    
    /**
     * Set the offset for this Minecart's display tile
     * 
     * @param offset the offset to set
     */
    void setDisplayTileOffset(int offset);
    
    /**
     * Gets the custom name of this Minecart
     * 
     * @return the custom name of this Minecart
     */
    String getName();
    
    /**
     * Sets the custom name of this Minecart
     * 
     * @param name the new value of the custom name
     */
    void setName(String name);

}
