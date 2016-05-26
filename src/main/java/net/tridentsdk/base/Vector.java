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

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

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
public class Vector implements Serializable {
    private static final long serialVersionUID = 218773668333902972L;

    /** The Vector states holding arbitrary values */
    @GuardedBy("super.lock")
    private double x;
    @GuardedBy("super.lock")
    private double y;
    @GuardedBy("super.lock")
    private double z;

    /** The lock used to protect compound read/writes. */
    protected final Object lock = new Object();

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
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /*
     * WARNING: This section should be written with extreme
     * care
     *
     * "Sequential consistency is a very strong guarantee
     * that is made about visibility and ordering in an
     * execution of a program. Within a sequentially
     * consistent execution, there is a total order over all
     * individual actions (such as reads and writes) which
     * is consistent with the order of the program, and each
     * individual action is atomic and is immediately
     * visible to every thread.
     *
     * If a program has no data races, then all executions
     * of the program will appear to be sequentially
     * consistent." (Gosling et al. 17.4.3)
     *
     * Gosling, James, Bill Joy, Guy Steele, Gilad Bracha,
     * and Alex Buckley. "Chapter 17. Threads and Locks."
     * Chapter 17. Threads and Locks. Oracle, 13 Feb. 2015.
     * Web. 26 May 2016.
     * <http://docs.oracle.com/javase/specs/jls/se8/html/jls-17.html#jls-17.4.3>.
     *
     * Individual reads done relative to mutations of the
     * same state value are thus guaranteed to be atomic.
     * This only works because a single read will occur
     * before or after a write of the read state, thus it
     * does not matter whether this observation is
     * interleaved with a compound write.
     */

    /**
     * Obtains the {@code double} representation of this
     * vector's x value.
     *
     * @return the x value
     */
    public double x() {
        return this.x;
    }

    /**
     * Obtains the {@code double} representation of this
     * vector's y value.
     *
     * @return the y value
     */
    public double y() {
        return this.y;
    }

    /**
     * Obtains the {@code double} representation of this
     * vector's z value.
     *
     * @return the z value
     */
    public double z() {
        return this.z;
    }


    /**
     * Adds the values defined in the given vector to the
     * values contained in this vector.
     *
     * @param vector the vector to add
     */
    public void add(Vector vector) {
        synchronized (lock) {
            this.x = this.x + vector.x;
            this.y = this.y + vector.y;
            this.z = this.z + vector.z;
        }
    }

    /**
     * Subtracts the values defined in the given vector to the
     * values contained in this vector.
     *
     * @param vector the vector to subtract
     */
    public void subtract(Vector vector) {
        synchronized (lock) {
            this.x = this.x - vector.x;
            this.y = this.y - vector.y;
            this.z = this.z - vector.z;
        }
    }

    /**
     * Multiplies the values defined in the given vector to
     * the values contained in this vector.
     *
     * @param vector the vector to multiply
     */
    public void multiply(Vector vector) {
        synchronized (lock) {
            this.x = this.x * vector.x;
            this.y = this.y * vector.y;
            this.z = this.z * vector.z;
        }
    }

    /**
     * Dividess the values defined in the given vector to
     * the values contained in this vector.
     *
     * @param vector the vector to divide
     */
    public void divide(Vector vector) {
        synchronized (lock) {
            this.x = this.x / vector.x;
            this.y = this.y / vector.y;
            this.z = this.z / vector.z;
        }
    }
}
