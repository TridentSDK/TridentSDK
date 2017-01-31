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

import net.tridentsdk.command.logger.Logger;
import net.tridentsdk.config.Config;
import net.tridentsdk.entity.living.Player;
import net.tridentsdk.event.EventController;
import net.tridentsdk.world.WorldLoader;

import javax.annotation.concurrent.ThreadSafe;
import java.util.Collection;

/**
 * This is the representation of the server process' main
 * class.
 *
 * <p>The instance of the server may be obtained through
 * the {@link #getInstance()} method.</p>
 *
 * @author TridentSDK
 * @since 0.3-alpha-DP
 */
@ThreadSafe
public interface Server {
    /**
     * The current version of the server
     */
    String VERSION = "0.5-alpha";

    /**
     * Obtains the singleton instance of the server provided
     * by the implementation.
     *
     * @return the server
     */
    static Server getInstance() {
        return Impl.get().svr();
    }

    /**
     * Obtains the internal address to which the server
     * opens its socket.
     *
     * @return the internal IP
     */
    String getIp();

    /**
     * Obtains the port which the server selects to open its
     * socket.
     *
     * @return the port
     */
    int getPort();

    /**
     * This method obtains the logger as defined by the
     * server.
     *
     * <p>This may be a virtual logger, an OS logger, or
     * other, including but not limited to the listed
     * options above.</p>
     *
     * @return the server representation of the logger
     */
    Logger getLogger();

    /**
     * Obtains the configuration file that is used to set
     * options regarding how the server is to be run.
     *
     * @return the server config
     */
    Config getConfig();

    /**
     * Obtains a collection of players which are currently
     * connected to this server.
     *
     * @return the current online players
     */
    Collection<Player> getPlayers();

    /**
     * Obtains the singleton instance of the global world
     * handler.
     *
     * @return the instance of the server world loader
     */
    WorldLoader getWorldLoader();

    /**
     * Obtains the event controller for the server.
     *
     * @return the server's event controller
     */
    EventController getEventController();

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