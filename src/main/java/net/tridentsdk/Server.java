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

import net.tridentsdk.config.Config;
import net.tridentsdk.entity.living.Player;
import net.tridentsdk.entity.living.ai.AiHandler;
import net.tridentsdk.plugin.cmd.ServerConsole;
import net.tridentsdk.world.World;
import net.tridentsdk.world.WorldLoader;
import net.tridentsdk.world.gen.AbstractGenerator;
import org.slf4j.Logger;

import java.net.InetAddress;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;

/**
 * The access to the server internals
 *
 * @author The TridentSDK Team
 */
public interface Server {
    /**
     * Gets the port the server currently runs on
     *
     * @return the port the server runs on
     */
    int port();

    /**
     * The server's console
     *
     * @return the server console
     */
    ServerConsole console();

    /**
     * Closes the connections of the server, disconnects all clients, and unloads everything, then exits the JVM.
     */
    void shutdown();

    /**
     * Get all the worlds loaded on the server
     *
     * @return a Map of all the worlds, where the String is the world name
     */
    Map<String, World> worlds();

    /**
     * Creates a new world loader, which can use its own generator
     *
     * <p>The provided class must have a no-arg constructor.</p>
     *
     * @param generator the generator to use, a class to defensively protect the signature
     * @return the new world loader
     */
    WorldLoader newWorldLoader(Class<? extends AbstractGenerator> generator);

    /**
     * Gets the Internet Address of this server
     *
     * @return the address of this server
     */
    InetAddress serverIp();

    /**
     * Gets the version of Trident that the server is currently running
     *
     * @return a String representing the current version of the Trident server that the server is running
     */
    String version();

    /**
     * Gets the server's display information on the server list
     *
     * @return the display information manager
     */
    DisplayInfo info();

    /**
     * Gets the server's console logger for the this class
     *
     * @return the server's logger
     */
    Logger logger();

    /**
     * The server configuration file
     *
     * @return the server config
     */
    Config config();

    /**
     * Get the player by UUID
     *
     * @param id the UUID to find the player with
     * @return the player who has the specified UUID
     */
    Player playerBy(UUID id);

    Collection<Player> onlinePlayers();

    /**
     * Returns the AI handler for the server, determines what AI entities use
     */
    AiHandler aiHandler();
}
