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
package net.tridentsdk.factory;

import net.tridentsdk.world.Chunk;
import net.tridentsdk.world.gen.TempGenBlock;

/**
 * Produces world generation utilities and general classes needed to create worlds
 *
 * @author The TridentSDK Team
 */
public interface GenFactory {
    void putBlock(TempGenBlock tile, Chunk world);
}
