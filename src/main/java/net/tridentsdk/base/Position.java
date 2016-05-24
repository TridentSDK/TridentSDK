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

import net.tridentsdk.world.World;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.atomic.LongAdder;

/**
 * Position is a container class that represents a three-
 * dimensional coordinate within a specific world.
 *
 * <p>This container also may hold a yaw and pitch values
 * for use with rotatable objects such as entities.</p>
 *
 * @author TridentSDK
 * @since 0.3-alpha-DP
 */
@ThreadSafe
public final class Position extends Vector {
    private static final long serialVersionUID = 5910507790866074403L;

    /** Additional fields representing the state of the
     * position */
    private final World world;    // These fields MAY NOT
    private volatile float pitch; // be updated along with
    private volatile float yaw;   // a compound modification

    /**
     * Creates a Position in a given world with all
     * coordinates and directions set to 0.
     *
     * @param world the world that this position is set to
     *              reside in
     */
    public Position(World world) {
        this(world, 0D, 0D, 0D, 0F, 0F);
    }

    /**
     * Creates a Position in a given world and integer
     * Cartesian coordinates with all directions set to 0.
     *
     * @param world the world that this position is set to
     *              reside in
     * @param x the x coordinate
     * @param y the y coordinate
     * @param z the z coordinate
     */
    public Position(World world, int x, int y, int z) {
        this(world, (double) x, (double) y, (double) z, 0F, 0F);
    }

    /**
     * Creates a Position in a given world and {@code double}
     * Cartesian coordinates with all directions set to 0.
     *
     * @param world the world that this position is set to
     *              reside in
     * @param x the x coordinate
     * @param y the y coordinate
     * @param z the z coordinate
     */
    public Position(World world, double x, double y, double z) {
        this(world, x, y, z, 0F, 0F);
    }

    /**
     * Creates a Position in a given world, {@code double}
     * Cartesian coordinates, and directional values.
     *
     * @param world the world that this position is set to
     *              reside in
     * @param x the x coordinate
     * @param y the y coordinate
     * @param z the z coordinate
     * @param yaw the yaw directional
     * @param pitch the pitch directional
     */
    public Position(World world, double x, double y, double z, float yaw, float pitch) {
        super(x, y, z);
        this.world = world;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    /**
     * Obtains the World in which this Position is set to
     * reside in.
     *
     * @return the world containing this Position
     */
    public World world() {
        return this.world;
    }

    /**
     * Obtains the yaw at which this position is pointed to.
     *
     * <p>Yaw = horizontal rotation</p>
     *
     * @return this position's yaw
     */
    public float yaw() {
        return this.yaw;
    }

    /**
     * Obtains the pitch at which this position is pointed to.
     *
     * <p>Pitch = vertical rotation</p>
     *
     * @return this position's pitch
     */
    public float pitch() {
        return this.pitch;
    }

    // TODO
}
