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

package net.tridentsdk.entity.traits;

import java.util.UUID;

/**
 * Represents a tameable entity
 *
 * @author TridentSDK Team
 */
public interface Tameable extends Ageable {
    /**
     * Whether or not this entity is tamed
     *
     * @return whether or not this entity is tamed
     */
    boolean isTamed();

    /**
     * The UUID of this entity's owner
     *
     * @return the UUID of the {@link net.tridentsdk.entity.living.Player}Player that owns this entity, {@code null} if
     * untamed
     */
    UUID getTamer();
    
    /**
     * Sets the tamer of this entity.
     * @param uuid The UUID of the tamer, or null if setting to untamed.
     */ 
    void setTamer(UUID uuid);

    /**
     * Whether or not this entity is sitting
     *
     * @return whether or not this entity is sitting
     */
    boolean isSitting();
    
    /**
     * Sets whether or not this entity is sitting.
     * @param sitting Whether the entity should sit or not.
     */
    void setSitting(boolean sitting);
    
}
