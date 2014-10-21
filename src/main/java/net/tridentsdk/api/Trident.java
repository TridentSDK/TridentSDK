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

import com.google.common.base.Preconditions;
import net.tridentsdk.api.config.JsonConfig;
import net.tridentsdk.api.event.EventManager;
import net.tridentsdk.api.scheduling.Scheduler;
import net.tridentsdk.api.threads.ThreadProvider;
import net.tridentsdk.api.util.TridentLogger;
import net.tridentsdk.api.window.*;
import net.tridentsdk.api.window.Window;
import net.tridentsdk.api.world.World;
import net.tridentsdk.plugin.TridentPluginHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.InetAddress;
import java.util.List;

/**
 * Utility accessor to the {@link net.tridentsdk.api.Server}
 *
 * @author The TridentSDK Team
 */
public final class Trident {
    private static Server server;
    private static TridentLogger logger;

    private Trident() {
    }

    /**
     * Gets the server singleton that is currently running
     *
     * @return the server that is running
     */
    public static Server getServer() {
        return server;
    }

    /**
     * Do not call <p/> <p>Will throw an exception if you are not calling from a trusted source</p>
     *
     * @param s the server to set
     */
    public static void setServer(Server s) {
        Preconditions.checkState(isTrident(), "Server instance can only be set by TridentSDK!");
        server = s;
    }

    public static boolean isTrident() {
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        StackTraceElement element = elements[3];

        return element.getClassName().startsWith("net.tridentsdk");
    }

    /**
     * Gets the logger which the server is currently using
     *
     * @return the logger which is being used
     */
    public static TridentLogger getLogger() {
        return logger;
    }

    /**
     * Sets the output console logger
     *
     * @param logger the logger to use
     */
    public static void setLogger(TridentLogger logger) {
        Trident.logger = logger;
    }
    
    public int getPort() {
        return server.getPort();
    }
    
    public void shutdown() {
        server.shutdown();
    }
    
    public List<World> getWorlds() {
        return server.getWorlds();
    }
    
    public InetAddress getServerIp() {
        return server.getServerIp();
    }
    
    public void addTask(Runnable runnable) {
        server.addTask(runnable);
    }
    
    public String getMotd() {
        return server.getMotd();
    }
    
    public File getMotdPicture() {
        return server.getMotdPicture();
    }
    
    public BufferedImage getMotdPictureImage() {
        return server.getMotdPictureImage();
    }

    public int setMotdImage(Image image) {
        return server.setMotdImage(image);
    }
    
    public int getMaxPlayers() {
        return server.getMaxPlayers();
    }
    
    public int getCurrentPlayerCount() {
        return server.getCurrentPlayerCount();
    }
    
    public Difficulty getDifficulty() {
        return server.getDifficulty();
    }
    
    public String getVersion() {
        return server.getVersion();
    }
    
    public Window getWindow(int id) {
        return server.getWindow(id);
    }
    
    public EventManager getEventManager() {
        return server.getEventManager();
    }
    
    public void sendPluginMessage(String channel, byte... data) {
        server.sendPluginMessage(channel, data);
    }
    
    public TridentPluginHandler getPluginHandler() {
        return server.getPluginHandler();
    }
    
    public Scheduler getScheduler() {
        return server.getScheduler();
    }

    public JsonConfig getConfig() {
        return server.getConfig();
    }
    
    public ThreadProvider provideThreads() {
        return server.provideThreads();
    }
}
