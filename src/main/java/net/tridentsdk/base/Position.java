/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2017 The TridentSDK Team
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

import lombok.Getter;
import net.tridentsdk.world.World;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

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
@Immutable
public final class Position extends AbstractVector<Position> {
    private static final long serialVersionUID = 5910507790866074403L;

    /**
     * Additional fields representing the state of the
     * position
     */
    @Getter
    private final World world;
    @Getter
    private final float yaw;
    @Getter
    private final float pitch;

    /**
     * Creates a Position in a given world with all
     * coordinates and directions set to 0.
     *
     * @param world the world that this position is set to
     * reside in
     */
    public Position(@Nonnull World world) {
        this(world, 0D, 0D, 0D, 0F, 0F);
    }

    /**
     * Creates a Position in a given world and integer
     * Cartesian coordinates with all directions set to 0.
     *
     * @param world the world that this position is set to
     * reside in
     * @param x the x coordinate
     * @param y the y coordinate
     * @param z the z coordinate
     */
    public Position(@Nonnull World world, int x, int y, int z) {
        this(world, (double) x, (double) y, (double) z, 0F, 0F);
    }

    /**
     * Creates a Position in a given world and {@code
     * double}
     * Cartesian coordinates with all directions set to 0.
     *
     * @param world the world that this position is set to
     * reside in
     * @param x the x coordinate
     * @param y the y coordinate
     * @param z the z coordinate
     */
    public Position(@Nonnull World world, double x, double y, double z) {
        this(world, x, y, z, 0F, 0F);
    }

    /**
     * Creates a Position in a given world, {@code double}
     * Cartesian coordinates, and directional values.
     *
     * @param world the world in which this position is set
     * to reside
     * @param x the x coordinate
     * @param y the y coordinate
     * @param z the z coordinate
     * @param yaw the yaw directional
     * @param pitch the pitch directional
     */
    public Position(@Nonnull World world, double x, double y, double z, float yaw, float pitch) {
        super(x, y, z);
        this.world = world;
        this.yaw = yaw > 360 || yaw < -360 ? yaw % 360 : yaw;
        this.pitch = pitch > 90 || pitch < -90 ? pitch % 90 : pitch;
    }

    /**
     * Creates a new {@code Position} object containing all
     * of the same data points as this position, except that
     * its world is set to the one given.
     *
     * @param world the world from which to create a position
     * @return the new position with the given world
     */
    public Position setWorld(World world) {
        return new Position(world, this.x, this.y, this.z, this.yaw, this.pitch);
    }

    /**
     * Creates a new {@code Position} object containing all
     * of the same data points as this position, except that
     * its yaw is set to the one given.
     *
     * @param yaw the yaw from which to create a position
     * @return the new position with the given yaw
     */
    public Position setYaw(float yaw) {
        return new Position(this.world, this.x, this.y, this.z, yaw, this.pitch);
    }

    /**
     * Creates a new {@code Position} object containing all
     * of the same data points as this position, except that
     * its pitch is set to the one given.
     *
     * @param pitch the pitch from which to create a position
     * @return the new position with the given pitch
     */
    public Position setPitch(float pitch) {
        return new Position(this.world, this.x, this.y, this.z, this.yaw, pitch);
    }


    /**
     * Obtains the block that is located at this position.
     *
     * <p>If you do not already have a {@link Position}
     * object, then use
     * {@link World#getBlockAt(int, int, int)} instead, as
     * it is not necessary to create a throwaway object.</p>
     *
     * @return the block
     */
    public Block getBlock() {
        return this.world.getBlockAt(this);
    }

    /**
     * Obtains the square of the distance between this
     * position and the given position.
     *
     * <p>Despite having to use heavy synchronization due
     * to compound reads on both positions, this method is
     * preferable to use, if possible, over
     * {@link #distance(Position)}.</p>
     *
     * @param position the position from which to find the
     * square of the distance
     * @return the distance squared
     */
    public double distanceSquared(Position position) {
        double dX = this.x - position.x;
        double dY = this.y - position.y;
        double dZ = this.z - position.z;

        return square(dX) + square(dY) + square(dZ);
    }

    /**
     * Obtains the distance between this position and the
     * given position.
     *
     * <p>If possible, use
     * {@link #distanceSquared(Position)} in performance
     * sensitive applications due to the overhead of using
     * {@link Math#sqrt(double)}.</p>
     *
     * @param position the position from which to find the
     * distance
     * @return the distance
     */
    public double distance(Position position) {
        return Math.sqrt(this.distanceSquared(position));
    }

    /**
     * This is the most correct way I've found to compare
     * {@code floats}.
     *
     * @param f0 the first float
     * @param f1 the second float
     * @return {@code true} if they are equal
     */
    // Private static - compiler inline hint
    private static boolean eq(float f0, float f1) {
        return Float.compare(f0, f1) == 0;
    }

    /**
     * Obtains the X coordinate of the chunk in which this
     * position is located.
     *
     * @return the chunk x
     */
    public int getChunkX() {
        return this.getIntX() >> 4;
    }

    /**
     * Obtains the Z coordinate of the chunk in which this
     * position is located.
     *
     * @return the chunk z
     */
    public int getChunkZ() {
        return this.getIntZ() >> 4;
    }

    @Override
    public Position setX(double x) {
        return new Position(this.world, x, this.y, this.z, this.yaw, this.pitch);
    }

    @Override
    public Position setY(double y) {
        return new Position(this.world, this.x, y, this.z, this.yaw, this.pitch);
    }

    @Override
    public Position setZ(double z) {
        return new Position(this.world, this.x, this.y, z, this.yaw, this.pitch);
    }

    @Override
    public Position set(double x, double y, double z) {
        return new Position(this.world, x, y, z, this.yaw, this.pitch);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Position) {
            Position v = (Position) obj;
            return eq(this.x, v.x) && eq(this.y, v.y) && eq(this.z, v.z) &&
                    this.world.equals(v.world) &&
                    eq(this.pitch, v.pitch) && eq(this.yaw, v.yaw);
        }

        return false;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 31 * hash + this.world.hashCode();
        hash = 31 * hash + Float.floatToIntBits(this.pitch);
        hash = 31 * hash + Float.floatToIntBits(this.yaw);
        return hash;
    }

    @Override
    public String toString() {
        return String.format("Position{%s, (%f, %f, %f), pitch=%f, yaw=%f}",
                this.world.getName(), this.x, this.y, this.z, this.pitch, this.yaw);
    }
}