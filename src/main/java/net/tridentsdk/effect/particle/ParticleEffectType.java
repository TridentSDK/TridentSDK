/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2014 The TridentSDK Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
    SMALL_EXPLOSION(0),

    /**
     * Large Explosion
     */
    LARGE_EXPLOSION(1),

    /**
     * Huge Explosion
     */
    HUGE_EXPLOSION(2),

    /**
     * Firework Spark
     */
    FIREWORK_SPARK(3),

    /**
     * Bubble
     */
    BUBBLE(4),

    /**
     * Water Splash
     */
    SPLASH(5),

    /**
     * Fishing Animation
     */
    WAKE(6),

    /**
     * Underwater Suspended
     */
    SUSPENDED(7),

    /**
     * Depth Suspended
     */
    DEPTH_SUSPENDED(8),

    /**
     * Critical Hit
     */
    CRIT(9),

    /**
     * Magical Crit
     */
    MAGIC_CRIT(10),

    /**
     * Small Smoke (like torches)
     */
    SMALL_SMOKE(11),

    /**
     * Large Smoke (like fire)
     */
    LARGE_SMOKE(12),

    /**
     * Splash Potion / Bottle O' Enchanting
     */
    SPELL(13),

    /**
     * Instant Health/Damage Potion
     */
    INSTANT_SPELL(14),

    /**
     * Status Effect / Trading / Wither
     */
    MOB_SPELL(15),

    /**
     * Beacon Effects
     */
    MOB_SPELL_AMBIENT(16),

    /**
     * Witch Effect
     */
    WITCH_MAGIC(17),

    /**
     * Dripping Water Through Blocks
     */
    DRIP_WATER(18),

    /**
     * Dripping Lava Through Blocks
     */
    DRIP_LAVA(19),

    /**
     * Angry Villager Cloud
     */
    ANGRY_VILLAGER(20),

    /**
     * Happy Villager / Bone Meal / Feeding Animal
     */
    HAPPY_VILLAGER(21),

    /**
     * Mycelium Effect
     */
    TOWN_AURA(22),

    /**
     * Note Block Note
     */
    NOTE(23),

    /**
     * Nether Portal / Endermen Effect
     */
    PORTAL(24),

    /**
     * Enchantment Table Glyphs
     */
    ENCHANTMENT_TABLE(25),

    /**
     * Flame
     */
    FLAME(26),

    /**
     * Small Lava Fireballs
     */
    LAVA(27),

    /**
     * Footsteps
     */
    FOOTSTEP(28),

    /**
     * Cloud Smoke Effect
     */
    CLOUD(29),

    /**
     * Active Redstone Effect
     */
    RED_DUST(30),

    /**
     * Snowball Poof
     */
    SNOWBALL_POOF(31),

    /**
     * Breaking Snow / Creating Snow Golem
     */
    SNOW_SHOVEL(32),

    /**
     * Slime Particles
     */
    SLIME(33),

    /**
     * Hearts From Breeding/Taming
     */
    HEART(34),

    /**
     * Barrier Block Sign
     */
    BARRIER(35),

    /**
     * Eating / Thrown Egg / Potion / Eye Of Ender / Breaking Tool
     */
    ICON_CRACK(36),

    /**
     * Breaking Blocks / Sprinting
     */
    BLOCK_CRACK(37),

    /**
     * Breaking Armor Stand / Falling
     */
    BLOCK_DUST(38),

    /**
     * Rain Droplets
     */
    DROPLET(39),

    /**
     * ...?
     */
    TAKE(40),

    /**
     * Elder Guardian Animation
     */
    MOB_APPEARANCE(41);

    private final int id;

    ParticleEffectType(int id) {
        this.id = id;
    }

    /**
     * Get the id value of the effect
     *
     * @return The id value of the effect
     */
    public int id(){
        return id;
    }

}
