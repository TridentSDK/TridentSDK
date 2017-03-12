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

import javax.annotation.concurrent.ThreadSafe;

/**
 * A vector is a container of 3 number values that can be
 * arbitrarily used or as a directional construct.
 *
 * <p>The three arbitrary values are represented internally
 * as doubles.</p>
 *
 * @author TridentSDK
 * @since 0.3-alpha-DP
 */
@ThreadSafe
public final class Vector extends AbstractVector<Vector> {
    private static final long serialVersionUID = 9128045730182148574L;

    /**
     * Creates a new Vector object with all 3 values set to
     * {@code 0}.
     */
    public Vector() {
        this(0D, 0D, 0D);
    }

    /**
     * Creates a new Vector object using {@code int}egers.
     *
     * @param x the x value
     * @param y the y value
     * @param z the z value
     */
    public Vector(int x, int y, int z) {
        this((double) x, (double) y, (double) z);
    }

    /**
     * Creates a new Vector object using {@code double}s.
     *
     * @param x the x value
     * @param y the y value
     * @param z the z value
     */
    public Vector(double x, double y, double z) {
        super(x, y, z);
    }

    /**
     * Obtains the square of the magnitude of this Vector.
     *
     * <p>This method should always be considered, if
     * possible, over {@link #getMagnitude()}.</p>
     *
     * @return the magnitude squared
     */
    public double getMagnitudeSquared() {
        double x;
        double y;
        double z;
        synchronized (this.lock) {
            x = this.x;
            y = this.y;
            z = this.z;
        }

        return square(x) + square(y) + square(z);
    }

    /**
     * Obtains the magnitude of this Vector.
     *
     * <p>The method {@link #getMagnitudeSquared()} should
     * always be considered over this method because usage
     * of the square root function is very performance
     * intensive.</p>
     *
     * @return the magnitude
     */
    public double getMagnitude() {
        return Math.sqrt(this.getMagnitudeSquared());
    }

    /**
     * Obtains this vector converted to a unit vector, with
     * a magnitude of {@code 1} but retaining the same
     * direction.
     *
     * <p>The usage of {@link #getMagnitude()} in this
     * operation means that it should be avoided if
     * possible
     * due to performance concerns.</p>
     *
     * @return the normalized vector
     */
    public Vector normalize() {
        double mag = this.getMagnitude();
        return this.divide(mag, mag, mag);
    }
}
