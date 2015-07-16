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

package net.tridentsdk.event.entity;

import net.tridentsdk.entity.Entity;
import net.tridentsdk.entity.LivingEntity;
import net.tridentsdk.entity.living.Player;

/**
 * Called when an entity dies, especially if it was a LivingEntity
 *
 * @author The TridentSDK Team
 */
public class EntityDeathEvent extends EntityEvent {
    /**
     * @param entity the entity that has died
     */
    public EntityDeathEvent(Entity entity) {
        super(entity);
    }

    public EntityDamageEvent deathCause() {
        return this.entity().getLastDamageEvent();
    }

    /**
     * Returns a Player if a player was involved in the killing of this entity, else null
     */
    public Player killedByPlayer() {
        return this.entity().getLastPlayerDamager();
    }

    /**
     * Returns whether or not a player was involved in the killing of this entity
     */
    public boolean wasKilledByPlayer() {
        return this.killedByPlayer() == null;
    }

    @Override
    public LivingEntity entity() {
        return (LivingEntity) super.entity();
    }
}


