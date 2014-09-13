package net.tridentsdk.api.entity;

/**
 * Represents a Command Block Minecart
 * 
 * @author TigerReborn
 */
public interface CommandMinecart extends MinecartBase {
    
    /**
     * Gets the BlockState that represents this Minecart's command block
     * 
     * @return the state of this Minecart's command block
     */
    Object getCommandBlockState();  /* TODO: Replace return type to valid implementation of BlockState */

}
