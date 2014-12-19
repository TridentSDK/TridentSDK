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
package net.tridentsdk;

import com.google.common.base.Preconditions;
import net.tridentsdk.config.JsonConfig;
import net.tridentsdk.event.EventHandler;
import net.tridentsdk.plugin.TridentPluginHandler;
import net.tridentsdk.window.Window;
import net.tridentsdk.world.World;

import java.net.InetAddress;
import java.util.Set;

/**
 * Utility accessor to the {@link Server}
 *
 * @author The TridentSDK Team
 */
public final class Trident {
    private static Server server;

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

    public static int getPort() {
        return server.getPort();
    }

    public static void shutdown() {
        server.shutdown();
    }

    public static Set<World> getWorlds() {
        return server.getWorlds();
    }

    public static InetAddress getServerIp() {
        return server.getServerIp();
    }

    public static Difficulty getDifficulty() {
        return server.getDifficulty();
    }

    public static String getVersion() {
        return server.getVersion();
    }

    public static Window getWindow(int id) {
        return server.getWindow(id);
    }

    public static EventHandler getEventManager() {
        return server.getEventManager();
    }

    public static void sendPluginMessage(String channel, byte... data) {
        server.sendPluginMessage(channel, data);
    }

    public static TridentPluginHandler getPluginHandler() {
        return server.getPluginHandler();
    }

    public static JsonConfig getConfig() {
        return server.getConfig();
    }
}
