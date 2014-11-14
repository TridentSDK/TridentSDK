/*
 *     TridentSDK - A Minecraft Server API
 *     Copyright (C) 2014, The TridentSDK Team
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.tridentsdk.api;

import net.tridentsdk.api.config.JsonConfig;
import net.tridentsdk.api.entity.living.Player;
import net.tridentsdk.api.event.EventManager;
import net.tridentsdk.api.scheduling.Scheduler;
import net.tridentsdk.api.threads.ThreadProvider;
import net.tridentsdk.api.window.Window;
import net.tridentsdk.api.world.World;
import net.tridentsdk.plugin.TridentPluginHandler;
import org.slf4j.Logger;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
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
    int port();

    /**
     * Closes the connections of the server, disconnects all clients, and unloads everything, then exits the JVM.
     */
    void shutdown();

    /**
     * Get all the worlds loaded on the server
     *
     * @return a {@link java.util.List} of all the worlds
     */
    Set<World> worlds();

    /**
     * Gets the Internet Address of this server
     *
     * @return the address of this server
     */
    InetAddress serverIp();

    /**
     * Asks the server to call a task, will be run immediately on an alternate thread
     *
     * @param runnable The code to call
     */
    void addTask(Runnable runnable);

    /**
     * Gets the logger that Trident uses, not necessarily the same logger your plugin should be using
     *
     * @return that represents the Logger Trident is using
     */
    Logger logger();

    /**
     * A string containing the current broadcast MOTD of the server
     *
     * @return a string containing the MOTD of the server, may be empty, never null
     */
    String motd();

    /**
     * Returns the {@link java.io.File} that represents the picture displayed next to the server listing on clients
     *
     * @return the file that represents the picture sent to clients when they ping the server
     * @see net.tridentsdk.api.Server#motdImage() for the representing the image sent to clients
     */
    File motdPicture();

    /**
     * Gets the {@link java.awt.image.BufferedImage} that represents the Motd picture sent to clients
     *
     * @return the image sent to clients
     * @see net.tridentsdk.api.Server#motdPicture() for the file itself
     */
    BufferedImage motdImage();

    /**
     * Sets the MOTD image sent to clients, may or may not take a server restart to take effect
     *
     * @param image the image to set it to
     * @return 0 for success, -1 if this feature is disabled in config, -2 for generic failure
     */
    int motdImage(Image image);

    /**
     * Gets the maximum number of players allowed on the server
     *
     * @return the maximum number of players the server will allow
     */
    int maxPlayers();

    /**
     * Returns the number of players currently on the server
     *
     * @return a number representing the number of players on the server
     */
    int playerCount();

    /**
     * Gets the current difficulty that the server is set to (Worlds can have their own difficulty)
     *
     * @return the difficulty of the server
     */
    Difficulty difficulty();

    /**
     * Gets the version of Trident that the server is currently running
     *
     * @return a String representing the current version of the Trident server that the server is running
     */
    String version();

    /**
     * Gets an inventory window
     *
     * @param id the ID of the window to be searched
     * @return the window with the ID
     */
    Window windowFor(int id);

    /**
     * Get the event manager
     *
     * @return the EventManager instance
     */
    EventManager eventManager();

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
    TridentPluginHandler pluginHandler();

    /**
     * The trident task scheduler
     *
     * @return the scheduler for submitting tasks to
     */
    Scheduler scheduler();

    /**
     * The server configuration file
     *
     * @return the server config
     */
    JsonConfig config();

    /**
     * Requests the thread handler
     *
     * @return the thread provider for the server
     */
    ThreadProvider provideThreads();

    /**
     * Acquires a player by their UUID which was given by Mojang to the account the player is playing with
     *
     * @param id the UUID object
     * @return the player with the given UUID
     */
    Player playerFor(UUID id);
}
