package net.tridentsdk.api.entity;

import net.tridentsdk.api.inventory.FireworkProperties;

public interface Firework extends Entity {
    
    /**
     * Get the number of ticks that this Firework will explode at
     * 
     * @return the number of ticks that this will explode at
     */
    int getLaunchTicks();
    
    /**
     * Returns the properties of the Firework launched
     * 
     * @return the properties of the firework launched
     */
    FireworkProperties getFirework();

}
