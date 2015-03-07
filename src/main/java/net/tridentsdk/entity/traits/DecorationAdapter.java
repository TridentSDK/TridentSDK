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

import net.tridentsdk.Position;
import net.tridentsdk.entity.Entity;
import net.tridentsdk.entity.types.EntityType;
import net.tridentsdk.util.Vector;
import net.tridentsdk.world.World;

import java.util.Set;
import java.util.UUID;

/**
 * Adapts an entity with the original instance to delegate functionality of a decorator away from implementation
 * specifics
 *
 * @param <T> the entity type to delegate to
 * @author The TridentSDK Team
 */
public class DecorationAdapter<T extends Entity> implements Entity {
    private final T entity;

    /**
     * Constructs the delegate to entity adaptation for decorators
     *
     * @param entity the original entity to delegate to
     */
    protected DecorationAdapter(T entity) {
        this.entity = entity;
    }

    /**
     * The entity used for delegation functionality
     *
     * @return the original entity passed through the constructor
     */
    protected T original() {
        return this.entity;
    }

    @Override
    public void teleport(double x, double y, double z) {
        entity.teleport(x, y, z);
    }

    @Override
    public void teleport(Entity entity) {
        entity.teleport(entity);
    }

    @Override
    public void teleport(Position location) {
        entity.teleport(location);
    }

    @Override
    public World world() {
        return entity.world();
    }

    @Override
    public Position position() {
        return entity.position();
    }

    @Override
    public Vector velocity() {
        return entity.velocity();
    }

    @Override
    public void setVelocity(Vector vector) {
        entity.setVelocity(vector);
    }

    @Override
    public boolean onGround() {
        return entity.onGround();
    }

    @Override
    public Set<Entity> withinRange(double radius) {
        return entity.withinRange(radius);
    }

    @Override
    public String displayName() {
        return entity.displayName();
    }

    @Override
    public void setDisplayName(String name) {
        entity.setDisplayName(name);
    }

    @Override
    public boolean isNameVisible() {
        return entity.isNameVisible();
    }

    @Override
    public boolean isSilent() {
        return entity.isSilent();
    }

    @Override
    public int entityId() {
        return entity.entityId();
    }

    @Override
    public UUID uniqueId() {
        return entity.uniqueId();
    }

    @Override
    public void remove() {
        entity.remove();
    }

    @Override
    public Entity passenger() {
        return entity.passenger();
    }

    @Override
    public void setPassenger(Entity entity) {
        entity.setPassenger(entity);
    }

    @Override
    public void eject() {
        entity.eject();
    }

    @Override
    public EntityType type() {
        return entity.type();
    }

    @Override
    public void applyProperties(EntityProperties properties) {
        entity.applyProperties(properties);
    }
}
