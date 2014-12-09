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
package net.tridentsdk.base;

import com.google.common.base.Function;
import com.google.common.collect.*;
import net.tridentsdk.Coordinates;
import net.tridentsdk.docs.InternalUseOnly;
import net.tridentsdk.entity.Entity;
import net.tridentsdk.entity.decorate.Impalable;
import net.tridentsdk.entity.projectile.Projectile;
import net.tridentsdk.factory.Factories;
import net.tridentsdk.util.Vector;

import javax.annotation.Nullable;
import java.lang.ref.WeakReference;
import java.util.*;

/**
 * A basic structure in minecraft, a material bearing piece set at a given location
 *
 * @author The TridentSDK Team
 */
public class Tile implements Impalable {
    private final Coordinates location;
    /**
     * The type for this block
     */
    protected Substance material;
    /**
     * The block metadata
     */
    protected byte data;
    /**
     * Describes projectile logic
     */
    private final Set<WeakReference<Projectile>> projectiles = Sets.newSetFromMap(
            Factories.collect().<WeakReference<Projectile>, Boolean>createMap());

    /**
     * Constructs the wrapper representing the block
     *
     * @param location Location of the Block
     */
    public Tile(Coordinates location) {
        this.location = location;

        // Note: Avoid recursion by not creating a new instance from World#getTileAt(Location)
        Tile worldBlock = location.getWorld().getTileAt(location);

        this.material = worldBlock.material;
    }

    @InternalUseOnly
    protected Tile(Coordinates location, boolean createdByServer) {
        this.location = location;
    }

    public void setSubstance(Substance material) {
        this.material = material;
    }

    public Substance getSubstance() {
        return this.material;
    }

    /**
     * Returns the Location of the Block
     *
     * @return Location of the Block
     */
    public Coordinates getLocation() {
        return this.location;
    }

    /**
     * Gets the tile data
     *
     * @return the data of the tile
     */
    public byte getMeta() {
        return this.data;
    }

    /**
     * Sets the tile data
     *
     * @param data the data to set the tile
     */
    public void setMeta(byte data) {
        this.data = data;
    }

    /**
     * Returns a block immediately to the direction specified
     *
     * @param vector the direction to look for the block adjacent to the current
     * @return the block adjacent to the current
     */
    public Tile relativeTile(Vector vector) {
        return new Tile(this.location.getRelative(vector));
    }

    @Override
    public boolean isImpaledEntity() {
        return false;
    }

    @Override
    public boolean isImpaledTile() {
        return true;
    }

    @Override
    public Entity impaledEntity() {
        return null;
    }

    @Override
    public Tile impaledTile() {
        if (!this.isImpaledTile())
            return null;
        return this;
    }

    @Override
    public void put(Projectile projectile) {
        this.projectiles.add(new WeakReference<>(projectile));
    }

    @Override
    public boolean remove(Projectile projectile) {
        return this.projectiles.remove(new WeakReference<>(projectile));
    }

    @Override
    public void clear() {
        // TODO remove the projectile entities
        this.projectiles.clear();
    }

    @Override
    public Collection<Projectile> projectiles() {
        return new ImmutableSet.Builder<Projectile>().addAll(Iterators.transform(projectiles.iterator(),
                new Function<WeakReference<Projectile>, Projectile>() {
                    @Nullable
                    @Override
                    public Projectile apply(WeakReference<Projectile> projectileWeakReference) {
                        return projectileWeakReference.get();
                    }
                })).build();
    }
}
