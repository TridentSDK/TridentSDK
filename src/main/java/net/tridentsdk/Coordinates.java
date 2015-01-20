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

package net.tridentsdk;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import net.tridentsdk.base.Block;
import net.tridentsdk.docs.PossiblyThreadSafe;
import net.tridentsdk.util.Vector;
import net.tridentsdk.world.World;

/**
 * Represents a point on the coordinate grid of the world
 *
 * @author The TridentSDK Team
 */
@PossiblyThreadSafe
public class Coordinates implements Cloneable {
    private volatile double x;
    private volatile double y;
    private volatile double z;

    private volatile World world;

    private volatile float yaw;
    private volatile float pitch;

    private Coordinates(World world, double x, double y, double z, float yaw, float pitch) {
        this.world = world;

        this.x = x;
        this.y = y;
        this.z = z;

        this.yaw = yaw;
        this.pitch = pitch;
    }

    private Coordinates(World world, double x, double y, double z) {
        this(world, x, y, z, 0.0F, 0.0F);
    }

    private static double square(double d) {
        return d * d;
    }

    /**
     * References the point on the world as a location that wraps the coordinates
     *
     * @param world the world which the point resides in
     * @param x     the x coordinate
     * @param y     the y coordinate
     * @param z     the z coordinate
     * @param yaw   goes side to side, in degrees
     * @param pitch goes up and down, in degrees
     */
    public static Coordinates create(World world, double x, double y, double z, float yaw, float pitch) {
        return new Coordinates(world, x, y, z, yaw, pitch);
    }

    /**
     * Wraps the point without specific yaw and pitch (set to 0)
     *
     * @param world the world which the point resides in
     * @param x     the x coordinate
     * @param y     the y coordinate
     * @param z     the z coordinate
     */
    public static Coordinates create(World world, double x, double y, double z) {
        return new Coordinates(world, x, y, z);
    }

    /**
     * The x position of the location
     *
     * @return the x value of this location
     */
    public double getX() {
        return this.x;
    }

    /**
     * Sets the x value of the location
     *
     * @param x the x coordinate to set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * The y position of the location
     *
     * @return the y value of this location
     */
    public double getY() {
        return this.y;
    }

    /**
     * Sets the y value of the location
     *
     * @param y the y coordinate to set
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * The z position of the location
     *
     * @return the z value of this location
     */
    public double getZ() {
        return this.z;
    }

    /**
     * Sets the z value of the location
     *
     * @param z the z coordinate to set
     */
    public void setZ(double z) {
        this.z = z;
    }

    /**
     * The world the location is in
     *
     * @return the world where the location is
     */
    public World world() {
        return this.world;
    }

    /**
     * Sets the location's world
     *
     * @param world the world to set the location to
     */
    public void setWorld(World world) {
        this.world = world;
    }

    /**
     * The yaw of the location
     *
     * @return the yaw of this location
     */
    public float yaw() {
        return this.yaw;
    }

    /**
     * Sets the yaw of the location
     *
     * @param yaw the yaw of the location to set
     */
    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    /**
     * The pitch of the location
     *
     * @return the pitch value of this location
     */
    public float pitch() {
        return this.pitch;
    }

    /**
     * Sets the pitch of the location
     *
     * @param pitch the pitch of the location to set
     */
    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    /**
     * Adds the x, y, and z from the vector to the coordinates of this location
     *
     * @param vector the vector containing the relative data
     * @return the relative location
     */
    public Coordinates add(Vector vector) {
        this.setX(vector.getX());
        this.setY(vector.getY());
        this.setZ(vector.getZ());

        return this;
    }

    /**
     * Acquires the relative location to this set of coordinates
     *
     * @param vector the vector that has the x, y, and z of the location relative to this
     * @return the relative location
     */
    public Coordinates relative(Vector vector) {
        return new Coordinates(this.world(), vector.getX() + this.getX(), vector.getY() + this.getY(),
                vector.getZ() + this.getZ(), this.yaw(), this.pitch());
    }

    /**
     * Acquires the tile at this location
     *
     * @return the tile occupying the coordinates of this location
     */
    public Block tile() {
        return world().tileAt(this);
    }

    /**
     * Creates new Vector with Location's coordinates
     *
     * @return New Vector containing this Location's coordinates
     */
    public Vector asVector() {
        return new Vector(this.getX(), this.getY(), this.getZ());
    }

    /**
     * The distance this from location to another. Math.sqrt is costly, ergo calling this method a lot is not advised.
     *
     * @param location the location to measure distance with
     * @return distance from this location to another
     */
    public double distance(Coordinates location) {
        return Math.sqrt(this.distanceSquared(location));
    }

    /**
     * The distance squared from this location to another
     *
     * @param location the location to measure distance with
     * @return distance squared from this location to another
     */
    public double distanceSquared(Coordinates location) {
        Preconditions.checkNotNull(location, "Location cannot be null.");
        if (!this.world().equals(location.world()))
            return 0.0;
        return square(this.getX() - location.getX()) + square(this.getY() - location.getY()) +
                square(this.getZ() - location.getZ());
    }

    @Override
    public Coordinates clone() {
        try {
            return (Coordinates) super.clone();
        } catch (CloneNotSupportedException ignored) {
            return null;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Coordinates))
            return false;
        if (obj.hashCode() != this.hashCode())
            return false;
        if (x != ((Coordinates) obj).x) {
            return false;
        } else if (y != ((Coordinates) obj).y) {
            return false;
        } else if (z != ((Coordinates) obj).z) {
            return false;
        } else if (world != ((Coordinates) obj).world) {
            return false;
        } else if (pitch != ((Coordinates) obj).pitch) {
            return false;
        } else if (yaw != ((Coordinates) obj).yaw) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(world, x, y, z, pitch, yaw);
    }
}
