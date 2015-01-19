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

package net.tridentsdk.entity;

import net.tridentsdk.Coordinates;
import net.tridentsdk.docs.InternalUseOnly;
import net.tridentsdk.entity.living.Player;
import net.tridentsdk.entity.living.ProjectileLauncher;
import net.tridentsdk.entity.living.ai.AiModule;
import net.tridentsdk.entity.living.ai.Path;
import net.tridentsdk.event.entity.EntityDamageEvent;

/**
 * An entity that is alive, which can be damaged and move with AI
 *
 * @author The TridentSDK Team
 */
public interface LivingEntity extends Entity, ProjectileLauncher {
    /**
     * Makes the specified entity invisible to the current entity
     *
     * @param entity the entity to make invisible to this entity
     */
    void hide(Entity entity);

    /**
     * Un-hides the entity that was hidden from view, or does nothing of already visible
     *
     * @param entity the entity to make visible to this entity
     */
    void show(Entity entity);

    /**
     * Returns the health of the Entity
     *
     * @return double health of the Entity
     */
    double health();

    /**
     * Sets the health of the Entity
     *
     * @param health health of the Entity
     */
    void setHealth(double health);

    /**
     * Returns the maximum health of the Entity
     *
     * @return double maximum health of the Entity
     */
    double maxHealth();

    /**
     * Sets the maximum health of the Entity
     *
     * <p>maxHealth cannot be above the current health of the Entity</p>
     *
     * @param maxHealth maximum health of the Entity
     */
    void setMaxHealth(double maxHealth);

    /**
     * Returns the amount of remaining air for the Entity
     *
     * @return long remaining amount of air in ticks
     */
    long remainingAir();

    /**
     * Sets the amount of remaining air for the LivingAir
     *
     * @param ticks long amount of remaining air in ticks
     */
    void setRemainingAir(long ticks);

    /**
     * Returns the display name for the Entity
     *
     * @return String the display name for the Entity
     */
    @Override
    String displayName();

    /**
     * Returns the location of the Entity's eye
     *
     * @return Location the location of the Entity's eye
     */
    Coordinates headLocation();

    /**
     * Returns if the Entity can pickup items
     *
     * @return true if the Entity can pickup items
     */
    boolean canCollectItems();

    /**
     * Returns the last EntityDamageEvent which inflicted this Entity <p/> <p>The event may be cancelled.</p>
     *
     * @return EntityDamageEvent the last Entity to inflict this Entity
     */
    EntityDamageEvent lastDamageEvent();

    /**
     * Returns the player who dealt damage to this Entity since its last full heal <p>Used for death messages</p>
     *
     * @return Player the player who dealt damage to this entity since last full heal Returns null if no player has
     * damaged the Entity
     */
    Player lastPlayerDamager();

    /**
     * Checks if the entity has died, or has 0 health. Should only apply to entities that are {@code instanceof} {@link
     * net.tridentsdk.entity.LivingEntity}
     *
     * @return {@code true} if the entity is dead, {@code false} if it is alive
     */
    boolean isDead();

    /**
     * Gets the AI Module for this class, which will be the default if there is no special AI that has been defined for
     * this entity
     *
     * @return the AI module that controls this entity
     */
    AiModule aiModule();

    /**
     * Overides defaults and previous AI Modules for this entity, assigning a new module to it
     */
    void setAiModule(AiModule module);

    /**
     * Gets the path that this entity is currently following
     */
    Path path();

    /**
     * Sets a path for this entity to follow, should only be used in an AiHandler
     */
    void setPath(Path path);
}
