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

import net.tridentsdk.Coordinates;
import net.tridentsdk.entity.Entity;
import net.tridentsdk.entity.EntityProperties;
import net.tridentsdk.entity.EntityType;
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
    public void teleport(Coordinates location) {
        entity.teleport(location);
    }

    @Override
    public World getWorld() {
        return entity.getWorld();
    }

    @Override
    public Coordinates getLocation() {
        return entity.getLocation();
    }

    @Override
    public Vector getVelocity() {
        return entity.getVelocity();
    }

    @Override
    public void setVelocity(Vector vector) {
        entity.setVelocity(vector);
    }

    @Override
    public void tick() {
        entity.tick();
    }

    @Override
    public boolean isOnGround() {
        return entity.isOnGround();
    }

    @Override
    public Set<Entity> getNearbyEntities(double radius) {
        return entity.getNearbyEntities(radius);
    }

    @Override
    public String getDisplayName() {
        return entity.getDisplayName();
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
    public int getId() {
        return entity.getId();
    }

    @Override
    public UUID getUniqueId() {
        return entity.getUniqueId();
    }

    @Override
    public void remove() {
        entity.remove();
    }

    @Override
    public Entity getPassenger() {
        return entity.getPassenger();
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
    public EntityType getType() {
        return entity.getType();
    }

    @Override
    public void applyProperties(EntityProperties properties) {
        entity.applyProperties(properties);
    }
}
