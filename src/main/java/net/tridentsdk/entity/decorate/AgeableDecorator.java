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

package net.tridentsdk.entity.decorate;

import net.tridentsdk.entity.Entity;

/**
 * Represents a LivingEntity that has an age and has the ability to bread
 *
 * @author TridentSDK Team
 */
public abstract class AgeableDecorator implements LivingEntity {
    private final Entity entity;
    private int age;

    protected AgeableDecorator(Entity entity) {
        this.entity = entity;
    }

    /**
     * The current age of this entity, in ticks
     *
     * @return the age of this entity
     */
    int getAge() {
        return age;
    }

    /**
     * Set the current age of this entity, in ticks
     *
     * @param ticks the age to set
     */
    void setAge(int ticks) {
        this.age = ticks;
    }

    /**
     * Whether or not this entity can breed or not, where the ability to breed represents whether or not this entity can
     * become "in love"
     *
     * @return whether or not this entity can be bred
     */
    abstract boolean canBreed();

    /**
     * Whether or not this entity is "in love", such that it will actively display the particle effect for breeding
     * hearts and search for a mate
     *
     * @return whether or not this entity is in love
     */
    abstract boolean isInLove();
}
