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
package net.tridentsdk.ui.tablist;

import net.tridentsdk.chat.ChatComponent;
import net.tridentsdk.entity.living.Player;

import java.util.List;

public interface TabList {

    /**
     * Set an element of the tablist to a specific value.
     * If value is null, the element will be removed
     *
     * @param slot the slot number of the element
     * @param value the value of the element
     */
    void setElement(int slot, ChatComponent value);

    /**
     * Obtains the value of the specified element
     *
     * @param slot the slot number of the element
     * @return the value of the element
     */
    ChatComponent getElement(int slot);

    /**
     * Change the header of the tablist.
     * If value is null, header will be removed.
     *
     * @param value the new header or null
     */
    void setHeader(ChatComponent value);

    /**
     * Obtain the current header of this tablist.
     *
     * @return the current header
     */
    ChatComponent getHeader();

    /**
     * Change the footer of the tablist.
     * If value is null, footer will be removed.
     *
     * @param value the new footer or null
     */
    void setFooter(ChatComponent value);

    /**
     * Obtain the current footer of this tablist.
     *
     * @return the current footer
     */
    ChatComponent getFooter();

    /**
     * Return a list of all players that currently use
     * this tablist.
     *
     * @return list of tablist users
     */
    List<Player> getUserList();

    /**
     * Add a player as a user of this tablist
     *
     * @param player the player to add
     */
    void addUser(Player player);

    /**
     * Remove a player as a user of this tablist
     *
     * @param player the player to remove
     */
    void removeUser(Player player);
}
