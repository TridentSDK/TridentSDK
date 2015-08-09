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

package net.tridentsdk.entity.living;

import net.tridentsdk.Messagable;
import net.tridentsdk.entity.LivingEntity;
import net.tridentsdk.entity.traits.PlayerSpeed;
import net.tridentsdk.entity.traits.WindowHolder;
import net.tridentsdk.inventory.Item;
import net.tridentsdk.meta.MessageBuilder;
import net.tridentsdk.plugin.cmd.CommandIssuer;
import net.tridentsdk.service.PermissionOwner;
import net.tridentsdk.world.settings.GameMode;

import java.util.Locale;

/**
 * Represents a player entity after joining the server
 *
 * @author The TridentSDK Team
 * @since 0.3-alpha-DP
 */
public interface Player extends LivingEntity, Messagable, CommandIssuer, WindowHolder, PermissionOwner {
    // TODO: Use word settings?

    /**
     * Current header on TAB, returns null if non-existent
     *
     * @return current header on TAB
     */
    String header();

    default void setHeader(String message) {
        setHeader(new MessageBuilder(message));
    }

    void setHeader(MessageBuilder builder);

    /**
     * Current footer on TAB, returns null if non-existent
     *
     * @return current footer on TAB
     */
    String footer();

    default void setFooter(String message) {
        setFooter(new MessageBuilder(message));
    }

    void setFooter(MessageBuilder builder);

    /**
     * The name of the player, matches that of Mojang servers
     *
     * @return the name of the player
     */
    String name();

    /**
     * Returns the Player's {@link Locale} settings
     *
     * @return Locale the Player's Locale settings
     */
    Locale locale();

    /**
     * Returns the GameMode the Player is in
     *
     * @return GameMode current GameMode of the Player
     */
    GameMode gameMode();

    /**
     * Sets the player's gamemode to the specified gamemode
     */
    void setGameMode(GameMode mode);

    /**
     * Obtains the settings for the player's speed
     *
     * @return the player speed settings
     */
    PlayerSpeed speedModifiers();

    /**
     * Sends the player a message
     *
     * @param message the message to display to the player
     */
    void sendMessage(String message);

    /**
     * Obtains whether this player is currently on the server or not
     *
     * @return {@true to indicate the player is connected to the server}
     */
    boolean connected();

    Item pickedItem();

    void setPickedItem(Item item);
}
