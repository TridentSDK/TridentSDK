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

import net.tridentsdk.doc.Internal;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.io.Serializable;

/**
 * A vector is a container of 3 number values that can be
 * arbitrarily used or as a directional construct.
 *
 * <p>The three arbitrary values are represented internally
 * as doubles.</p>
 *
 * @author TridentSDK
 * @param <T> this must be the subclass type in order to
 *            provide the proper functionality
 * @since 0.3-alpha-DP
 */
@ThreadSafe @Internal("public access for convenience only")
public class AbstractVector<T extends AbstractVector<T>> implements Serializable {
    private static final long serialVersionUID = 218773668333902972L;
    /** The lock used to protect compound read/writes. */
    protected final Object lock = new Object();
    /** The AbstractVector states holding arbitrary values */
    @GuardedBy("lock")
    private volatile double x;
    @GuardedBy("lock")
    private volatile double y;
    @GuardedBy("lock")
    private volatile double z;

    /**
     * Creates a new AbstractVector object with all 3 values set to
     * {@code 0}.
     */
    public AbstractVector() {
        this(0D, 0D, 0D);
    }

    /**
     * Creates a new AbstractVector object using {@code int}egers.
     *
     * @param x the x value
     * @param y the y value
     * @param z the z value
     */
    public AbstractVector(int x, int y, int z) {
        this((double) x, (double) y, (double) z);
    }

    /**
     * Creates a new AbstractVector object using {@code double}s.
     *
     * @param x the x value
     * @param y the y value
     * @param z the z value
     */
    public AbstractVector(double x, double y, double z) {
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
     * before or after a write of the read state. It
     * does not matter whether this observation is
     * interleaved with a compound write.
     *
     * When it is conditionally thread-safe "relative" to
     * when a state is observed or mutated, it means that
     * the thread performing the given action seen by
     * another thread remains atomic because the result of
     * the operation is consistent with what the observer
     * should see. Even though an interleaved write such as
     * the one shown in the diagram
     *
     * T1 -> ADD  [     x          y  z]        x=4
     * T2 -> SETX [  |           |     ]        x=5
     * T3 --------[[1]-[4]]-[[1]--[5]]-------> observe x
     *
     * produces two possible results (indicated by the
     * vertical bars in SETX), we must realize that they are
     * the ONLY two results and either of them BOTH make
     * sense independent of the timing between the two
     * threads.
     *
     * Although this is technically considered to be a "race
     * condition," it does not produce undesirable
     * effects; therefore it is completely legal and
     * conforms to the thread safety policy for this class.
     * The correct scenario where the term "race condition"
     * applies to is the check-then-act idiom because the
     * operation is inherently non-atomic relative to the
     * thread performing this action. Observer threads may
     * see that interleaving writes and checks cause the
     * actual mutation to produce inconsistent results
     * depending on which thread comes between the check and
     * the act.
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
     * Sets the x value of this vector
     *
     * @param x the new value to set x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the x value of this vector
     *
     * @param x the new value to set x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Sets the y value of this vector
     *
     * @param y the new value to set y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Sets the y value of this vector
     *
     * @param y the new value to set y
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Sets the z value of this vector
     *
     * @param z the new value to set z
     */
    public void setZ(int z) {
        this.z = z;
    }

    /**
     * Sets the z value of this vector
     *
     * @param z the new value to set z
     */
    public void setZ(double z) {
        this.z = z;
    }


    /**
     * Adds the values defined in the given vector to the
     * values contained in this vector.
     *
     * @param vector the vector values to add
     */
    public void add(T vector) {
        synchronized (lock) {
            this.x = this.x + vector.x();
            this.y = this.y + vector.y();
            this.z = this.z + vector.z();
        }
    }

    /**
     * Subtracts the values defined in the given vector to the
     * values contained in this vector.
     *
     * @param vector the vector values to subtract
     */
    public void subtract(T vector) {
        synchronized (lock) {
            this.x = this.x - vector.x();
            this.y = this.y - vector.y();
            this.z = this.z - vector.z();
        }
    }

    /**
     * Multiplies the values defined in the given vector to
     * the values contained in this vector.
     *
     * @param vector the vector values to multiply
     */
    public void multiply(T vector) {
        synchronized (lock) {
            this.x = this.x * vector.x();
            this.y = this.y * vector.y();
            this.z = this.z * vector.z();
        }
    }

    /**
     * Dividess the values defined in the given vector to
     * the values contained in this vector.
     *
     * @param vector the vector values to divide
     */
    public void divide(T vector) {
        synchronized (lock) {
            this.x = this.x / vector.x();
            this.y = this.y / vector.y();
            this.z = this.z / vector.z();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AbstractVector) {
            AbstractVector v = (AbstractVector) obj;
            return (x == v.x) && (y == v.y) && (z == v.z);
        }

        return false;
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash = 31 * hash + Long.hashCode(Double.doubleToLongBits(x));
        hash = 31 * hash + Long.hashCode(Double.doubleToLongBits(y));
        hash = 31 * hash + Long.hashCode(Double.doubleToLongBits(z));
        return hash;
    }

    @Override
    public String toString() {
        return "Vector{" + x + "," + y + "," + z + "}";
    }
}
