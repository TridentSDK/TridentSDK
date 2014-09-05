/*
 * Copyright (C) 2014 The TridentSDK Team
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.tridentsdk.api;

import net.tridentsdk.api.util.Vector;
import net.tridentsdk.api.world.World;
import org.apache.commons.lang.Validate;

import java.io.Serializable;

/**
 * Represents a point on the coordinate grid of the world
 *
 * @author The TridentSDK Team
 */
public class Location implements Serializable, Cloneable {
    private static final long serialVersionUID = 142870546231866867L;

    private double x;
    private double y;
    private double z;

    private World world;

    private float yaw;
    private float pitch;

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
    public Location(World world, double x, double y, double z, float yaw, float pitch) {
        this.world = world;

        this.x = x;
        this.y = y;
        this.z = z;

        this.yaw = yaw;
        this.pitch = pitch;
    }

    /**
     * Wraps the point without specific yaw and pitch (set to 0)
     *
     * @param world the world which the point resides in
     * @param x     the x coordinate
     * @param y     the y coordinate
     * @param z     the z coordinate
     */
    public Location(World world, double x, double y, double z) {
        this(world, x, y, z, 0.0F, 0.0F);
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
    public World getWorld() {
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
    public float getYaw() {
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
    public float getPitch() {
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

    public Location add(Vector vector) {
        setX(vector.getX());
        setY(vector.getY());
        setZ(vector.getZ());

        return this;
    }

    public Location getRelative(Vector vector) {
        return new Location(getWorld(), vector.getX() + getX(), vector.getY() + getY(),
                vector.getZ() + getZ(), getYaw(), getPitch());
    }

    /**
     *  Returns the location as a vector
     */
    public Vector toVector() {
        return new Vector(getX(), getY(), getZ());
    }

    /**
     *  Returns the distance this from location to another.
     *  Math.sqrt is costly, ergo calling this method a lot is not
     *  advised.
     *
     *  @param location the location to measure distance with
     */
    public double distance(Location location) {
        return Math.sqrt(distanceSquared(location));
    }

    /**
     *  Returns the distance squared from this location to another
     *
     *  @param location the location to measure distance with
     */
    public double distanceSquared(Location location) {
        Validate.notNull(location, "Location cannot be null.");
        if(getWorld() != location.getWorld()) return 0;
        return square(getX() - location.getX()) + square(getY() - location.getY()) + square(getZ() - location.getZ());
    }

    private double square(double d) {
        return d * d;
    }
}
