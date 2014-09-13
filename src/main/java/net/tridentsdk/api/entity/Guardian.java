package net.tridentsdk.api.entity;

/**
 * Represents a Guardian
 * 
 * @author TigerReborn 
 */
public interface Guardian extends Hostile{
    
    /**
     * Whether this Guardian is an Elder Guardian or not
     * 
     * @return Whether or not this Guardian is an Elder Guardian
     */
    boolean isElder();
}
