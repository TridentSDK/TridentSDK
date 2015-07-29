package net.tridentsdk.effect.particle;

/**
 * Enum of all possible particle effects
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 */
public enum ParticleEffectType {

    /**
     * Small Explosion
     */
    SMALL_EXPLOSION((byte) 0),

    /**
     * Large Explosion
     */
    LARGE_EXPLOSION((byte) 1),

    /**
     * Huge Explosion
     */
    HUGE_EXPLOSION((byte) 2),

    /**
     * Firework Spark
     */
    FIREWORK_SPARK((byte) 3),

    /**
     * Bubble
     */
    BUBBLE((byte) 4),

    /**
     * Water Splash
     */
    SPLASH((byte) 5),

    /**
     * Fishing Animation
     */
    WAKE((byte) 6),

    /**
     * Underwater Suspended
     */
    SUSPENDED((byte) 7),

    /**
     * Depth Suspended
     */
    DEPTH_SUSPENDED((byte) 8),

    /**
     * Critical Hit
     */
    CRIT((byte) 9),

    /**
     * Magical Crit
     */
    MAGIC_CRIT((byte) 10),

    /**
     * Small Smoke (like torches)
     */
    SMALL_SMOKE((byte) 11),

    /**
     * Large Smoke (like fire)
     */
    LARGE_SMOKE((byte) 12),

    /**
     * Splash Potion / Bottle O' Enchanting
     */
    SPELL((byte) 13),

    /**
     * Instant Health/Damage Potion
     */
    INSTANT_SPELL((byte) 14),

    /**
     * Status Effect / Trading / Wither
     */
    MOB_SPELL((byte) 15),

    /**
     * Beacon Effects
     */
    MOB_SPELL_AMBIENT((byte) 16),

    /**
     * Witch Effect
     */
    WITCH_MAGIC((byte) 17),

    /**
     * Dripping Water Through Blocks
     */
    DRIP_WATER((byte) 18),

    /**
     * Dripping Lava Through Blocks
     */
    DRIP_LAVA((byte) 19),

    /**
     * Angry Villager Cloud
     */
    ANGRY_VILLAGER((byte) 20),

    /**
     * Happy Villager / Bone Meal / Feeding Animal
     */
    HAPPY_VILLAGER((byte) 21),

    /**
     * Mycelium Effect
     */
    TOWN_AURA((byte) 22),

    /**
     * Note Block Note
     */
    NOTE((byte) 23),

    /**
     * Nether Portal / Endermen Effect
     */
    PORTAL((byte) 24),

    /**
     * Enchantment Table Glyphs
     */
    ENCHANTMENT_TABLE((byte) 25),

    /**
     * Flame
     */
    FLAME((byte) 26),

    /**
     * Small Lava Fireballs
     */
    LAVA((byte) 27),

    /**
     * Footsteps
     */
    FOOTSTEP((byte) 28),

    /**
     * Cloud Smoke Effect
     */
    CLOUD((byte) 29),

    /**
     * Active Redstone Effect
     */
    RED_DUST((byte) 30),

    /**
     * Snowball Poof
     */
    SNOWBALL_POOF((byte) 31),

    /**
     * Breaking Snow / Creating Snow Golem
     */
    SNOW_SHOVEL((byte) 32),

    /**
     * Slime Particles
     */
    SLIME((byte) 33),

    /**
     * Hearts From Breeding/Taming
     */
    HEART((byte) 34),

    /**
     * Barrier Block Sign
     */
    BARRIER((byte) 35),

    /**
     * Eating / Thrown Egg / Potion / Eye Of Ender / Breaking Tool
     */
    ICON_CRACK((byte) 36),

    /**
     * Breaking Blocks / Sprinting
     */
    BLOCK_CRACK((byte) 37),

    /**
     * Breaking Armor Stand / Falling
     */
    BLOCK_DUST((byte) 38),

    /**
     * Rain Droplets
     */
    DROPLET((byte) 39),

    /**
     * ...?
     */
    TAKE((byte) 40),

    /**
     * Elder Guardian Animation
     */
    MOB_APPEARANCE((byte) 41);

    private final byte data;

    ParticleEffectType(byte data) {
        this.data = data;
    }

    /**
     * Get the data value of the effect
     *
     * @return The data value of the effect
     */
    public byte data(){
        return data;
    }

}
