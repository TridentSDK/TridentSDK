/*
 * Copyright (c) 2014, The TridentSDK Team
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     1. Redistributions of source code must retain the above copyright
 *        notice, this list of conditions and the following disclaimer.
 *     2. Redistributions in binary form must reproduce the above copyright
 *        notice, this list of conditions and the following disclaimer in the
 *        documentation and/or other materials provided with the distribution.
 *     3. Neither the name of the The TridentSDK Team nor the
 *        names of its contributors may be used to endorse or promote products
 *        derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL The TridentSDK Team BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package net.tridentsdk.api.entity;

import net.tridentsdk.api.Location;
import net.tridentsdk.api.entity.living.Player;
import net.tridentsdk.api.entity.living.ProjectileSource;
import net.tridentsdk.api.event.entity.EntityDamageEvent;

public interface LivingEntity extends Entity, ProjectileSource {

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
    double getHealth();

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
    double getMaxHealth();

    /**
     * Sets the maximum health of the Entity
     * <p>maxHealth cannot be above the current health of the Entity</p>
     * TODO: Rephrase?
     *
     * @param maxHealth maximum health of the Entity
     */
    void setMaxHealth(double maxHealth);

    /**
     * Returns the amount of remaining air for the Entity
     *
     * @return long remaining amount of air in ticks
     */
    long getRemainingAir();

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
    String getDisplayName();

    /**
     * Returns the location of the Entity's eye
     *
     * @return Location the location of the Entity's eye
     */
    Location getEyeLocation();

    /**
     * Returns if the Entity can pickup items
     *
     * @return true if the Entity can pickup items
     */
    boolean canPickupItems();

    /**
     * Returns the last EntityDamageEvent which inflicted this Entity
     * <p>The event may be cancelled.</p>
     * 
     * @return EntityDamageEvent the last Entity to inflict this Entity
     */
    EntityDamageEvent getLastDamageCause();

    /**
     * Returns the player who dealt damage to this Entity since its last full heal
     * <p>Used for death messages</p>
     *
     * @return Player the player who dealt damage to this entity since last full heal
     *          Returns null if no player has damaged the Entity
     */
    Player hurtByPlayer();

    /**
     * Checks if the entity has died, or has 0 health. Should only apply to entities that are "live" (TODO
     * Entity)
     *
     * @return {@code true} if the entity is dead, {@code false} if it is alive
     */
    boolean isDead();
}
