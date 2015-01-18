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

package net.tridentsdk.entity.living.ai;

import net.tridentsdk.entity.EntityType;

/**
 * Handles the assigning of AIs to Entities on the server.
 */
public interface AiHandler {

    /**
     * Returns the AI Module that is the default for a certain type of entity
     */
    AiModule defaultAIFor(EntityType type);

    /**
     * Sets the AI for this entity type, used to provide a separate AiModule for
     * Entities
     *
     * <p>Entities of this type in the world will immediately start using this
     * {@link net.tridentsdk.entity.living.ai.AiModule}, even if they were
     * created before this was set.</p>
     *
     * @param type   the type of entity to replace the AI for
     * @param module the new AiModule to use
     */
    void setDefaultAiFor(EntityType type, AiModule module);

    /**
     * Returns the server's implementation of the AI for a given entity type
     *
     * @param type the type of entity to request the AI for
     */
    AiModule nativeAIFor(EntityType type);
}