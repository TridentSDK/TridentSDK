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
}
