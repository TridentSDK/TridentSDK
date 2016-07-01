/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2016 The TridentSDK Team
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
package net.tridentsdk.world.opt;

import net.tridentsdk.util.Misc;

/**
 * This is a set of possible game rules that may be applied
 * to a world.
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 * @see WorldOpts
 * @see GameRuleValue
 */
public enum GameRule {
    /**
     * Whether or not actions performed by command blocks
     * are displayed in the chat.
     *
     * <p>Default: {@code true}</p>
     */
    CMD_BLOCK_OUTPUT("commandBlockOutput", true),
    /**
     * Whether the server should disable checking if the
     * player is moving too fast (cheating) while wearing
     * Elytra.
     *
     * <p>Default: {@code true}</p>
     */
    MOVE_CHECK("disableElytraMovementCheck", true),
    /**
     * Whether to do the Daylight Cycle or not.
     *
     * <p>Default: {@code true}</p>
     */
    DAYLIGHT_CYCLE("doDaylightCycle", true),
    /**
     * Whether to spread or remove fire
     *
     * <p>Default: {@code true}</p>
     */
    FIRE_TICK("doFireTick", true),
    /**
     * Whether mobs should drop loot when killed.
     *
     * <p>Default: {@code true}</p>
     */
    MOB_LOOT("doMobLoot", true),
    /**
     * Whether mobs should spawn naturally.
     *
     * <p>Default: {@code true}</p>
     */
    MOB_SPAWN("doMobSpawning", true),
    /**
     * Whether breaking blocks should drop the block's item
     * drop.
     *
     * <p>Default: {@code true}</p>
     */
    TILE_DROP("doTileDrops", true),
    /**
     * Whether players keep their inventory after they die.
     *
     * <p>Default: {@code false}</p>
     */
    KEEP_INVENTORY("keepInventory", false),
    /**
     * Whether to log admin commands to server log.
     *
     * <p>Default: {@code true}</p>
     */
    LOG_ADMIN_CMDS("logAdminCommands", true),
    /**
     * Whether mobs can destroy blocks (creeper explosions,
     * zombies breaking doors, etc).
     *
     * <p>Default: {@code true}</p>
     */
    MOB_GRIEF("mobGriefing", true),
    /**
     * Whether the player naturally regenerates health if
     * their hunger is high enough.
     *
     * <p>Default: {@code true}</p>
     */
    NATURAL_REGEN("naturalRegeneration", true),
    /**
     * How often a random tick occurs, such as plant growth,
     * leaf decay, etc.
     *
     * <p>Default: {@code 3}</p>
     */
    RANDOM_TICK_SPEED("randomTickSpeed", 3),
    /**
     * Whether the feedback from commands executed by a
     * player should show up in chat.
     *
     * <p>Default: {@code true}</p>
     */
    SEND_CMD_FEEDBACK("sendCommandFeedback", true),
    /**
     * Whether a message appears in chat when a player dies.
     *
     * <p>Default: {@code true}</p>
     */
    SHOW_DEATH_MSG("sendCommandFeedback", true);

    /** The raw minecraft form of the game rule */
    private final String stringForm;
    /** The default value given to the game rule */
    private final GameRuleValue defValue;

    /**
     * Creates a new game rule that takes a boolean value.
     *
     * @param stringForm the raw string form of the rule
     * @param defValue the default boolean value of the rule
     */
    GameRule(String stringForm, boolean defValue) {
        this.stringForm = stringForm;
        this.defValue = GameRuleValue.bool(defValue);
    }

    /**
     * Creates a new game rule that takes a number value.
     *
     * @param stringForm the raw string form of the rule
     * @param defValue the default boolean value of the rule
     */
    GameRule(String stringForm, long defValue) {
        this.stringForm = stringForm;
        this.defValue = GameRuleValue.number(defValue);
    }

    /**
     * Obtains the default value for the given game rule.
     *
     * @return the vanilla default value
     */
    public GameRuleValue defValue() {
        return this.defValue;
    }

    /**
     * Obtains the game rule using the raw string form in
     * the NBT format.
     *
     * <p>This method ignores case.</p>
     *
     * @param s the NBT form of the game rule to find
     * @return the game rule, if found
     * @throws IllegalArgumentException if the game rule is
     *         not found
     */
    public static GameRule from(String s) {
        for (GameRule rule : values()) {
            if (rule.stringForm.equalsIgnoreCase(s)) return rule;
        }

        throw new IllegalArgumentException(String.format(Misc.NBT_BOUND_FAIL, "n.t.w.o.GameRule"));
    }

    /**
     * This method returns the string that represents the
     * game rule.
     *
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.stringForm;
    }

    /*
     * Work(s) cited
     *
     * "Level Format." Minecraft Wiki. Curse Inc.,
     * 27 Apr. 2016. Web. 26 June 2016.
     * <http://minecraft.gamepedia.com/Level_format>
     */
}