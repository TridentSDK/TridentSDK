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

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import net.tridentsdk.util.Vector;
import net.tridentsdk.world.Chunk;
import net.tridentsdk.world.World;

import javax.annotation.concurrent.ThreadSafe;

/**
 * Represents a point on the coordinate grid of the world, including pitch and yaw
 *
 * @author The TridentSDK Team
 * @since 0.3-alpha-DP
 */
@ThreadSafe
public final class Position extends Vector implements Cloneable {
    private volatile World world;
    private volatile float yaw;
    private volatile float pitch;

    private Position(World world, double x, double y, double z, float yaw, float pitch) {
        this.world = world;

        this.x = x;
        this.y = y;
        this.z = z;

        this.yaw = yaw;
        this.pitch = pitch;
    }

    public Position(World world, double x, double y, double z) {
        this(world, x, y, z, 0.0F, 0.0F);
    }

    private static double square(double d) {
        return d * d;
    }

    /**
     * References the point on the world as a position that wraps the coordinates
     *
     * @param world the world which the point resides in
     * @param x     the x coordinate
     * @param y     the y coordinate
     * @param z     the z coordinate
     * @param yaw   goes side to side, in degrees
     * @param pitch goes up and down, in degrees
     */
    public static Position create(World world, double x, double y, double z, float yaw, float pitch) {
        return new Position(world, x, y, z, yaw, pitch);
    }

    /**
     * Wraps the point without specific yaw and pitch (set to 0)
     *
     * @param world the world which the point resides in
     * @param x     the x coordinate
     * @param y     the y coordinate
     * @param z     the z coordinate
     */
    public static Position create(World world, double x, double y, double z) {
        return new Position(world, x, y, z);
    }

    /**
     * The world the position is in
     *
     * @return the world where the position is
     */
    public World world() {
        return this.world;
    }

    /**
     * Chunk of the current position
     *
     * @return Chunk of the position
     */
    public Chunk chunk() {
        return world.chunkAt((int) x >> 4, (int) z >> 4, true);
    }

    /**
     * Sets the position's world
     *
     * @param world the world to set the position to
     */
    public void setWorld(World world) {
        this.world = world;
    }

    /**
     * The yaw of the position
     *
     * @return the yaw of this position
     */
    public float yaw() {
        return this.yaw;
    }

    /**
     * Sets the yaw of the position
     *
     * @param yaw the yaw of the position to set
     */
    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    /**
     * The pitch of the position
     *
     * @return the pitch value of this position
     */
    public float pitch() {
        return this.pitch;
    }

    /**
     * Sets the pitch of the position
     *
     * @param pitch the pitch of the position to set
     */
    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    /**
     * Adds the x, y, and z from the vector to the coordinates of this position
     *
     * @param vector the vector containing the relative data
     * @return the relative position
     */
    public Position add(Vector vector) {
        this.setX(x() + vector.x());
        this.setY(y() + vector.y());
        this.setZ(z() + vector.z());

        return this;
    }

    /**
     * Acquires the relative position to this set of coordinates
     *
     * @param vector the vector that has the x, y, and z of the position relative to this
     * @return the relative position
     */
    public Position relative(Vector vector) {
        return new Position(this.world(), vector.x() + this.x(), vector.y() + this.y(),
                vector.z() + this.z(), this.yaw(), this.pitch());
    }

    /**
     * Acquires the tile at this position
     *
     * @return the tile occupying the coordinates of this position
     */
    public Block block() {
        return world().blockAt(this);
    }

    /**
     * Creates new Vector with Location's coordinates
     *
     * @return New Vector containing this Location's coordinates
     */
    public Vector asVector() {
        return new Vector(this.x(), this.y(), this.z());
    }

    /**
     * Obtains the unit vector pointing in the direction of the pitch and yaw
     *
     * @return the direction vector
     */
    public Vector asUnitVector() {
        // queerest calculations ever, apparently x goes towards 3pi/2 and z to 0...?
        double x = -Math.cos(pitch) * Math.sin(yaw);
        double y = -Math.sin(pitch);
        double z = Math.cos(pitch) * Math.cos(yaw);

        return new Vector(x, y, z);
    }

    /**
     * The distance this from position to another. Math.sqrt is costly, ergo calling this method a lot is not advised.
     *
     * @param position the position to measure distance with
     * @return distance from this position to another
     */
    public double distance(Position position) {
        return Math.sqrt(this.distanceSquared(position));
    }

    /**
     * The distance squared from this position to another
     *
     * @param position the position to measure distance with
     * @return distance squared from this position to another
     */
    public double distanceSquared(Position position) {
        Preconditions.checkNotNull(position, "Location cannot be null.");
        if (!this.world().equals(position.world()))
            return 0.0;
        return square(this.x() - position.x()) + square(this.y() - position.y()) +
                square(this.z() - position.z());
    }

    @Override
    public Position clone() {
        return new Position(world, x, y, z(), yaw, pitch);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Position))
            return false;
        if (obj.hashCode() != this.hashCode())
            return false;
        if (x != ((Position) obj).x) {
            return false;
        } else if (y != ((Position) obj).y) {
            return false;
        } else if (z != ((Position) obj).z) {
            return false;
        } else if (world != ((Position) obj).world) {
            return false;
        } else if (pitch != ((Position) obj).pitch) {
            return false;
        } else if (yaw != ((Position) obj).yaw) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(world, x, y, z, pitch, yaw);
    }
}
