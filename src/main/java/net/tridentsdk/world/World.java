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

import net.tridentsdk.world.settings.WorldSettings;

/**
 * This class is a representation of a world
 *
 * <p>A world is defined as a folder containing compressed
 * chunk data and may be accessed using this class both
 * whilst loaded and unloaded.</p>
 *
 * @author TridentSDK
 * @since 0.3-alpha-DP
 */
public interface World {
    /**
     * Obtains the name of the world that is represented
     * with the name of the file folder containing the
     * region files.
     *
     * @return the name of the world
     */
    String name();

    /**
     * Obtains the seed used to generate the terrain and
     * features on the world.
     *
     * <p>Technical information on how this method is
     * supposed to be implemented may be found
     * <a href="http://minecraft.gamepedia.com/Seed_(level_generation)>here</a>.</p>
     *
     * @return the world seed
     */
    long seed();

    /**
     * Obtains the settings that this world has been created
     * to use.
     *
     * @return the world settings
     */
    WorldSettings settings();
}