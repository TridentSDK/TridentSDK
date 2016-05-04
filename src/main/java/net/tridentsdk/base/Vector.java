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

import javax.annotation.concurrent.ThreadSafe;
import java.io.Serializable;

/**
 * A vector is a container of 3 number values that can be
 * arbitrarily used or as a directional construct
 *
 * <p>The three arbitrary values are represented internally
 * as doubles.</p>
 *
 * <p>Consideration for this class to be immutable was
 * dismissed as being significantly too costly. However,
 * methods that perform atomic operations will
 * <strong>ALWAYS</strong> return a new instance in order
 * to preserve thread safety. These methods are documented
 * below.</p>
 *
 * @author TridentSDK
 * @since 0.3-alpha-DP
 */
@ThreadSafe
public class Vector implements Serializable {
    private static final long serialVersionUID = 218773668333902972L;

    private volatile double x;
    private volatile double y;
    private volatile double z;

    /**
     * Creates a new Vector object with all 3 values set to
     * {@code 0}.
     */
    public Vector() {
        this(0D, 0D, 0D);
    }

    /**
     * Creates a new Vector object using {@code int}egers
     *
     * @param x the x value
     * @param y the y value
     * @param z the z value
     */
    public Vector(int x, int y, int z) {
        this((double) x, (double) y, (double) z);
    }

    /**
     * Creates a new Vector object using {@code double}s
     *
     * @param x the x value
     * @param y the y value
     * @param z the z value
     */
    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Obtains the {@code double} representation of this
     * vector's x value
     *
     * @return the x value
     */
    public double x() {
        return this.x;
    }

    /**
     * Obtains the {@code double} representation of this
     * vector's y value
     *
     * @return the y value
     */
    public double y() {
        return this.y;
    }

    /**
     * Obtains the {@code double} representation of this
     * vector's z value
     *
     * @return the z value
     */
    public double z() {
        return this.z;
    }
}
