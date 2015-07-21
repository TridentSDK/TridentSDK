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

import net.tridentsdk.base.Position;
import net.tridentsdk.entity.traits.EntityProperties;
import net.tridentsdk.entity.types.EntityType;
import net.tridentsdk.util.Vector;
import net.tridentsdk.world.World;

import java.util.Set;
import java.util.UUID;

/**
 * Represents the abstraction for a mob, player, animal, or other "object" that is not a block type
 *
 * @author The TridentSDK Team
 * @since 0.3-alpha-DP
 */
public interface Entity {
    /**
     * Moves the entity to the specified position
     *
     * @param x the x coordinate of the position
     * @param y the y coordinate of the position
     * @param z the z coordinate of the position
     */
    void teleport(double x, double y, double z);

    /**
     * Moves the current entity to the provided entity's position
     *
     * @param entity the entity to move the current entity to
     */
    void teleport(Entity entity);

    /**
     * Moves the entity to the coordinates specified by the position object passed in
     *
     * @param position the position to move the entity to
     */
    void teleport(Position position);

    /**
     * The world which the entity resides in
     *
     * @return the world that contains the entity
     */
    World world();

    /**
     * The position of the entity with respect to the coordinate grid
     *
     * @return the entity's position
     */
    Position position();

    /**
     * The direction and movement magnitude of the entity
     *
     * @return the vector representing the entity's velocity
     */
    Vector velocity();

    /**
     * Sets the entity's movement direction and speed to the magnitude of the vector
     *
     * @param vector the vector to set the entity velocity to
     */
    void setVelocity(Vector vector);

    /**
     * Checks if the entity is currently on the ground, or at least touching the ground
     *
     * @return {@code true} if the entity touches the ground, {@code false} if it is in the air (such as if it was
     * falling)
     */
    boolean onGround();

    /**
     * Gets the entities that are within proximity to this entity
     *
     * @param radius the spherical radius to look for entities around
     * @return the collection of entities within the radius around the entity
     */
    Set<Entity> withinRange(double radius);

    /**
     * Gets the display name for the entity, used on inventories and deaths
     *
     * @return Display name
     */
    String displayName();

    /**
     * Sets the entity's display name, effects inventories (if applicable) and death messages
     *
     * @param name Entity name
     */
    void setDisplayName(String name);

    /**
     * Gets if the entity's display name visible
     *
     * @return if the entity's display name visible
     */
    boolean isNameVisible();

    /**
     * Gets if the entity is silent (sounds)
     *
     * @return if the entity is silent
     */
    boolean isSilent();

    /**
     * The identifier for this entity for runtime, see uniqueId for a set id of the entity
     *
     * @return the id to all entities on the server at runtime
     * @see net.tridentsdk.entity.Entity#uniqueId()
     */
    int entityId();

    /**
     * The unique id for the entity to the server
     *
     * @return The unique id for the entity
     */
    UUID uniqueId();

    /**
     * Removes the entity from the world and destroys it, freeing all memory associated with it
     */
    void remove();

    /**
     * Gets the entity that is riding this entity, if there is any
     *
     * @return the entity that is riding, {@code null} if there are none
     */
    Entity passenger();

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
    EntityType type();

    /**
     * Sets the properties of this entity to the specified properties
     *
     * @param properties the properties to set
     */
    void applyProperties(EntityProperties properties);
}
