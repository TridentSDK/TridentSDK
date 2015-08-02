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
package net.tridentsdk.effect.entity;

import net.tridentsdk.effect.Effect;
import net.tridentsdk.entity.Entity;

/**
 * Represents an entity effect
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 */
public interface EntityStatusEffect extends Effect<EntityStatusEffectType> {

    /**
     * Set the entity to which the status effect will be applied
     *
     * @param entity The entity to which the status effect will be applied
     */
    void setEntity(Entity entity);

    /**
     * Returns the entity to which the status effect will be applied
     *
     * @return The entity to which the status effect will be applied
     */
    Entity entity();

}
