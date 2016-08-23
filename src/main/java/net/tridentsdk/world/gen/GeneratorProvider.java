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
package net.tridentsdk.world.gen;

import net.tridentsdk.world.World;

import javax.annotation.Nonnull;
import java.util.Set;

/**
 * This class is the instance provider for all 3 generators
 * required to generate world chunks and terrain.
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
public interface GeneratorProvider {
    /**
     * Obtains the terrain generator for the world
     * possessing this generator provider, given the options
     * for generation.
     *
     * @param world the world which to provide generators,
     *        used for gathering options for generation
     * @return the terrain generator for the world
     */
    TerrainGenerator terrain(World world);

    /**
     * Obtains the set of feature generators for the world
     * possessing this generator provider, given the options
     * for generation.
     *
     * @param world the world which to provide generators,
     *        used for gathering options for generation
     * @return the feature generators for the world
     */
    @Nonnull
    Set<FeatureGenerator> featureSet(World world);

    /**
     * Obtains the set of prop generators for the world
     * possessing this generator provider, given the options
     * for generation.
     *
     * @param world the world which to provide generators,
     *        used for gathering options for generation
     * @return the prop generators for the world
     */
    @Nonnull
    Set<PropGenerator> propSet(World world);

    /**
     * Obtains the container that is used for running the
     * generator classes.
     *
     * <p>This method should never ever be overridden unless
     * the following circumstances are true:
     * 1) You are an implementation developer
     * 2) You are well versed in the threading consequences
     * that can come as a result of changing this method
     * 3) You are stupid</p>
     *
     * @return the container that runs teh generator
     */
    default GenContainer container() {
        return GenContainer.DEFAULT;
    }
}