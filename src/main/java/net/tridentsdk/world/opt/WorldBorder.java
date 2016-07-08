/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2016 The TridentSDK Team
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

import net.tridentsdk.world.IntPair;

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
    IntPair DEFAULT_CENTER = IntPair.make(0, 0);
    /**
     * The default width between two sides of a world border
     */
    int DEFAULT_SIZE = 60_000_000;
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
     * Obtains the center of the world border.
     *
     * <p>By default, returns an IntPair with the
     * coordinates (0, 0).</p>
     *
     * @return the center of the border
     */
    IntPair center();

    /**
     * Sets the center of the world border.
     *
     * <p>Use {@link #DEFAULT_CENTER} to use the default
     * border size.</p>
     *
     * @param center the new border center
     */
    void setCenter(IntPair center);

    /**
     * Obtains the size of the world border.
     *
     * <p>The world border is always a square, the size is
     * the width and length across the border.</p>
     *
     * @return the border size
     */
    int size();

    /**
     * Sets the size of the world border.
     *
     * <p>One may use {@link #DEFAULT_SIZE} to reset the
     * border to its default size.</p>
     *
     * @param size the new size to set the world border to
     * @param time the time in seconds that the border to
     *             grow or shrink to the new size, or
     *             {@code 0} to take immediate effect
     */
    void setSize(int size, int time);

    /**
     * Grows the world border, or shrinks it given a
     * negative {@code delta}.
     *
     * @param delta the size to grow or shrink, if it is
     *              negative
     * @param time the time in seconds that the border to
     *             grow or shrink to the new size, or
     *             {@code 0} to take immediate effect
     */
    void grow(int delta, int time);

    /**
     * Obtains the amount of damage being outside the world
     * border will be dealt to a player each second.
     *
     * @return the damage per second, where 0.5 is half a
     *         heart
     */
    double damage();

    /**
     * Sets the damage dealt per second to players outside
     * the safe zone behind the world border.
     *
     * <p>The damage can be set to {@link #DEFAULT_DAMAGE}
     * in order to reset the damage dealt per second.</p>
     *
     * @param damage the damage, where 0.5 is half a heart
     * @see #damage()
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
    int safeZoneDist();

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
    void setSafeZoneDist(int size);

    /**
     * Obtains the distance away from the other side of the
     * world border where players will be warned by the
     * screen being tinted red.
     *
     * @return the blocks away from the border that players
     *         will be warned
     */
    int warnDist();

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
    void setWarnDist(int dist);

    /**
     * Obtains the time which a shrinking border will reach
     * the player and thus warn them by tinting the screen
     * red.
     *
     * @return the time in seconds before the border will
     *         reach the player until they are warned
     */
    int warnTime();

    /**
     * Sets the time before the world border will reach the
     * player before they are warned.
     *
     * <p>One may set this to
     * {@link #DEFAULT_WARN_TIME} in order to reset
     * the warning time.</p>
     *
     * @param time the time in seconds
     */
    void setWarnTime(int time);
}