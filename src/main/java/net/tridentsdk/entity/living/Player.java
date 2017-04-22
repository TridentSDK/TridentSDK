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
package net.tridentsdk.entity.living;

import net.tridentsdk.chat.ChatComponent;
import net.tridentsdk.chat.ChatType;
import net.tridentsdk.entity.Entity;
import net.tridentsdk.ui.bossbar.BossBar;
import net.tridentsdk.ui.tablist.TabList;
import net.tridentsdk.ui.title.Title;
import net.tridentsdk.world.opt.GameMode;

import javax.annotation.concurrent.ThreadSafe;
import java.util.List;
import java.util.UUID;

/**
 * This class represents a player entity that has joined
 * the server.
 *
 * @author TridentSDK
 * @since 0.3-alpha-DP
 */
@ThreadSafe
public interface Player extends Entity {

    /**
     * The default flying speed for a player
     */
    float DEFAULT_FLYING_SPEED = 0.159F;

    /**
     * The default walking speed for a player
     */
    float DEFAULT_WALKING_SPEED = 0.699999988079071F;

    /**
     * Obtains the name that this player had logged in.
     *
     * @return the player's name
     */
    String getName();

    /**
     * Obtains the UUID of this player.
     *
     * <p>This UUID should be this player's identifier
     * wherever possible as the name is subject to change.
     * </p>
     *
     * @return the player's UUID
     */
    UUID getUuid();

    /**
     * Gets this player's display name.
     *
     * @return The display name.
     */
    ChatComponent getDisplayName();

    /**
     * Sets this player's display name.
     *
     * @param displayName The display name.
     */
    void setDisplayName(ChatComponent displayName);

    /**
     * Sends this player a message.
     *
     * @param message The message.
     * @param type The type of message.
     */
    void sendMessage(ChatComponent message, ChatType type);

    /**
     * Sends this player a system message.
     *
     * <p>Equivalent to
     * {@code sendMessage(message, ChatType.SYSTEM)}.</p>
     *
     * @param message The message.
     */
    default void sendMessage(ChatComponent message) {
        this.sendMessage(message, ChatType.SYSTEM);
    }

    /**
     * Sends this player a system message.
     *
     * <p>Equivalent to
     * {@code sendMessage(ChatComponent.text(message))}.</p>
     *
     * @param message The message.
     */
    default void sendMessage(String message) {
        this.sendMessage(ChatComponent.text(message));
    }

    /**
     * Disconnects this player from the server, displaying
     * the given message on screen as to why they may have
     * been disconnected.
     *
     * @param reason the reason for kicking the player
     */
    void kick(ChatComponent reason);

    /**
     * Obtains the game mode which the player is currently
     * using.
     *
     * @return the player's game mode
     */
    GameMode getGameMode();

    /**
     * Transitions the player from the current game mode to
     * the new given game mode.
     *
     * @param gameMode the new game mode
     */
    void setGameMode(GameMode gameMode);

    /**
     * Obtains the current tablist of the player.
     * If the player doesn't have its own tablist, then
     * the global tablist is returned.
     *
     * @return the current tablist
     */
    TabList getTabList();

    /**
     * Set the tablist of the player to a custom one.
     *
     * @param tabList the new tablist
     */
    void setTabList(TabList tabList);

    /**
     * Gets this player's boss bars.
     * <br><br>
     * <i>Note: the list order is top-to-bottom of a player's screen space</i>
     *
     * @return The boss bars.
     */
    List<BossBar> getBossBars();

    /**
     * Adds a boss bar to this player.
     *
     * @param bar The boss bar.
     */
    void addBossBar(BossBar bar);

    /**
     * Removes a boss bar from this player.
     *
     * @param bar The boss bar to be removed.
     */
    void removeBossBar(BossBar bar);

    /**
     * Updates this player's boss bars, sending any required packets.
     */
    void updateBossBars();

    /**
     * Sends a title to this player, sending any required
     * packets.
     *
     * @param title the title which to send to the player
     */
    void sendTitle(Title title);

    /**
     * Resets this player's title, removing the title
     * completely.
     */
    void resetTitle();

    /**
     * Gets whether or not this player is in god mode.
     *
     * @return True iff the player is in god mode.
     */
    boolean isGodMode();

    /**
     * Sets whether or not this player is in god mode.
     *
     * @param godMode Whether this player is in god mode.
     */
    void setGodMode(boolean godMode);

    /**
     * Gets whether or not this player can fly.
     *
     * @return True iff the player can fly.
     */
    boolean canFly();

    /**
     * Sets whether or not this player can fly.
     *
     * @param canFly Whether this player can fly.
     */
    void setCanFly(boolean canFly);

    /**
     * Gets whether or not this player is flying.
     *
     * @return True iff the player is flying.
     */
    boolean isFlying();

    /**
     * Sets whether or not this player is flying.
     *
     * @param flying Whether this player is flying.
     */
    void setFlying(boolean flying);

    /**
     * Gets this player's flying speed.
     *
     * @return The flying speed.
     */
    float getFlyingSpeed();

    /**
     * Sets this player's flying speed.
     *
     * @param flyingSpeed The flying speed.
     */
    void setFlyingSpeed(float flyingSpeed);

    /**
     * Gets this player's walking speed.
     *
     * @return The walking speed.
     */
    float getWalkingSpeed();

    /**
     * Sets this player's walking speed.
     *
     * @param walkingSpeed The walking speed.
     */
    void setWalkingSpeed(float walkingSpeed);
}
