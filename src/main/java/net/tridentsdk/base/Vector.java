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
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

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

    /** Default static implementation of the lock switcher */
    private static final Lock LOCK_IMPL = new FullLockImpl();
    private static final Lock NULL_IMPL = new NullLockImpl();

    /** The Vector states holding arbitrary values */
    private volatile double x;
    private volatile double y;
    private volatile double z;

    /** The lock switcher */
    private volatile Lock lock = NULL_IMPL;
    /** An arbitrary number used to show compound writes */
    protected final AtomicLong writeStamp = new AtomicLong();

    /*
     * HOW THIS CLASS IMPLEMENTS OPTIMISTIC READING
     *
     *
     */

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

    /**
     * Atomic read function allows for a client-provided
     * function to be injected with the current state of
     * this Vector in order to process the snapshot.
     *
     * <p>This method first uses an optimistic read, which
     * means it does not perform a full lock and assumes
     * that the entire operation will be uninterrupted by
     * updates to the state of the Vector. Single updates
     * to one of the states contained in the current
     * instance will not be factored into the snapshot.</p>
     *
     * <p>This method performs a full lock if the internal
     * state was already modified by the time the state has
     * been completely read.</p>
     *
     * @param func the function to process the snapshot
     * @return the return value of the function
     */
    protected final Vector atomicRead(TriFunction func) {
        long stamp = writeStamp.get();
        double x = this.x;
        double y = this.y;
        double z = this.z;
        long check = writeStamp.get();

        if (stamp != check) {
            lock = LOCK_IMPL;

            lock.lock();
            try {
                return func.accept(this.x, this.y, this.z);
            } finally {
                lock.unlock();
                lock = NULL_IMPL;
            }
        }

        return func.accept(x, y, z);
    }

    /**
     * This class is provided as a convenience processor
     * for functions that require an atomic read of two or
     * more state fields of Vector.
     *
     * @author TridentSDK
     * @since 0.4-alpha
     */
    @FunctionalInterface
    protected interface TriFunction {
        /**
         * This method accepts the current three values read
         * from the state of the current Vector instance
         * holding this class.
         *
         * @param x the x value of the vector
         * @param y the x value of the vector
         * @param z the x value of the vector
         * @return a new desired vector, if needed
         */
        Vector accept(double x, double y, double z);
    }

    /*******************************************************
     * LOCKING MECHANISMS
     * Hand-rolled implementations that only include what is
     * needed and nothing else.
     ******************************************************/

    /** Lock interface */
    private interface Lock {
        void lock();   // Acquires a monitor
        void unlock(); // Releases the monitor
    }

    /** No-op lock; assigned when the read function finishes
     * reading the state of the fields */
    private static class NullLockImpl implements Lock {
        @Override
        public void lock() {}

        @Override
        public void unlock() {}
    }

    /** Implementation of the internal lock that uses
     * {@link ReentrantLock} to provide the correct
     * synchronising mechanisms*/
    private static class FullLockImpl
            extends ReentrantLock
            implements Lock {
        private static final long serialVersionUID = 4779776260718452375L;
    }
}
