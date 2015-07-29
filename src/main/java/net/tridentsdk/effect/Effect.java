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
package net.tridentsdk.effect;

import net.tridentsdk.entity.LivingEntity;

/**
 * Represents all visual, sound, particle and entity effects
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 */
public interface Effect<T> {

    /**
     * Execute the effect at the given location for specified player
     *
     * @param entity The entity to apply the effect on
     */
    void apply(LivingEntity entity);

    /**
     * The type of the effect
     *
     * @return The type of the effect
     */
    T type();

    /**
     * Set the type of the effect
     *
     * @param type The type of the effect
     */
    void setType(T type);

    /**
     * Returns the packet ID of the effect
     *
     * @return The packet ID of the effect
     */
    byte packetId();

}
