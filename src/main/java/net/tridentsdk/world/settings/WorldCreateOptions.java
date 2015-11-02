package net.tridentsdk.world.settings;

import com.google.common.collect.Sets;
import net.tridentsdk.util.FastRandom;
import net.tridentsdk.world.gen.ChunkGenerator;

import java.util.Collections;
import java.util.Set;

/**
 * World settings which are applid when a world is created by a worldloader
 *
 * @author The TridentSDK Team
 */
public final class WorldCreateOptions {
    private volatile Dimension dimension = Dimension.OVERWORLD;
    private volatile Difficulty difficulty = Difficulty.NORMAL;
    private volatile GameMode gameMode = GameMode.SURVIVAL;
    private volatile LevelType levelType = LevelType.DEFAULT;
    private final Set<String> rules = Sets.newHashSet();
    private volatile Class<? extends ChunkGenerator> generator = null;
    private volatile boolean structures = true;
    private volatile boolean pvp = true;
    private volatile long seed = FastRandom.random();

    /**
     * Sets the dimension for the world generator
     *
     * @param dimension the dimension
     * @return the current instance
     */
    public WorldCreateOptions dimension(Dimension dimension) {
        this.dimension = dimension;
        return this;
    }

    /**
     * Sets the difficulty of the world to be generated
     *
     * @param difficulty the difficulty
     * @return the the current instance
     */
    public WorldCreateOptions difficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    /**
     * Sets the gamemode of the world to be generated
     *
     * @param gameMode the gamemode
     * @return the current instance
     */
    public WorldCreateOptions gameMode(GameMode gameMode) {
        this.gameMode = gameMode;
        return this;
    }

    /**
     * Sets the leveltype of the world to be generated
     *
     * @param levelType the leveltype
     * @return the current instance
     */
    public WorldCreateOptions level(LevelType levelType) {
        this.levelType = levelType;
        return this;
    }

    /**
     * Sets the game rules of the world to be generated
     *
     * @param rules the game rules
     * @return the current instance
     */
    public WorldCreateOptions rule(String... rules) {
        Collections.addAll(this.rules, rules);
        return this;
    }

    /**
     * Sets the game rules of the world to be generated
     *
     * @param rules the game rules
     * @return the current instance
     */
    public WorldCreateOptions rule(Set<String> rules) {
        this.rules.addAll(rules);
        return this;
    }

    /**
     * Sets whether or not to generate structures (caves, villages, etc)
     *
     * @param gen {@code true} to generate structures
     * @return the current instance
     */
    public WorldCreateOptions structures(boolean gen) {
        this.structures = gen;
        return this;
    }

    /**
     * Sets the PvP enabled flag
     *
     * @param enable {@code true} to allow pvp
     * @return the current instance
     */
    public WorldCreateOptions pvp(boolean enable) {
        this.pvp = enable;
        return this;
    }

    /**
     * Uses the specified generator to generate chunks in the world
     *
     * @param generator the generator to use
     * @return the current instance
     */
    public WorldCreateOptions generator(Class<? extends ChunkGenerator> generator) {
        this.generator = generator;
        return this;
    }

    /**
     * Sets the seed used to generate the world
     *
     * @param seed the seed used to generate the world
     * @return the current instance
     */
    public WorldCreateOptions seed(String seed) {
        long finalSeed;
        try {
            finalSeed = Long.parseLong(seed);
        } catch (NumberFormatException e) {
            finalSeed = seed.hashCode();
        }

        this.seed = finalSeed;
        return this;
    }

    /**
     * Sets the seed used to generate the world
     *
     * @param seed the seed used to generate the world
     * @return the current instance
     */
    public WorldCreateOptions seed(long seed) {
        this.seed = seed;
        return this;
    }

    /**
     * Obtains the dimension of the world created using these options
     *
     * @return the world's dimension
     */
    public Dimension dimension() {
        return dimension;
    }

    /**
     * Obtains the difficulty of the world created using these options
     *
     * @return the world's difficulty
     */
    public Difficulty difficulty() {
        return difficulty;
    }

    /**
     * Obtains the default gamemode of the world created using these options
     *
     * @return the game mode
     */
    public GameMode defaultGameMode() {
        return gameMode;
    }

    /**
     * Obtains the level type of the world created using these options
     *
     * @return the level type of the world
     */
    public LevelType levelType() {
        return levelType;
    }

    /**
     * Checks whether the ru
     *
     * @param rule
     * @return
     */
    public boolean isRule(String rule) {
        return rules.contains(rule);
    }

    public Set<String> gameRules() {
        return rules;
    }

    public boolean generateStructures() {
        return structures;
    }

    public boolean allowPvp() {
        return pvp;
    }

    public Class<? extends ChunkGenerator> generator() {
        return generator;
    }

    public long seed() {
        return seed;
    }
}