/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2017 The TridentSDK Team
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

import net.tridentsdk.doc.Policy;
import net.tridentsdk.util.Misc;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

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
    private static final Map<String, GameRule<?>> GAME_RULES = new HashMap<>();

    /**
     * Whether player achievements are announced to players
     * in this world.
     *
     * <p>Default: {@code true}</p>
     */
    public static final GameRule<Boolean> ANNOUNCE_ADVANCEMENT = newRule("announceAdvancements", true);
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
    public static final GameRule<Boolean> MOVE_CHECK = newRule("disableElytraMovementCheck", false);
    /**
     * Whether to do the Daylight Cycle or not.
     *
     * <p>Default: {@code true}</p>
     */
    public static final GameRule<Boolean> DAYLIGHT_CYCLE = newRule("doDaylightCycle", true);
    /**
     * Whether non-living entities have drops.
     *
     * <p>Default: {@code true}</p>
     */
    public static final GameRule<Boolean> DO_ENTITY_DROPS = newRule("doEntityDrops", true);
    /**
     * Whether to spread or remove fire
     *
     * <p>Default: {@code true}</p>
     */
    public static final GameRule<Boolean> FIRE_TICK = newRule("doFireTick", true);
    /**
     * Whether crafting should be limited to only unlocked
     * recipes.
     *
     * <p>Default: {@code false}</p>
     */
    public static final GameRule<Boolean> LIMIT_CRAFTING = newRule("doLimitedCrafting", false);
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
     * If the weather does toggle between rain, thunder and
     * clear.
     *
     * <p>Default: {@code true}</p>
     */
    public static final GameRule<Boolean> WEATHER_CYCLE = newRule("doWeatherCycle", true);
    /**
     * The name of the function file to use when the server
     * is ticked.
     *
     * <p>Default: {@code ""}</p>
     */
    public static final GameRule<String> TICK_FUNCTION = newRule("gameLoopFunction", "");
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
     * The maximum number of commands that may be executed
     * by a function file.
     *
     * <p>Default: {@code 65536}</p>
     */
    public static final GameRule<Integer> MAX_CMD_CHAIN_LEN = newRule("maxCommandChainLength", 65536);
    /**
     * The maximum number of entities a player can push
     * (excluding bats and spectators) before they begin to
     * take 3 damage points (1.5 hearts).
     *
     * <p>Default: {@code 24}</p>
     */
    public static final GameRule<Integer> MAX_ENTITY_CRAM = newRule("maxEntityCramming", 24);
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
     * Whether players on this world will have reduced
     * debug info available in the F3 menu.
     *
     * <p>Default: {@code false}</p>
     */
    public static final GameRule<Boolean> REDUCE_DEBUG = newRule("reducedDebugInfo", false);
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
    public static final GameRule<Boolean> SHOW_DEATH_MSG = newRule("showDeathMessages", true);
    /**
     * The distance from spawn that players will initially
     * spawn or respawn without a bed.
     *
     * <p>I don't think we use this at all.</p>
     *
     * <p>Default: {@code 10}</p>
     */
    public static final GameRule<Integer> SPAWN_RADIUS = newRule("spawnRadius", 10);
    /**
     * Whether or not spectator players are allowed to
     * generate chunks (and therefore move freely throughout
     * the world).
     *
     * <p>Default: {@code true}</p>
     */
    public static final GameRule<Boolean> SPEC_GEN_CHUNKS = newRule("spectatorsGenerateChunks", true);

    /**
     * Factory method shortcut for creating new game rule
     * constant values.
     *
     * @param name NBT name of the game rule
     * @param defValue the default value
     * @return the new game rule
     */
    private static GameRule<Boolean> newRule(String name, boolean defValue) {
        GameRule<Boolean> rule = new GameRule<>(name, defValue, s -> s.equals("true"));
        GAME_RULES.put(name, rule);
        return rule;
    }

    /**
     * Factory method shortcut for creating new game rule
     * constant values.
     *
     * @param name NBT name of the game rule
     * @param defValue the default value
     * @return the new game rule
     */
    private static GameRule<Integer> newRule(String name, int defValue) {
        GameRule<Integer> rule = new GameRule<>(name, defValue, Integer::parseInt);
        GAME_RULES.put(name, rule);
        return rule;
    }

    /**
     * Factory method shortcut for creating new game rule
     * constant values.
     *
     * @param name NBT name of the game rule
     * @param defValue the default value
     * @return the new game rule
     */
    private static GameRule<String> newRule(String name, String defValue) {
        GameRule<String> rule = new GameRule<>(name, defValue, s -> s);
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
     * The function used to parse the String value into a
     * game rule value
     */
    private final Function<String, T> parseFunction;

    /**
     * Creates a new game rule.
     *
     * @param stringForm the raw string form of the rule
     * @param defValue the default value
     */
    private GameRule(String stringForm, T defValue, Function<String, T> parseFunction) {
        this.stringForm = stringForm;
        this.defValue = defValue;
        this.parseFunction = parseFunction;
    }

    /**
     * Obtains the default value for the given game rule.
     *
     * @return the vanilla default value
     */
    public T getDefault() {
        return this.defValue;
    }

    /**
     * Parses the game rule value string from a compound
     * into a game rule value.
     *
     * @param value the value to parse
     * @return the game rule value
     */
    public T parseValue(String value) {
        return this.parseFunction.apply(value);
    }

    /**
     * Obtains the game rule using the raw string form in
     * the NBT format.
     *
     * <p>This method ignores case.</p>
     *
     * @param <T> the type of value that the game rule holds
     * @param s the NBT form of the game rule to find
     * @return the game rule, if found
     * @throws IllegalArgumentException if the game rule is
     *         not found
     */
    @Nonnull
    public static <T> GameRule<T> from(String s) {
        GameRule rule = GAME_RULES.get(s);
        if (rule == null) {
            throw new IllegalArgumentException(String.format(Misc.NBT_BOUND_FAIL, "n.t.w.o.GameRule (" + s + ')'));
        }

        return rule;
    }

    /**
     * Obtains all the possible game rule keys.
     *
     * @return the set of valid game rule keys, not
     * modifiable
     */
    public static Collection<String> getKeyStrings() {
        return Collections.unmodifiableCollection(GAME_RULES.keySet());
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
