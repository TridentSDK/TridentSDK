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

import net.tridentsdk.config.JsonConfig;
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
    int getPort();

    /**
     * The server's console
     *
     * @return the server console
     */
    ServerConsole getConsole();

    /**
     * Closes the server as per the /stop command.
     */
    void shutdown();

    /**
     * Get all the worlds loaded on the server
     *
     * @return a Map of all the worlds, where the String is the world name
     */
    Map<String, World> getWorlds();

    /**
     * Creates a new world loader, which can use its own generator
     *
     * <p><i>The provided class must have a no-arg constructor.</i></p>
     *
     * @param generator the generator to use, a class to defensively protect the signature
     * 
     * @return The created world loader.
     */
    WorldLoader createWorldLoader(Class<? extends AbstractGenerator> generator);

    /**
     * Gets the InetAddress of this server
     *
     * @return The address of this server given by configuration.
     */
    InetAddress getServerIP();

    /**
     * Gets the version of Trident that the server is currently running.
     *
     * @return A String representing the current version of the Trident server that the server is running.
     */
    String getVersion();

    /**
     * Gets the server's display information for server list pings.
     *
     * @return The display information manager.
     */
    DisplayInfo getDisplayInfo();

    /**
     * Gets the server's console logger for the this class
     *
     * @return The server's logger.
     */
    Logger getLogger();

    /**
     * The server's configuration file.
     *
     * @return The server's configuration.
     */
    JsonConfig getConfig();

    /**
     * Gets a player with the provided UUID.
     *
     * @param id The UUID corresponding to the desired player.
     * @return The player who has the specified UUID, or null if not found.
     */
    Player getPlayer(UUID uuid);

    /**
     * Gets a listing of all currently online players.
     * @return A Collection containing all online players.
     */
    Collection<Player> getOnlinePlayers();

    /**
     * Gets the AI handler for the server, which determines what AI entities use.
     * @return The server's AI handler.
     */
    AiHandler getAIHandler();
    
    /**
     * Broadcasts a message to all currently online players.
     * @param message The message to be sent.
     */
    default void broadcastMessage(String message) {
    	getOnlinePlayers().stream().forEach((p) -> p.sendMessage(message));
    }
    
}
