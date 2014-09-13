package net.tridentsdk.api.entity;

/**
 * Represents a Ghast
 * 
 * @author TigerReborn
 */
public interface Ghast extends Hostile {
    
    /**
     * Gets the explosion radius used when a Fireball launched by this Ghast explodes
     * 
     * @return this Ghast's fireball explosion radius
     */
    float getFireballRadius();

}
