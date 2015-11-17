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
package net.tridentsdk.world.settings;

import com.google.common.collect.Maps;
import net.tridentsdk.base.Position;
import net.tridentsdk.util.FastRandom;
import net.tridentsdk.world.gen.ChunkGenerator;

import java.util.Map;

/**
 * World settings which are applied when a world is created by a worldloader
 *
 * @author The TridentSDK Team
 */
public final class WorldCreateOptions {
    private volatile Dimension dimension = Dimension.OVERWORLD;
    private volatile Difficulty difficulty = Difficulty.NORMAL;
    private volatile GameMode gameMode = GameMode.SURVIVAL;
    private volatile LevelType levelType = LevelType.DEFAULT;
    private final Map<GameRule, GameRule.Value> rules = Maps.newConcurrentMap();
    private volatile Class<? extends ChunkGenerator> generator = null;
    private volatile boolean structures = true;
    private volatile boolean pvp = true;
    private volatile long seed = FastRandom.random();
    private final Position spawn = Position.create(null, 0, 0, 0);

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
     * @param rule the game rule to add
     * @param value the game rule's parameter value
     * @return the current instance
     */
    public WorldCreateOptions rule(GameRule rule, GameRule.Value value) {
        rules.put(rule, value);
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
     * Checks whether the rule is enabled
     *
     * @param rule the game rule
     * @return {@code true} if the game rule is equal to the provided argument
     */
    public boolean isRule(GameRule rule, GameRule.Value value) {
        if (value.isInteger()) {
            GameRule.Value v = rules.get(rule);
            if (v == null) {
                return rule.intEqualToValue(value.asInt());
            } else {
                return v.asInt() == value.asInt();
            }
        } else {
            GameRule.Value v = rules.get(rule);
            if (v == null) {
                return rule.boolEqualToValue(value.asBoolean());
            } else {
                return v.asBoolean() == value.asBoolean();
            }
        }
    }

    public Map<GameRule, GameRule.Value> gameRules() {
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