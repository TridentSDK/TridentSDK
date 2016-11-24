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
package net.tridentsdk.entity.living;

import net.tridentsdk.chat.ChatComponent;
import net.tridentsdk.chat.ChatType;
import net.tridentsdk.entity.Entity;
import net.tridentsdk.ui.bossbar.BossBar;
import net.tridentsdk.ui.tablist.TabList;
import net.tridentsdk.world.opt.GameMode;

import javax.annotation.concurrent.ThreadSafe;
import java.util.Collection;
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
     * Obtains the name that this player had logged in.
     *
     * @return the player's name
     */
    String name();

    /**
     * Obtains the UUID of this player.
     *
     * <p>This UUID should be this player's identifier
     * wherever possible as the name is subject to change.
     * </p>
     *
     * @return the player's UUID
     */
    UUID uuid();

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
     *
     * @return The boss bars.
     */
    Collection<BossBar> getBossBars();

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
}