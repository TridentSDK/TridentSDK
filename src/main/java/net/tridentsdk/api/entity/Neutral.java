package net.tridentsdk.api.entity;

/**
 * Represents a neutral entity
 * Purpose of interface is to provide ease-of-access to large groups of a single type
 * (i.e. 'Hostiles', 'Friendlies')
 * 
 * @author TigerReborn
 */
public interface Neutral extends LivingEntity{
    
    /**
     * Whether or not this entity has been angered. Note, not all neutral entities can be angered.
     * When an entity is angered, it is considered hostile
     * 
     * return Whether this entity is angered or not
     */
    boolean isHostile();

}
