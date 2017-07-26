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
package net.tridentsdk.world.opt;

import lombok.Data;

import javax.annotation.concurrent.ThreadSafe;

/**
 * This class represents the world border.
 *
 * @author TridentSDK
 * @since 0.4-alpha
 */
@ThreadSafe
public interface WorldBorder {
    /**
     * The default center of a world border
     */
    DoubleXZ DEFAULT_CENTER = new DoubleXZ(0, 0);
    /**
     * The default width between two sides of a world border
     */
    double DEFAULT_SIZE = 60_000_000;
    /**
     * The default distance for the safe zone and warning
     * zone
     */
    int DEFAULT_SAFE_AND_WARN_DIST = 5;
    /**
     * The default seconds when a border will reach a player
     * before they are warned
     */
    int DEFAULT_WARN_TIME = 15;
    /**
     * The default damage per second dealt to a player
     * outside the safe zone
     */
    double DEFAULT_DAMAGE = 0.2;

    /**
     * Represents a pair of doubles that signify the X and
     * Z coordinates of a world border's center.
     *
     * @author TridentSDK
     * @since 0.5-alpha
     */
    @Data
    class DoubleXZ {
        private final double x;
        private final double z;
    }

    /**
     * Initializes the world border, bringing it into view
     * for players on this world.
     */
    void init();

    /**
     * Obtains the center of the world border.
     *
     * <p>By default, returns an IntPair with the
     * coordinates (0, 0).</p>
     *
     * @return the center of the border
     */
    DoubleXZ getCenter();

    /**
     * Sets the center of the world border.
     *
     * <p>Use {@link #DEFAULT_CENTER} to use the default
     * border size.</p>
     *
     * @param center the new border center
     */
    void setCenter(DoubleXZ center);

    /**
     * Obtains the <em>current</em> size of the world
     * border.
     *
     * <p>The world border is always a square, the size is
     * the width and length across the border.</p>
     *
     * @return the border size
     */
    double getSize();

    /**
     * Obtains the size that is expected to be of the world
     * border once {@link #getTargetTime()} seconds have
     * elapsed unless another call to either
     * {@link #setSize(double, long)} or
     * {@link #grow(double, long)} occurs during the time
     * elapsed, in which case this method will return target
     * size set by the newest invocation.
     * <p>The value returned by this method may match that
     * given by {@link #getSize()} if the world border has
     * grown to the target size already.</p>
     *
     * @return the size that this border is set to grow
     */
    double getTargetSize();

    /**
     * Obtains the remaining time that it will take for
     * {@link #getSize()} to reach {@link #getTargetSize()}
     * in seconds, if the border size is not already at the
     * target size.
     *
     * @return the time the border will take to shrink or
     * grow in MILLISECONDS
     */
    long getTargetTime();

    /**
     * Sets the size of the world border.
     *
     * <p>One may use {@link #DEFAULT_SIZE} to reset the
     * border to its default size.</p>
     *  @param size the new size to set the world border to
     * @param time the time in MILLIS that the border to
     *             grow or shrink to the new size, or
     *             {@code 0} to take immediate effect
     */
    void setSize(double size, long time);

    /**
     * Grows the world border, or shrinks it given a
     * negative {@code delta}.
     *  @param delta the size to grow or shrink, if it is
     *              negative
     * @param time the time in MILLIS that the border to
     *             grow or shrink to the new size, or
     *             {@code 0} to take immediate effect
     */
    void grow(double delta, long time);

    /**
     * Obtains the amount of damage being outside the world
     * border will be dealt to a player each second.
     *
     * @return the damage per second, where 0.5 is half a
     *         heart
     */
    double getDamage();

    /**
     * Sets the damage dealt per second to players outside
     * the safe zone behind the world border.
     *
     * <p>The damage can be set to {@link #DEFAULT_DAMAGE}
     * in order to reset the damage dealt per second.</p>
     *
     * @param damage the damage, where 0.5 is half a heart
     * @see #getDamage()
     */
    void setDamage(double damage);

    /**
     * Obtains the N amount of blocks away from the world
     * border where players will not be dealt damage until
     * they reach N+1 blocks away from the safe zone.
     *
     * @return the amount of blocks players are safe before
     *         being dealt damage
     */
    double getSafeZoneDistance();

    /**
     * Sets the amount of blocks on the other side of the
     * world border which the player must be in order to be
     * dealt damage.
     *
     * <p>One may set this to
     * {@link #DEFAULT_SAFE_AND_WARN_DIST} in order to reset
     * the size of this safe zone.</p>
     *
     * @param size the amount of blocks away from the border
     */
    void setSafeZoneDistance(int size);

    /**
     * Obtains the distance away from the other side of the
     * world border where players will be warned by the
     * screen being tinted red.
     *
     * @return the blocks away from the border that players
     *         will be warned
     */
    int getWarnDistance();

    /**
     * Sets the distance away from the border which players
     * will be warned.
     *
     * <p>One may set this to
     * {@link #DEFAULT_SAFE_AND_WARN_DIST} in order to reset
     * the warning distance.</p>
     *
     * @param dist the blocks away from the border which to
     *             warn players
     */
    void setWarnDistance(int dist);

    /**
     * Adds the given distance (negative numbers valid) to
     * the distance away from the border which the player
     * must be in order to be dealt damage.
     *
     * @param dist the distance to grow or shrink
     */
    void growWarnDistance(int dist);

    /**
     * Obtains the time in which the player will be warned
     * before a shrinking world border will reach them.
     *
     * @return the time in seconds before the border will
     *         reach the player until they are warned
     */
    int getWarnTime();

    /**
     * Sets the number of seconds before a shrinking world
     * border will reach a player in which they will be
     * warned.
     *
     * @param seconds the number of seconds until the border
     * will reach the player before warning the player
     */
    void setWarnTime(int seconds);
}