/*
 * Copyright (c) 2014, The TridentSDK Team
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     1. Redistributions of source code must retain the above copyright
 *        notice, this list of conditions and the following disclaimer.
 *     2. Redistributions in binary form must reproduce the above copyright
 *        notice, this list of conditions and the following disclaimer in the
 *        documentation and/or other materials provided with the distribution.
 *     3. Neither the name of the The TridentSDK Team nor the
 *        names of its contributors may be used to endorse or promote products
 *        derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL The TridentSDK Team BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package net.tridentsdk.api;

import net.tridentsdk.api.config.JsonConfig;
import net.tridentsdk.api.event.EventManager;
import net.tridentsdk.api.scheduling.Scheduler;
import net.tridentsdk.api.window.Window;
import net.tridentsdk.api.world.World;
import net.tridentsdk.plugin.TridentPluginHandler;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.InetAddress;
import java.util.List;
import java.util.logging.Logger;

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
    List<World> getWorlds();

    /**
     * Gets the Internet Address of this server
     *
     * @return the address of this server
     */
    InetAddress getServerIp();

    /**
     * Asks the server to execute a task, will be run immediately on an alternate thread
     *
     * @param runnable The code to execute
     */
    void addTask(Runnable runnable);

    /**
     * Gets the logger that Trident uses, not necessarily the same logger your plugin should be using
     *
     * @return that represents the Logger Trident is using
     */
    Logger getLogger();

    /**
     * A string containing the current broadcast MOTD of the server
     *
     * @return a string containing the MOTD of the server, may be empty, never null
     */
    String getMotd();

    /**
     * Returns the {@link java.io.File} that represents the picture displayed next to the server listing on clients
     *
     * @return the file that represents the picture sent to clients when they ping the server
     * @see net.tridentsdk.api.Server#getMotdPictureImage() for the representing the image sent to clients
     */
    File getMotdPicture();

    /**
     * Gets the {@link java.awt.image.BufferedImage} that represents the Motd picture sent to clients
     *
     * @return the image sent to clients
     * @see net.tridentsdk.api.Server#getMotdPicture() for the file itself
     */
    BufferedImage getMotdPictureImage();

    /**
     * Sets the MOTD image sent to clients, may or may not take a server restart to take effect
     *
     * @param image the image to set it to
     * @return 0 for success, -1 if this feature is disabled in config, -2 for generic failure
     */
    int setMotdImage(Image image);

    /**
     * Gets the maximum number of players allowed on the server
     *
     * @return the maximum number of players the server will allow
     */
    int getMaxPlayers();

    /**
     * Returns the number of players currently on the server
     *
     * @return a number representing the number of players on the server
     */
    int getCurrentPlayerCount();

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

    Window getWindow(int id);

    /**
     * Get the event manager
     *
     * @return the EventManager instance
     */
    EventManager getEventManager();

    /**
     * Send a plugin message
     *
     * @param channel name of the channel
     * @param data the data to send
     */
    void sendPluginMessage(String channel, byte[] data);

    /**
     * Get the Trident Plugin Handler
     *
     * @return the TridentPluginHandler instance
     */
    TridentPluginHandler getPluginHandler();

    Scheduler getScheduler();

    JsonConfig getConfig();
}
