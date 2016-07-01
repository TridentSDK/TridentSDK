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

import net.tridentsdk.world.opt.GenOpts;
import net.tridentsdk.world.opt.Weather;
import net.tridentsdk.world.opt.WorldBorder;
import net.tridentsdk.world.opt.WorldOpts;

import javax.annotation.concurrent.ThreadSafe;

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
@ThreadSafe
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
     * Obtains the options that this world has been created
     * to use.
     *
     * @return the world options
     */
    WorldOpts opts();

    /**
     * Obtains the weather conditions currently taking place
     * in the world.
     *
     * @return the weather options
     */
    Weather weather();

    /**
     * Obtains the options for the generator of this world.
     *
     * @return the generator options
     */
    GenOpts genOpts();

    /**
     * Obtains the world border
     *
     * @return the world border options
     */
    WorldBorder border();
}