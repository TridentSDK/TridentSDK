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
    public static void server(Server s) {
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
    public static void logger(TridentLogger logger) {
        Trident.logger = logger;
    }

    public static int port() {
        return server.port();
    }

    public static void shutdown() {
        server.shutdown();
    }

    public static Set<World> worlds() {
        return server.worlds();
    }

    public static InetAddress serverIp() {
        return server.serverIp();
    }

    public static void addTask(Runnable runnable) {
        server.addTask(runnable);
    }

    public static String motd() {
        return server.motd();
    }

    public static File motdPicture() {
        return server.motdPicture();
    }

    public static BufferedImage motdImage() {
        return server.motdImage();
    }

    public static int motdImage(Image image) {
        return server.motdImage(image);
    }

    public static int maxPlayers() {
        return server.maxPlayers();
    }

    public static int playerCount() {
        return server.playerCount();
    }

    public static Difficulty difficulty() {
        return server.difficulty();
    }

    public static String version() {
        return server.version();
    }

    public static Window windowFor(int id) {
        return server.windowFor(id);
    }

    public static EventManager eventManager() {
        return server.eventManager();
    }

    public static void sendPluginMessage(String channel, byte... data) {
        server.sendPluginMessage(channel, data);
    }

    public static TridentPluginHandler pluginHandler() {
        return server.pluginHandler();
    }

    public static Scheduler scheduler() {
        return server.scheduler();
    }

    public static JsonConfig config() {
        return server.config();
    }

    public static ThreadProvider provideThreads() {
        return server.provideThreads();
    }
}
