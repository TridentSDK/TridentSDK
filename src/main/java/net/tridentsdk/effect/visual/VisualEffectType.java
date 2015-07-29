package net.tridentsdk.effect.visual;

/**
 * Enum of all possible visual effects
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 */
public enum VisualEffectType {

    /**
     * Spawns 10 smoke particles
     */
    FIRE_SMOKE(2000),

    /**
     * Block Break Particles
     */
    BLOCK_BREAK(2001),

    /**
     * Splash Potion Particles
     */
    SPLASH_POTION(2002),

    /**
     * Eye Of Ender Break Animation
     */
    EYE_BREAK(2003),

    /**
     * Mob Spawn Particle Effect (Smoke + Flames)
     */
    MOB_SPAWN(2004),

    /**
     * Happy Villager Effect (Green Crosses)
     */
    HAPPY_VILLAGER(2005);

    private final int data;

    VisualEffectType(int data) {
        this.data = data;
    }

    /**
     * Get the data value of the effect
     *
     * @return The data value of the effect
     */
    public int data(){
        return data;
    }

}
