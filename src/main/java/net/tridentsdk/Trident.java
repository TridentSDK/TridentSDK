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
import net.tridentsdk.docs.InternalUseOnly;
import net.tridentsdk.event.EventHandler;
import net.tridentsdk.plugin.TridentPluginHandler;
import net.tridentsdk.window.Window;
import net.tridentsdk.world.World;

import javax.annotation.concurrent.ThreadSafe;
import java.net.InetAddress;
import java.util.Map;

/**
 * Utility accessor to the {@link Server}
 *
 * @author The TridentSDK Team
 */
@ThreadSafe
public final class Trident {
    private static Server server;
    private static final ExposedSecurity SECURITY = new ExposedSecurity();
    private static class ExposedSecurity extends SecurityManager {
        @Override
        protected Class[] getClassContext() {
            return super.getClassContext();
        }
    }

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
     * Do not call <
     *
     * <p>Will throw an exception if you are not calling from a trusted source</p>
     *
     * @param s the server to set
     */
    public static void setServer(Server s) {
        Preconditions.checkState(isTrident(), "Server instance can only be set by TridentSDK!");
        server = s;
    }

    public static boolean isTrident() {
        return getCaller(3).getClassLoader().equals(Trident.class.getClassLoader());
    }

    @InternalUseOnly
    public static Class<?> getCaller(int index) {
        return SECURITY.getClassContext()[index];
    }

    public static int getPort() {
        return server.port();
    }

    public static void shutdown() {
        server.shutdown();
    }

    public static Map<String, World> getWorlds() {
        return server.worlds();
    }

    public static InetAddress getServerIp() {
        return server.serverIp();
    }

    public static String getVersion() {
        return server.version();
    }

    public static Window getWindow(int id) {
        return server.windowBy(id);
    }

    public static EventHandler getEventHandler() {
        return server.eventHandler();
    }

    public static void sendPluginMessage(String channel, byte... data) {
        server.sendPluginMessage(channel, data);
    }

    public static TridentPluginHandler getPluginHandler() {
        return server.getPluginHandler();
    }

    public static JsonConfig getConfig() {
        return server.config();
    }
}
