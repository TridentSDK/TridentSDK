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
import net.tridentsdk.api.window.Window;
import net.tridentsdk.api.world.World;
import net.tridentsdk.plugin.TridentPluginHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.InetAddress;
import java.util.Set;

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
    public static Server server() {
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
    public static TridentLogger logger() {
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

    public static int port() {
        return server.getPort();
    }

    public static void exit() {
        server.shutdown();
    }

    public static Set<World> worlds() {
        return server.getWorlds();
    }

    public static InetAddress ipAddress() {
        return server.getServerIp();
    }

    public static void run(Runnable runnable) {
        server.addTask(runnable);
    }

    public static String motd() {
        return server.getMotd();
    }

    public static File serverLogoFile() {
        return server.getMotdPicture();
    }

    public static BufferedImage serverLogoImage() {
        return server.getMotdPictureImage();
    }

    public static int setLogo(Image image) {
        return server.setMotdImage(image);
    }

    public static int maxPlayers() {
        return server.getMaxPlayers();
    }

    public static int playerCount() {
        return server.getCurrentPlayerCount();
    }

    public static Difficulty difficulty() {
        return server.getDifficulty();
    }

    public static String version() {
        return server.getVersion();
    }

    public static Window windowBy(int id) {
        return server.getWindow(id);
    }

    public static EventManager eventManager() {
        return server.getEventManager();
    }

    public static void sendPluginMessage(String channel, byte... data) {
        server.sendPluginMessage(channel, data);
    }

    public static TridentPluginHandler pluginHandler() {
        return server.getPluginHandler();
    }

    public static Scheduler scheduler() {
        return server.getScheduler();
    }

    public static JsonConfig configuration() {
        return server.getConfig();
    }

    public static ThreadProvider provideThreads() {
        return server.provideThreads();
    }
}
