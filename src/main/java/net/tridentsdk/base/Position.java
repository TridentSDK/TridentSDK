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

import net.tridentsdk.world.World;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

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
public final class Position extends AbstractVector<Position> implements Cloneable {
    private static final long serialVersionUID = 5910507790866074403L;

    /**
     * Additional fields representing the state of the
     * position
     */
    private final World world;
    private float yaw;
    private float pitch;

    @Override
    protected void writeFields(Position vector) {
        this.pitch = vector.pitch;
        this.yaw = vector.yaw;
    }

    /**
     * Creates a Position in a given world with all
     * coordinates and directions set to 0.
     *
     * <p>While it is recommended for the given world to be
     * nonnull, certain situations such as those where the
     * world will be found after its coordinates, the
     * {@link #write(AbstractVector)} or
     * {@link #add(double, double, double)} methods
     * can be used in addition to this</p>
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
     * <p>While it is recommended for the given world to be
     * nonnull, certain situations such as those where the
     * world will be found after its coordinates, the
     * {@link #write(AbstractVector)} or
     * {@link #add(double, double, double)} methods
     * can be used in addition to this</p>
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
     * <p>While it is recommended for the given world to be
     * nonnull, certain situations such as those where the
     * world will be found after its coordinates, the
     * {@link #write(AbstractVector)} or
     * {@link #add(double, double, double)} methods
     * can be used in addition to this</p>
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
     * <p>While it is recommended for the given world to be
     * nonnull, certain situations such as those where the
     * world will be found after its coordinates, the
     * {@link #write(AbstractVector)} or
     * {@link #add(double, double, double)} methods
     * can be used in addition to this</p>
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
        this.yaw = yaw;
        this.pitch = pitch;
    }

    /**
     * Obtains the World in which this Position is set to
     * reside.
     *
     * @return the world containing this Position
     */
    public World world() {
        return this.world;
    }

    /**
     * Sets the yaw of this position.
     *
     * @param yaw the yaw to set
     */
    public void setYaw(float yaw) {
        synchronized (this.lock) {
            this.yaw = yaw;
        }
    }

    /**
     * Obtains the yaw at which this position is pointed.
     *
     * <p>Yaw = horizontal rotation</p>
     *
     * @return this position's yaw
     */
    public float getYaw() {
        synchronized (this.lock) {
            return this.yaw;
        }
    }

    /**
     * Sets the pitch of this position.
     *
     * @param pitch the pitch to set
     */
    public void setPitch(float pitch) {
        synchronized (this.lock) {
            this.pitch = pitch;
        }
    }

    /**
     * Obtains the pitch at which this position is pointed.
     *
     * <p>Pitch = vertical rotation</p>
     *
     * @return this position's pitch
     */
    public float getPitch() {
        synchronized (this.lock) {
            return this.pitch;
        }
    }

    /**
     * Obtains the block that is located at this position.
     *
     * <p>If you do not already have a {@link Position}
     * object, then use {@link World#getBlockAt(int, int, int)} instead, as it is not necessary to create a
     * throwaway object.</p>
     *
     * @return the block
     */
    public Block getBlock() {
        return this.world.getBlockAt(this);
    }

    /**
     * Obtains an immutable copy of this vector.
     *
     * @return the copy of the current state of this vector
     */
    public ImmutableWorldVector toWorldVector() {
        synchronized (this.lock) {
            return new ImmutableWorldVector(this.world, this.getIntX(), this.getIntY(), this.getIntZ());
        }
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
        double dX;
        double dY;
        double dZ;
        synchronized (this.lock) {
            synchronized (position.lock) {
                dX = this.x - position.x;
                dY = this.y - position.y;
                dZ = this.z - position.z;
            }
        }

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
     * @return the chunk xx
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
    public boolean equals(Object obj) {
        if (obj instanceof Position) {
            Position v = (Position) obj;
            synchronized (this.lock) {
                return eq(this.x, v.x) && eq(this.y, v.y) && eq(this.z, v.z) &&
                        this.world.equals(v.world) &&
                        eq(this.pitch, v.pitch) && eq(this.yaw, v.yaw);
            }
        }

        return false;
    }

    @Override
    public int hashCode() {
        synchronized (this.lock) {
            int hash = super.hashCode();
            hash = 31 * hash + this.world.hashCode();
            hash = 31 * hash + Float.floatToIntBits(this.pitch);
            hash = 31 * hash + Float.floatToIntBits(this.yaw);
            return hash;
        }
    }

    @Override
    public String toString() {
        synchronized (this.lock) {
            return String.format(
                    "Position{%s-%f,%f,%f-pitch=%f,yaw=%f}",
                    this.world.getName(), this.x, this.y, this.z, this.pitch, this.yaw);
        }
    }

    @Override
    public Position clone() {
        synchronized (this.lock) {
            return new Position(this.world, this.x, this.y, this.z, this.yaw, this.pitch);
        }
    }
}
