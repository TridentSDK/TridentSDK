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

import net.tridentsdk.world.opt.GenOpts;

import java.util.Set;

/**
 * This class is the instance provider for all 3 generators
 * required to generate world chunks and terrain.
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
public interface AbstractGeneratorFactory {
    // TODO how to force client to make this thread-safe?

    /**
     * Obtains the terrain generator for the world
     * possessing this generator provider, given the options
     * for generation.
     *
     * @param opts the generation options
     * @return the terrain generator for the world
     */
    TerrainGenerator terrain(GenOpts opts);

    /**
     * Obtains the set of feature generators for the world
     * possessing this generator provider, given the options
     * for generation.
     *
     * @param opts the generation options
     * @return the feature generators for the world
     */
    Set<FeatureGenerator> featureSet(GenOpts opts);

    /**
     * Obtains the set of prop generators for the world
     * possessing this generator provider, given the options
     * for generation.
     *
     * @param opts the generation options
     * @return the prop generators for the world
     */
    Set<PropGenerator> propSet(GenOpts opts);
}