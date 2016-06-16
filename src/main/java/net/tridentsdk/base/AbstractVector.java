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
import net.tridentsdk.doc.Policy;

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
    protected volatile double x;
    @GuardedBy("lock")
    protected volatile double y;
    @GuardedBy("lock")
    protected volatile double z;

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
     * This class must be designed extremely carefully, it
     * is a high-contention class that is core to entity
     * movement. That means lots and lots and lots of
     * updates, so it pays to measure and test in order to
     * reduce contention on this class as much as possible.
     *
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
     *
     * Nevertheless, caution must be taken on a compound
     * write because they must require compound reads as
     * well in order to load the current value, load the
     * values to add, add them together, and then push to
     * the current vector. If a concurrent compound write
     * occurs on the vector to be added, then the added
     * value may potentially be stale because the current
     * vector now knows that one or two of the values could
     * be out of date due to the separate reads. This
     * scenario is illustrated by the diagram:
     *
     * ADD (0, 0, 0) to (1, 1, 1)
     * T1 -> [GETX 0 --------------> GETY 1 GETZ 1]
     * T2 ---------> [SETX 1 -- SETY 1 --- SETZ 1]
     *
     * Because the two are only synchronized in relation
     * to themselves, they are NOT ordered such that T1
     * sees T2's write is atomic. Although when X is
     * observed, it has an old value meaning that the write
     * should not have occured or is waiting, the other two
     * values are new and appear to occur after T2's write.
     * This data conflict is a thread-safety violation, and
     * thus compound operations on two vectors must be
     * synchronized with each other. We must also take time
     * to discuss the fact that the current vector must be
     * synchronized first because although the caller can
     * wait for the current vector to update, the vector
     * that represents the operation cannot wait for the
     * current vector's lock to become available - that is
     * a waste of cycles that could be used to maintain
     * better concurrency.
     *
     * Finally a small note regarding micro-optimization:
     *
     */

    // /-- DO NOT SYNCHRONIZE --\
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
     * Obtains the {@code int} representation of this
     * vector's x value.
     *
     * @return the x value
     */
    public int intX() {
        return (int) this.x;
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
     * Obtains the {@code int} representation of this
     * vector's y value.
     *
     * @return the y value
     */
    public int intY() {
        return (int) this.y;
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
     * Obtains the {@code int} representation of this
     * vector's z value.
     *
     * @return the z value
     */
    public int intZ() {
        return (int) this.z;
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
    // \-- DO NOT SYNCHRONIZE --/

    /**
     * Adds the values defined in the given vector to the
     * values contained in this vector.
     *
     * <p>Please avoid using this method. It was designed to
     * be completely correct and thus requires the use of
     * two acquires of the monitor of both this and the
     * vector on which to operate. Often more corrent and
     * better performing idioms may be used (for example if
     * you'd like to add a single constant) instead of this
     * one is recommended.</p>
     *
     * <p>The monitor of the current vector must always be
     * acquired first in compound read and writes.</p>
     *
     * @param vector the vector values to add
     */
    public void add(T vector) {
        synchronized (this.lock) {
            synchronized (vector.lock) {
                this.add(vector.x, vector.y, vector.z);
            }
        }
    }

    /**
     * Adds the values given by the parameters to those
     * associated values contained in this vector.
     *
     * @param x the x to add
     * @param y the y to add
     * @param z the z to add
     */
    public void add(int x, int y, int z) {
        synchronized (this.lock) {
            this.addImpl((double) x, (double) y, (double) z);
        }
    }

    /**
     * Adds the values given by the parameters to those
     * associated values contained in this vector.
     *
     * @param x the x to add
     * @param y the y to add
     * @param z the z to add
     */
    public void add(double x, double y, double z) {
        synchronized (this.lock) {
            this.addImpl(x, y, z);
        }
    }

    /** JIT Compiler inlining hint */
    // The most useful part of this method is avoiding the
    // invokevirtual opcode which produces an albiet minor
    // speedup in methods that delegate down the chain
    // the same model is used with all other compound adds
    // for vector
    //
    // This method is only 31 byte in opcodes and 32 bytes
    // as detected by the JVM (this is why you must do real
    // world tests!) so it is inlined on the second pass
    // by the JIT compiler on my machine. The default
    // MaxInlineSize is 35 bytes, therefore this method fits
    // it quite well, actually.
    @Internal @Policy("GuardedBy this.lock")
    private void addImpl(double x, double y, double z) {
        this.x += x;
        this.y += y;
        this.z += z;
    }

    /**
     * Subtracts the values defined in the given vector to the
     * values contained in this vector.
     *
     * <p>Please avoid using this method. It was designed to
     * be completely correct and thus requires the use of
     * two acquires of the monitor of both this and the
     * vector on which to operate. Often more corrent and
     * better performing idioms may be used (for example if
     * you'd like to add a single constant) instead of this
     * one is recommended.</p>
     *
     * <p>The monitor of the current vector must always be
     * acquired first in compound read and writes.</p>
     *
     * @param vector the vector values to subtract
     */
    public void subtract(T vector) {
        synchronized (this.lock) {
            synchronized (vector.lock) {
                this.subImpl(vector.x, vector.y, vector.z);
            }
        }
    }

    /**
     * Subtracts the values given by the parameters to those
     * associated values contained in this vector.
     *
     * @param x the x to subtract
     * @param y the y to subtract
     * @param z the z to subtract
     */
    public void subtract(int x, int y, int z) {
        synchronized (this.lock) {
            this.subImpl((double) x, (double) y, (double) z);
        }
    }

    /**
     * Subtracts the values given by the parameters to those
     * associated values contained in this vector.
     *
     * @param x the x to subtract
     * @param y the y to subtract
     * @param z the z to subtract
     */
    public void subtract(double x, double y, double z) {
        synchronized (this.lock) {
            this.subImpl(x, y, z);
        }
    }

    @Internal @Policy("GuardedBy this.lock")
    private void subImpl(double x, double y, double z) {
        this.x -= x;
        this.y -= y;
        this.z -= z;
    }

    /**
     * Multiplies the values defined in the given vector to
     * the values contained in this vector.
     *
     * <p>Please avoid using this method. It was designed to
     * be completely correct and thus requires the use of
     * two acquires of the monitor of both this and the
     * vector on which to operate. Often more corrent and
     * better performing idioms may be used (for example if
     * you'd like to add a single constant) instead of this
     * one is recommended.</p>
     *
     * <p>The monitor of the current vector must always be
     * acquired first in compound read and writes.</p>
     *
     * @param vector the vector values to multiply
     */
    public void multiply(T vector) {
        synchronized (this.lock) {
            synchronized (vector.lock) {
                this.mulImpl(vector.x, vector.y, vector.z);
            }
        }
    }

    /**
     * Multiplies the values given by the parameters to those
     * associated values contained in this vector.
     *
     * @param x the x to multiply
     * @param y the y to multiply
     * @param z the z to multiply
     */
    public void multiply(int x, int y, int z) {
        synchronized (this.lock) {
            this.mulImpl((double) x, (double) y, (double) z);
        }
    }

    /**
     * Multiplies the values given by the parameters to those
     * associated values contained in this vector.
     *
     * @param x the x to multiply
     * @param y the y to multiply
     * @param z the z to multiply
     */
    public void multiply(double x, double y, double z) {
        synchronized (this.lock) {
            this.mulImpl(x, y, z);
        }
    }

    @Internal @Policy("GuardedBy this.lock")
    private void mulImpl(double x, double y, double z) {
        this.x *= x;
        this.y *= y;
        this.z *= z;
    }

    /**
     * Dividess the values defined in the given vector to
     * the values contained in this vector.
     *
     * <p>Please avoid using this method. It was designed to
     * be completely correct and thus requires the use of
     * two acquires of the monitor of both this and the
     * vector on which to operate. Often more corrent and
     * better performing idioms may be used (for example if
     * you'd like to add a single constant) instead of this
     * one is recommended.</p>
     *
     * <p>The monitor of the current vector must always be
     * acquired first in compound read and writes.</p>
     *
     * @param vector the vector values to divide
     */
    public void divide(T vector) {
        synchronized (this.lock) {
            synchronized (vector.lock) {
                this.divImpl(vector.x, vector.y, vector.z);
            }
        }
    }

    /**
     * Divides the values given by the parameters to those
     * associated values contained in this vector.
     *
     * @param x the x to divide
     * @param y the y to divide
     * @param z the z to divide
     */
    public void divide(int x, int y, int z) {
        synchronized (this.lock) {
            this.divImpl((double) x, (double) y, (double) z);
        }
    }

    /**
     * Divides the values given by the parameters to those
     * associated values contained in this vector.
     *
     * @param x the x to divide
     * @param y the y to divide
     * @param z the z to divide
     */
    public void divide(double x, double y, double z) {
        synchronized (this.lock) {
            this.divImpl(x, y, z);
        }
    }

    @Internal @Policy("GuardedBy this.lock")
    private void divImpl(double x, double y, double z) {
        this.x /= x;
        this.y /= y;
        this.z /= z;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AbstractVector) {
            AbstractVector v = (AbstractVector) obj;
            return this.x == v.x && this.y == v.y && this.z == v.z;
        }

        return false;
    }

    @Override
    public int hashCode() {
        // Ignore IntelliJ warning for final field not in hashcode
        // anyone who uses a vector or position object as a
        // key is probably mentally retarded
        int hash = 1;
        hash = 31 * hash + Long.hashCode(Double.doubleToLongBits(this.x));
        hash = 31 * hash + Long.hashCode(Double.doubleToLongBits(this.y));
        hash = 31 * hash + Long.hashCode(Double.doubleToLongBits(this.z));
        return hash;
    }

    @Override
    public String toString() {
        return "Vector{" + this.x + ',' + this.y + "," + this.z + "}";
    }
}