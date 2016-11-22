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

/**
 * This class generates props in the world such as trees,
 * flowers, tall grass, etc...
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
public interface PropGenerator {
    /**
     * A prop generator is implemented by overriding this
     * method and writing the generated blocks to the
     * context.
     *
     * @param chunkX the chunk x
     * @param chunkZ the chunk z
     * @param context the context
     */
    void generate(int chunkX, int chunkZ, GeneratorContext context);
}