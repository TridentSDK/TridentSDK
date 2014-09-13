package net.tridentsdk.api.entity;

/**
 * Represents a Hopper Minecart
 * 
 * @author TigerReborn
 */
public interface HopperMinecart extends MinecartBase, InventoryHolder {
    
    /**
     * Gets the transfer cooldown of this Hopper Minecart
     * 
     * @return
     */
    int getTransferCooldown();
    
    /**
     * Sets the transfer cooldown of this Hopper Minecart
     * 
     * @param cooldown
     */
    void setTransferCooldown(int cooldown);

}
