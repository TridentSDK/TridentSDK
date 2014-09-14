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
import net.tridentsdk.api.util.Vector;
import net.tridentsdk.api.world.World;

import java.util.List;

/**
 * Represents the abstraction for a mob, player, animal, or other "object" that is not a block type
 *
 * @author The TridentSDK Team
 */
public interface Entity {
    /**
     * Moves the entity to the specified location
     *
     * @param x the x coordinate of the location
     * @param y the y coordinate of the location
     * @param z the z coordinate of the location
     */
    void teleport(double x, double y, double z);

    /**
     * Moves the current entity to the provided entity's location
     *
     * @param entity the entity to move the current entity to
     */
    void teleport(Entity entity);

    /**
     * Moves the entity to the coordinates specified by the location object passed in
     *
     * @param location the location to move the entity to
     */
    void teleport(Location location);

    /**
     * The world which the entity resides in
     *
     * @return the world that contains the entity
     */
    World getWorld();

    /**
     * The location of the entity with respect to the coordinate grid
     *
     * @return the entity's location
     */
    Location getLocation();

    /**
     * The direction and movement magnitude of the entity
     *
     * @return the vector representing the entity's velocity
     */
    Vector getVelocity();

    /**
     * Sets the entity's movement direction and speed to the magnitude of the vector
     *
     * @param vector the vector to set the entity velocity to
     */
    void setVelocity(Vector vector);

    /**
     * Perform entity management tasks in the 1/20 second server heartbeat
     */
    void tick();

    /**
     * Checks if the entity is currently on the ground, or at least touching the ground
     *
     * @return {@code true} if the entity touches the ground, {@code false} if it is in the air (such as if it was
     * falling)
     */
    boolean isOnGround();

    /**
     * Gets the entities that are close to this entity
     *
     * @param radius the spherical radius to look for entities around
     * @return the collection of entities within the radius around the entity
     */
    List<Entity> getNearbyEntities(double radius);

    /**
     * The identifier for this entity
     *
     * @return the unique id to all entities on the server
     */
    int getId();

    /**
     * Removes the entity from the world and destroys it, freeing all memory associated with it
     */
    void remove();

    /**
     * Gets the entity that is riding this entity, if there is any
     *
     * @return the entity that is riding, {@code null} if there are none
     */
    Entity getPassenger();

    /**
     * Mounts the specified entity on this entity
     *
     * @param entity the entity to set passenger to this entity
     */
    void setPassenger(Entity entity);

    /**
     * Removes the mounted entity, if there are any
     */
    void eject();

    /**
     * Gets the type of entity
     *
     * @return the entity type that is represented
     */
    EntityType getType();

    /**
     * Sets the properties of this entity to the specified properties
     *
     * @param properties the properties to set
     */
    void applyProperties(EntityProperties properties);
}
