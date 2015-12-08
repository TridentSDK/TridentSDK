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

import net.tridentsdk.docs.AccessNoDoc;

import javax.annotation.concurrent.Immutable;

/**
 * Gamerules are applied on a per-world basis and are options for more advanced/low-level properties of the world
 * such as command blocks, drops, and inventory modifiers
 *
 * <p>The default value for all game rules is {@code true} except for {@link #REDUCED_DEBUG_INFO} and
 * {@link #KEEP_INVENTORY}, which are both {@code false} by default</p>
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 */
public enum GameRule {
    /**
     * Whether command blocks should notify admins when they perform commands
     */
    COMMAND_BLOCK_OUTPUT("commandBlockOutput", Value.of(true)),
    /**
     * Whether time progresses
     */
    DO_DAYLIGHT_CYCLE("doDaylightCycle", Value.of(true)),
    /**
     * Whether entities that are not mobs should have drops
     */
    DO_ENTITY_DROPS("doEntityDrops", Value.of(true)),
    /**
     * Whether fire should spread and naturally extinguish
     */
    DO_FIRE_TICK("doFireTick", Value.of(true)),
    /**
     * Whether mobs should drop items
     */
    DO_MOB_LOOT("doMobLoot", Value.of(true)),
    /**
     * Whether mobs should naturally spawn
     */
    DO_MOB_SPAWNING("doMobSpawning", Value.of(true)),
    /**
     * Whether blocks should have drops
     */
    DO_TILE_DROPS("doTileDrops", Value.of(true)),
    /**
     * Whether the player should keep items in their inventory after death
     */
    KEEP_INVENTORY("keepInventory", Value.of(false)),
    /**
     * Whether to log admin commands to server log
     */
    LOG_ADMIN_COMMANDS("logAdminCommands", Value.of(true)),
    /**
     * Whether creepers, zombies, endermen, ghasts, withers, ender dragons, rabbits, sheep,
     * and villagers should be able to change blocks and whether villagers, zombies, skeletons,
     * and zombie pigmen can pick up items
     */
    MOB_GRIEFING("mobGriefing", Value.of(true)),
    /**
     * Whether the player can regenerate health naturally if their hunger is full enough (doesn't affect external
     * healing, such as golden apples, the Regeneration effect, etc.)
     */
    NATURAL_REGENERATION("naturalRegeneration", Value.of(true)),
    /**
     * How often a random block tick occurs (such as plant growth, leaf decay, etc.) per chunk section per game tick.
     *
     * <p>Default value: {@code 3}</p>
     *
     * <p>0 will disable random ticks, higher numbers will increase random ticks</p>
     */
    RANDOM_TICK_SPEED("randomTickSpeed", Value.of(3)),
    /**
     * Whether the debug screen shows all or reduced information
     */
    REDUCED_DEBUG_INFO("reducedDebugInfo", Value.of(false)),
    /**
     * Whether the feedback from commands executed by a player should show up in chat. Also affects the default behavior
     * of whether command blocks store their output text
     */
    SEND_COMMAND_FEEDBACK("sendCommandFeedback", Value.of(true)),
    /**
     * Whether a message appears in chat when a player dies
     */
    SHOW_DEATH_MESSAGES("showDeathMessages", Value.of(true)),
    /**
     * Whether players in spectator mode can generate chunks
     */
    SPECTATORS_GENERATE_CHUNKS("spectatorsGenerateChunks", Value.of(true));

    private final Value defValue;
    private final String raw;
    GameRule(String raw, Value defValue) {
        this.raw = raw;
        this.defValue = defValue;
    }

    /**
     * Obtains the default value of the game rule
     *
     * @return the default value
     */
    public Value defaultValue() {
        return defValue;
    }

    /**
     * Checks if the argument matches the default value
     *
     * @param b the boolean to check
     * @return {@code true} if it matches, {@code false} otherwise
     */
    public boolean boolEqualToValue(boolean b) {
        if (defValue.isInteger()) {
            throw new IllegalStateException("Value is an integer");
        }

        return defValue.asBoolean() == b;
    }

    /**
     * Checks if the argument matches the default value
     *
     * @param i the integer to check
     * @return {@code true} if it matches, {@code false} otherwise
     */
    public boolean intEqualToValue(int i) {
        if (defValue.isBoolean()) {
            throw new IllegalStateException("Value is a boolean");
        }

        return defValue.asInt() == i;
    }

    @Override
    public String toString() {
        return raw;
    }

    /**
     * Represents a value which can be possessed by the game rule
     *
     * @author The TridentSDK Team
     */
    @Immutable
    public static class Value {
        private final ValueType type;
        private final boolean bool;
        private final int integer;

        private Value(boolean bool) {
            this.type = ValueType.BOOLEAN;
            this.bool = bool;
            this.integer = 0;
        }

        private Value(int integer) {
            this.type = ValueType.INTEGER;
            this.integer = integer;
            this.bool = false;
        }

        /**
         * Creates a new game rule value that takes an integer parameter
         *
         * @param integer the game rule parameter
         * @return the wrapped value
         */
        public static Value of(int integer) {
            return new Value(integer);
        }

        /**
         * Creates a new game rule value that takes a boolean parameter
         *
         * @param bool the game rule parameter
         * @return the wrapped value
         */
        public static Value of(boolean bool) {
            return new Value(bool);
        }

        /**
         * Checks to see whether this value is an integer
         *
         * @return {@code true} if it is an integer, {@code false} if it is a boolean
         */
        public boolean isInteger() {
            return type == ValueType.INTEGER;
        }

        /**
         * Checks to see whether this value is a boolean
         *
         * @return {@code true} if it is a boolean, {@code false} if it is an integer
         */
        public boolean isBoolean() {
            return type == ValueType.BOOLEAN;
        }

        /**
         * Obtains the boolean value
         *
         * @return the boolean value
         */
        public boolean asBoolean() {
            if (type != ValueType.BOOLEAN) {
                throw new IllegalStateException("Value type not a boolean");
            }
            return bool;
        }

        /**
         * Obtains the integer value
         *
         * @return the integer value
         */
        public int asInt() {
            if (type != ValueType.INTEGER) {
                throw new IllegalStateException("Value type not an integer");
            }
            return integer;
        }
    }

    @AccessNoDoc
    private enum ValueType {
        INTEGER, BOOLEAN
    }
}