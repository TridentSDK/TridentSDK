package net.tridentsdk.api.entity;

/**
 * Represents a Zombie
 * 
 * @author TigerReborn
 */
public interface Zombie extends Hostile, Equippable {
    
    /**
     * Represents if this zombie is a Zombie Villager or not
     * 
     * @return whether or not this is a zombie villager
     */
    boolean isVillager();
    
    /**
     * Represents if this zombie is a baby zombie or not
     * 
     * @return whether or not this zombie is a baby zombie
     */
    boolean isBaby();

}
