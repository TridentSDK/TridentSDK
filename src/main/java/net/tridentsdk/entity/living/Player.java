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

import net.tridentsdk.GameMode;
import net.tridentsdk.Messagable;
import net.tridentsdk.entity.LivingEntity;
import net.tridentsdk.entity.traits.PlayerSpeed;
import net.tridentsdk.entity.traits.InventoryHolder;
import net.tridentsdk.plugin.cmd.CommandIssuer;
import net.tridentsdk.service.PermissionHolder;

import java.util.Locale;

/**
 * Represents a player entity after joining the server
 *
 * @author The TridentSDK Team
 */
public interface Player extends LivingEntity, Messagable, CommandIssuer, InventoryHolder, PermissionHolder {
    // TODO: Use word settings?

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
     * Returns if the Player is sneaking
     *
     * @return true if Player is sneaking
     */
    public boolean isSneaking();

    /**
     * Gets whether the Player is sprinting
     *
     * @return true if Player is sprinting
     */
    public boolean isSprinting();

}
