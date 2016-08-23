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

import com.google.common.collect.Maps;
import net.tridentsdk.doc.Policy;
import net.tridentsdk.util.Misc;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import java.util.Map;

/**
 * This is a set of possible game rules that may be applied
 * to a world.
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 * @param <T> the type of value that the game rule holds
 * @see WorldOpts
 * @see GameRuleMap
 */
@Immutable
public class GameRule<T> {
    /**
     * The game rule values.
     *
     * <p>We can get away with using an unsynchronized map
     * because staticfinal fields piggyback on class loader
     * locks.</p>
     */
    // Do not move this field
    // Doing so risks ExceptionInInitializerError
    @Policy("do not move")
    private static final Map<String, GameRule<?>> GAME_RULES = Maps.newHashMap();

    /**
     * Whether or not actions performed by command blocks
     * are displayed in the chat.
     *
     * <p>Default: {@code true}</p>
     */
    public static final GameRule<Boolean> CMD_BLOCK_OUTPUT = newRule("commandBlockOutput", true);
    /**
     * Whether the server should disable checking if the
     * player is moving too fast (cheating) while wearing
     * Elytra.
     *
     * <p>Default: {@code true}</p>
     */
    public static final GameRule<Boolean> MOVE_CHECK = newRule("disableElytraMovementCheck", true);
    /**
     * Whether to do the Daylight Cycle or not.
     *
     * <p>Default: {@code true}</p>
     */
    public static final GameRule<Boolean> DAYLIGHT_CYCLE = newRule("doDaylightCycle", true);
    /**
     * Whether to spread or remove fire
     *
     * <p>Default: {@code true}</p>
     */
    public static final GameRule<Boolean> FIRE_TICK = newRule("doFireTick", true);
    /**
     * Whether mobs should drop loot when killed.
     *
     * <p>Default: {@code true}</p>
     */
    public static final GameRule<Boolean> MOB_LOOT = newRule("doMobLoot", true);
    /**
     * Whether mobs should spawn naturally.
     *
     * <p>Default: {@code true}</p>
     */
    public static final GameRule<Boolean> MOB_SPAWN = newRule("doMobSpawning", true);
    /**
     * Whether breaking blocks should drop the block's item
     * drop.
     *
     * <p>Default: {@code true}</p>
     */
    public static final GameRule<Boolean> TILE_DROP = newRule("doTileDrops", true);
    /**
     * Whether players keep their inventory after they die.
     *
     * <p>Default: {@code false}</p>
     */
    public static final GameRule<Boolean> KEEP_INVENTORY = newRule("keepInventory", false);
    /**
     * Whether to log admin commands to server log.
     *
     * <p>Default: {@code true}</p>
     */
    public static final GameRule<Boolean> LOG_ADMIN_CMDS = newRule("logAdminCommands", true);
    /**
     * Whether mobs can destroy blocks (creeper explosions,
     * zombies breaking doors, etc).
     *
     * <p>Default: {@code true}</p>
     */
    public static final GameRule<Boolean> MOB_GRIEF = newRule("mobGriefing", true);
    /**
     * Whether the player naturally regenerates health if
     * their hunger is high enough.
     *
     * <p>Default: {@code true}</p>
     */
    public static final GameRule<Boolean> NATURAL_REGEN = newRule("naturalRegeneration", true);
    /**
     * How often a random tick occurs, such as plant growth,
     * leaf decay, etc.
     *
     * <p>Default: {@code 3}</p>
     */
    public static final GameRule<Integer> RANDOM_TICK_SPEED = newRule("randomTickSpeed", 3);
    /**
     * Whether the feedback from commands executed by a
     * player should show up in chat.
     *
     * <p>Default: {@code true}</p>
     */
    public static final GameRule<Boolean> SEND_CMD_FEEDBACK = newRule("sendCommandFeedback", true);
    /**
     * Whether a message appears in chat when a player dies.
     *
     * <p>Default: {@code true}</p>
     */
    public static final GameRule<Boolean> SHOW_DEATH_MSG = newRule("sendCommandFeedback", true);

    /**
     * Factory method shortcut for creating new game rule
     * constant values.
     *
     * @param name NBT name of the game rule
     * @param defValue the default value
     * @param <T> the type of value the game rule holds
     * @return the new game rule
     */
    private static <T> GameRule<T> newRule(String name, T defValue) {
        GameRule<T> rule = new GameRule<>(name, defValue);
        GAME_RULES.put(name, rule);
        return rule;
    }

    /**
     * The raw minecraft form of the game rule
     */
    private final String stringForm;
    /**
     * The default value given to the game rule
     */
    private final T defValue;

    /**
     * Creates a new game rule.
     *
     * @param stringForm the raw string form of the rule
     * @param defValue the default value
     */
    private GameRule(String stringForm, T defValue) {
        this.stringForm = stringForm;
        this.defValue = defValue;
    }

    /**
     * Obtains the default value for the given game rule.
     *
     * @return the vanilla default value
     */
    public T defValue() {
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
    @Nonnull
    public static <T> GameRule<T> from(String s) {
        GameRule rule = GAME_RULES.get(s);
        if (rule == null) {
            throw new IllegalArgumentException(String.format(Misc.NBT_BOUND_FAIL, "n.t.w.o.GameRule"));
        }

        return rule;
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