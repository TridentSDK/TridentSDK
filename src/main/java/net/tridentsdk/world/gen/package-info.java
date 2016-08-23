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
/**
 * This package contains classes pertaining to the
 * generation and creation of world chunks. Generators,
 * whether they be chunk generators, feature generators, or
 * prop generators are set and should not be changed once a
 * world is created.
 *
 * <p>Terrain generators generate the terrain, like hills,
 * the ground, valleys, perhaps rivers, etc...</p>
 *
 * <p>Feature generators generate strongholds, mineshafts,
 * villages, caves, end portal rooms, etc...</p>
 *
 * <p>Prop generators generate flowers, trees, mooshrooms,
 * tall grass, etc...</p>
 *
 * <p>None of the classes returned by and the
 * {@link net.tridentsdk.world.gen.GeneratorProvider}
 * itself can be guaranteed to be thread-safe, due to the
 * fact that this is designed to be implemented by the
 * client.</p>
 *
 * @see net.tridentsdk.world.gen.GeneratorProvider
 */
package net.tridentsdk.world.gen;