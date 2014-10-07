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
package net.tridentsdk.api.event.entity;

import net.tridentsdk.api.entity.Entity;
import net.tridentsdk.api.entity.LivingEntity;

public class EntityDamageEvent extends EntityEvent {
    private final Cause cause;
    private boolean cancel;
    private double damage;

    /**
     * @param player the player associated with this event
     * @param damage the amount of damage dealt to the player
     */

    public EntityDamageEvent(Entity entity, double damage, Cause cause) {
        super(entity);
        this.setDamage(damage);
        this.cause = cause;
    }

    public Cause getCause() {
        return this.cause;
    }

    /**
     * @return return the amount of damage dealt
     */

    public double getDamage() {
        return this.damage;
    }

    /**
     * Change the damage value dealt
     *
     * @param damage the amount of damage dealt
     */

    public void setDamage(double damage) {
        this.damage = damage;
    }

    @Override
    public LivingEntity getEntity() {
        return (LivingEntity) super.getEntity();
    }

    @Override
    public boolean isCancelled() {
        return this.cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    public enum Cause {
        STARVATION,
        FIRE,
        FALL,
        EXPLOSION,
        HIT,
        ENDER_PEARL,
        PROJECTILE,
        LIGHTNING,
        DROWNING,
        SUFFOCATION,
        ANVIL,
        CONTACT,
        LAVA,
        POISON,
        WITHER,
        VOID
    }
}
