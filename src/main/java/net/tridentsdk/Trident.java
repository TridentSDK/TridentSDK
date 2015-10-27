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
import net.tridentsdk.config.Config;
import net.tridentsdk.docs.InternalUseOnly;

import javax.annotation.concurrent.ThreadSafe;
import java.io.File;
import java.net.InetAddress;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Utility static accessor which delegates to the {@link Server}
 *
 * @author The TridentSDK Team
 * @since 0.3-alpha-DP
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
    @InternalUseOnly
    public static void setServer(Server s) {
        Preconditions.checkState(isTrident(), "Server instance can only be set by TridentSDK!");
        server = s;
    }

    @InternalUseOnly
    public static boolean isTrident() {
        return findCaller(3).getClassLoader().equals(Trident.class.getClassLoader());
    }

    @InternalUseOnly
    public static Class<?> findCaller(int index) {
        return SECURITY.getClassContext()[index];
    }

    /**
     * Returns the server's working directory, with the file separator appended
     *
     * @return the server working directory
     */
    public static Path fileContainer() {
        return Paths.get(System.getProperty("user.dir") + File.separator);
    }

    /**
     * The information displayed on the client server list when pinged
     *
     * @return the display information
     */
    public static DisplayInfo info() {
        return server.info();
    }

    /**
     * The port which the server connection has been opened on
     *
     * @return the port number
     */
    public static int port() {
        return server.port();
    }

    /**
     * Stops the server
     */
    public static void shutdown() {
        server.shutdown();
    }

    /**
     * Obtains the IP of the server
     *
     * @return the server IP
     */
    public static InetAddress ip() {
        return server.ip();
    }

    /**
     * Obtains the server release version
     *
     * @return the server release version
     */
    public static String version() {
        return server.version();
    }

    /**
     * Obtains access to the server console
     *
     * @return the console
     */
    public static ServerConsole console() {
        return server.console();
    }

    /**
     * The server configuration file
     *
     * @return the config
     */
    public static Config config() {
        return server.config();
    }

    /**
     * The singleton instance of the server
     *
     * @return the server instance
     */
    public static Server server() {
        return server;
    }
}