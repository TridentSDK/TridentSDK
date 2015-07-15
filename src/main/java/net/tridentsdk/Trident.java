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
import net.tridentsdk.entity.living.Player;
import net.tridentsdk.plugin.cmd.ServerConsole;
import net.tridentsdk.world.World;
import net.tridentsdk.world.WorldLoader;
import net.tridentsdk.world.gen.AbstractGenerator;

import javax.annotation.concurrent.ThreadSafe;
import java.io.File;
import java.net.InetAddress;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Map;

/**
 * Utility static accessor to the {@link Server}
 *
 * @author The TridentSDK Team
 */
@ThreadSafe
public final class Trident {
    private static final ExposedSecurity SECURITY = new ExposedSecurity();

    private static class ExposedSecurity extends SecurityManager {
        @Override
        protected Class[] getClassContext() {
            return super.getClassContext();
        }
    }

    private static volatile Server server;

    private Trident() {
    }

    /**
     * Gets the server singleton that is currently running
     *
     * @return the server that is running
     */
    public static Server instance() {
        return server;
    }

    /**
     * Do not call
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
        return findCaller(3).getClassLoader().equals(Trident.class.getClassLoader());
    }

    @InternalUseOnly
    public static Class<?> findCaller(int index) {
        return SECURITY.getClassContext()[index];
    }

    /**
     * Returns the server's working directory, with the file spearator appended
     *
     * @return the server working directory
     */
    public static Path fileContainer() {
        return Paths.get(System.getProperty("user.dir") + File.separator);
    }

    public static DisplayInfo info() {
        return server.info();
    }

    public static int port() {
        return server.port();
    }

    public static void shutdown() {
        server.shutdown();
    }

    public static Map<String, World> worlds() {
        return server.worlds();
    }

    public static WorldLoader newWorldLoader(Class<? extends AbstractGenerator> generator) {
        return server.newWorldLoader(generator);
    }

    public static InetAddress serverIp() {
        return server.serverIp();
    }

    public static String version() {
        return server.version();
    }

    public static ServerConsole console() {
        return server.console();
    }

    public static JsonConfig config() {
        return server.config();
    }

    public static Collection<Player> onlinePlayers() {
        return server.onlinePlayers();
    }

    public static void broadcast(String str) {
        onlinePlayers().stream()
                .forEach((p) -> p.sendMessage(str));
    }

    public static Server server() {
        return server;
    }
}
