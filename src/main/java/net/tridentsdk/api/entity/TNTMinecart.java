package net.tridentsdk.api.entity;

/**
 * Represents a TNT Minecart
 * 
 * @author TigerReborn
 */
public interface TNTMinecart extends MinecartBase{
    
    /**
     * Gets the fuse time for this TNT Minecart
     * 
     * @return this TNT Minecart's fuse time
     */
    int getFuseTime();
    
    /**
     * Sets the fuse time for this TNT Minecart
     * 
     * @param time the time to set
     */
    void setFuseTime(int time);

}
