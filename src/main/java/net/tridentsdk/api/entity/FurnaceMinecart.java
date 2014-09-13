package net.tridentsdk.api.entity;

/**
 * Represents a Furnace Minecart
 * 
 * @author TigerReborn
 */
public interface FurnaceMinecart extends Vehicle, InventoryHolder {
    
    /**
     * The number of fuel ticks this Furnace Minecart has
     * 
     * @return the number of fuel ticks
     */
    int getFuelTicks();
    
    /**
     * Set the number of fuel ticks this Furnace Minecart has
     * 
     * @param ticks the number of fuel ticks
     */
    void setFuelTicks(int ticks);
}
