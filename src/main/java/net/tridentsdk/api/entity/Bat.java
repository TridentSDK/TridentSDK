package net.tridentsdk.api.entity;

/**
 * Represents a Bat
 * 
 * @author TigerReborn
 */
public interface Bat extends Neutral {
    
    /**
     * Whether or not the bat is hanging
     * 
     * @return whether or not the bat is hanging
     */
    boolean isHanging();
    
    /**
     * Whether or not the bat is flying
     * 
     * @return whether or not the bat is flying
     */
    boolean isFlying();
}
