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
package net.tridentsdk.world;

import net.tridentsdk.Server;
import net.tridentsdk.world.opt.WorldCreateSpec;

import javax.annotation.Nonnull;
import java.util.Map;

/**
 * This class handles the world lifecycle, creating,
 * loading, and saving worlds.
 *
 * <p>This is basically a world registry.</p>
 *
 * <p>The instance of this class may be obtained using
 * {@link Server#getWorldLoader()}.</p>
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
public interface WorldLoader {
    /**
     * Obtains all of the worlds that are currently loaded
     * on the server.
     *
     * @return all of the loaded worlds
     */
    Map<String, World> getWorlds();

    /**
     * Obtains the default world that new players will join
     * upon connection to the server.
     *
     * @return the default world
     */
    World getDefault();

    /**
     * Obtains the world from the set of loaded worlds, or
     * if it has not loaded yet, attempt to load the world.
     *
     * @param name the name of the world
     * @return the world, or if it doesn't exist, throw an
     * exception
     */
    @Nonnull
    World get(String name);

    /**
     * Creates a new world using the given parameters as
     * the world properties.
     *
     * @param name the name of the new world
     * @param spec the world options
     */
    World create(String name, WorldCreateSpec spec);

    /**
     * Removes the given world by unloading and then
     * deleting the associated files.
     *
     * @param world the world to delete
     * @return {@code true} if the operation resulted in a
     * change, {@code false} if it failed or the world does
     * not exist.
     */
    boolean delete(World world);
}