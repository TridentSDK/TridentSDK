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
package net.tridentsdk.api;

import net.tridentsdk.api.config.JsonConfig;
import net.tridentsdk.api.entity.living.Player;
import net.tridentsdk.api.event.EventHandler;
import net.tridentsdk.api.window.Window;
import net.tridentsdk.api.world.World;
import net.tridentsdk.plugin.TridentPluginHandler;
import org.slf4j.Logger;

import java.net.InetAddress;
import java.util.Set;
import java.util.UUID;

/**
 * The access to the server internals
 *
 * @author The TridentSDK Team
 */
public interface Server {
    /**
     * Gets the port the server currently runs on
     *
     * @return the port the server runs on
     */
    int getPort();

    /**
     * Closes the connections of the server, disconnects all clients, and unloads everything, then exits the JVM.
     */
    void shutdown();

    /**
     * Get all the worlds loaded on the server
     *
     * @return a {@link java.util.List} of all the worlds
     */
    Set<World> getWorlds();

    /**
     * Gets the Internet Address of this server
     *
     * @return the address of this server
     */
    InetAddress getServerIp();

    /**
     * Asks the server to reflect a task, will be run immediately on an alternate thread
     *
     * @param runnable The code to reflect
     */
    void addTask(Runnable runnable);

    /**
     * Gets the logger that Trident uses, not necessarily the same logger your plugin should be using
     *
     * @return that represents the Logger Trident is using
     */
    Logger getLogger();

    /**
     * Gets the current difficulty that the server is set to (Worlds can have their own difficulty)
     *
     * @return the difficulty of the server
     */
    Difficulty getDifficulty();

    /**
     * Gets the version of Trident that the server is currently running
     *
     * @return a String representing the current version of the Trident server that the server is running
     */
    String getVersion();

    /**
     * Gets an inventory window
     *
     * @param id the ID of the window to be searched
     * @return the window with the ID
     */
    Window getWindow(int id);

    /**
     * Get the event manager
     *
     * @return the EventManager instance
     */
    EventHandler getEventManager();

    /**
     * Send a plugin message
     *
     * @param channel name of the channel
     * @param data    the data to send
     */
    void sendPluginMessage(String channel, byte... data);

    /**
     * Get the Trident Plugin Handler
     *
     * @return the TridentPluginHandler instance
     */
    TridentPluginHandler getPluginHandler();

    /**
     * Gets the server's display information on the server list
     *
     * @return the display information manager
     */
    DisplayInfo getInfo();

    /**
     * The server configuration file
     *
     * @return the server config
     */
    JsonConfig getConfig();

    Player getPlayer(UUID id);
}
