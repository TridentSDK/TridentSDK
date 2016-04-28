package com.tridentsdk;

import javax.annotation.concurrent.ThreadSafe;
import java.io.Console;
import java.net.InetSocketAddress;

/**
 * This is the representation of the server process' main
 * class.
 *
 * @author TridentSDK
 * @since 0.3-alpha-DP
 */
@ThreadSafe
public interface Server {
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
