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
package net.tridentsdk.world.opt;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

/**
 * The options pertaining to how the chunks within the world
 * that uses these options will generate.
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
@ThreadSafe
public interface GenOpts {
    /**
     * Obtains the name of the generator that provides the
     * facilities for creating chunks in the world.
     *
     * @return the name of the generator
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
     * The input used to create the seed for the generator,
     * or an empty string if it was randomly generated.
     *
     * @return the input that created the seed
     */
    @Nonnull
    String seedInput();

    /**
     * Determine whether world features such as villages,
     * strongholds, and mineshafts should be generated.
     *
     * @return {@code true} to indicate that features should
     *         be generated, {@code false} if not
     */
    boolean allowFeatures();
}