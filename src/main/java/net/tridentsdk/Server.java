/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2016 The TridentSDK Team
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

import javax.annotation.concurrent.ThreadSafe;
import java.io.Console;
import java.net.InetSocketAddress;

/**
 * This is the representation of the server process' main
 * class.
 *
 * <p>The instance of the server may be obtained through
 * the {@link #instance()} method.</p>
 *
 * @author TridentSDK
 * @since 0.3-alpha-DP
 */
@ThreadSafe
public interface Server {
    /**
     * Obtains the singleton instance of the server provided
     * by the implementation.
     *
     * @return the server
     */
    static Server instance() {
        return Impl.get().svr();
    }

    /**
     * This method obtains the address + port number of the
     * server's socket.
     *
     * @return the socket address
     */
    InetSocketAddress address();

    /**
     * This method obtains the console as defined by the
     * server.
     *
     * <p>This may be a virtual console, an OS console, or
     * other, including but not limited to the listed
     * options above.</p>
     *
     * @return the server representation of the console
     */
    Console console();

    /**
     * This method obtains an arbitrary String that
     * signifies the current server version.
     *
     * @return the server's version String
     */
    String version();

    /**
     * This method causes the server to save the current
     * state, unload all server objects such as worlds and
     * plugins, and load them again, while keeping existing
     * connections to the socket alive.
     *
     * <p>This can be thought of as a restart, but due to
     * players remaining on the server, certain procedures
     * implemented by plugins may not expect players to
     * be existing already. It is up to plugin authors to
     * correctly implement their plugins to handle teardowns
     * appropriately.</p>
     */
    void reload();

    /**
     * This method causes the server to shutdown.
     *
     * <p>"Shutdown" means that the server should properly
     * handle unsaved states and unload those assets before
     * completely stopping the server process.</p>
     */
    void shutdown();
}