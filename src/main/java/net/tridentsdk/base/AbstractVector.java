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

import net.tridentsdk.doc.Internal;
import net.tridentsdk.world.World;

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
 * <p>Subclasses need to implement their own hashcode,
 * equals, and toString methods.</p>
 *
 * @param <T> this must be the subclass type in order to
 *            provide the proper functionality
 * @author TridentSDK
 * @since 0.5-alpha
 */
@ThreadSafe
@Internal("public access for convenience only")
public class AbstractVector<T extends AbstractVector<T>> implements Serializable {
    private static final long serialVersionUID = 218773668333902972L;

    /**
     * Hook method used to implement equals for comparing
     * two {@code doubles}.
     *
     * <p>This is the most correct way at the current
     * moment.</p>
     *
     * @param d0 the first double
     * @param d1 the second double
     * @return {@code true} if the two {@code doubles} are
     * equal to each other
     */
    // Set to static - compiler inline hint
    protected static boolean eq(double d0, double d1) {
        return Double.compare(d0, d1) == 0;
    }

    /**
     * Squares the two {@code double}s.
     *
     * <p>This is provided as an inliner shortcut to using
     * {@link Math#pow(double, double)} which is not a
     * necessary JNI call for such a simple method as
     * squaring.</p>
     *
     * @param d the number to square
     * @return the result of squaring the double
     */
    // Set to static - compiler inline hint
    protected static double square(double d) {
        return d * d;
    }

    /**
     * The AbstractVector states holding arbitrary values
     */
    @GuardedBy("lock")
    protected double x;
    @GuardedBy("lock")
    protected double y;
    @GuardedBy("lock")
    protected double z;

    /**
     * The lock used to protect compound read/writes.
     */
    protected final Object lock = new Object();

    /**
     * Hook method used to append additional fields in
     * subclasses for the writeTo method.
     *
     * @param vector the vector to which this vector will
     *               write its fields
     */
    @GuardedBy("lock")
    protected void writeFields(T vector) {
    }

    /**
     * Creates a new AbstractVector object with all 3 values set to
     * {@code 0}.
     */
    public AbstractVector() {
        this(0D, 0D, 0D);
    }

    /**
     * Creates a new AbstractVector object using {@code int}s.
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

    /**
     * Obtains the {@code double} representation of this
     * vector's x value.
     *
     * @return the x value
     */
    public double getX() {
        synchronized (this.lock) {
            return this.x;
        }
    }

    /**
     * Obtains the {@code int} representation of this
     * vector's x value.
     *
     * @return the x value
     */
    public int getIntX() {
        synchronized (this.lock) {
            return (int) this.x;
        }
    }

    /**
     * Obtains the {@code double} representation of this
     * vector's y value.
     *
     * @return the y value
     */
    public double getY() {
        synchronized (this.lock) {
            return this.y;
        }
    }

    /**
     * Obtains the {@code int} representation of this
     * vector's y value.
     *
     * @return the y value
     */
    public int getIntY() {
        synchronized (this.lock) {
            return (int) this.y;
        }
    }

    /**
     * Obtains the {@code double} representation of this
     * vector's z value.
     *
     * @return the z value
     */
    public double getZ() {
        synchronized (this.lock) {
            return this.z;
        }
    }

    /**
     * Obtains the {@code int} representation of this
     * vector's z value.
     *
     * @return the z value
     */
    public int getIntZ() {
        synchronized (this.lock) {
            return (int) this.z;
        }
    }

    /**
     * Sets the x value of this vector
     *
     * @param x the new value to set x
     */
    public void setX(int x) {
        synchronized (this.lock) {
            this.x = x;
        }
    }

    /**
     * Sets the x value of this vector
     *
     * @param x the new value to set x
     */
    public void setX(double x) {
        synchronized (this.lock) {
            this.x = x;
        }
    }

    /**
     * Sets the y value of this vector
     *
     * @param y the new value to set y
     */
    public void setY(int y) {
        synchronized (this.lock) {
            this.y = y;
        }
    }

    /**
     * Sets the y value of this vector
     *
     * @param y the new value to set y
     */
    public void setY(double y) {
        synchronized (this.lock) {
            this.y = y;
        }
    }

    /**
     * Sets the z value of this vector
     *
     * @param z the new value to set z
     */
    public void setZ(int z) {
        synchronized (this.lock) {
            this.z = z;
        }
    }

    /**
     * Sets the z value of this vector
     *
     * @param z the new value to set z
     */
    public void setZ(double z) {
        synchronized (this.lock) {
            this.z = z;
        }
    }

    /**
     * Sets all of the values contained by this vector to
     * those given in the integer arguments.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @param z the z coordinate
     */
    public void set(int x, int y, int z) {
        synchronized (this.lock) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    /**
     * Sets all of the values contained by this vector to
     * those given in the {@code double} arguments.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @param z the z coordinate
     */
    public void set(double x, double y, double z) {
        synchronized (this.lock) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

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
     * @return the current vector, after the operation
     */
    public T add(T vector) {
        synchronized (this.lock) {
            synchronized (vector.lock) {
                this.addImpl(vector.x, vector.y, vector.z);
            }
        }

        return (T) this;
    }

    /**
     * Adds the values given by the parameters to those
     * associated values contained in this vector.
     *
     * @param x the x to add
     * @param y the y to add
     * @param z the z to add
     * @return the current vector, after the operation
     */
    public T add(int x, int y, int z) {
        synchronized (this.lock) {
            this.addImpl((double) x, (double) y, (double) z);
        }

        return (T) this;
    }

    /**
     * Adds the values given by the parameters to those
     * associated values contained in this vector.
     *
     * @param x the x to add
     * @param y the y to add
     * @param z the z to add
     * @return the current vector, after the operation
     */
    public T add(double x, double y, double z) {
        synchronized (this.lock) {
            this.addImpl(x, y, z);
        }

        return (T) this;
    }

    /**
     * JIT Compiler inlining hint
     */
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
    @Internal
    @GuardedBy("lock")
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
     * @return the current vector, after the operation
     */
    public T subtract(T vector) {
        synchronized (this.lock) {
            synchronized (vector.lock) {
                this.subImpl(vector.x, vector.y, vector.z);
            }
        }

        return (T) this;
    }

    /**
     * Subtracts the values given by the parameters to those
     * associated values contained in this vector.
     *
     * @param x the x to subtract
     * @param y the y to subtract
     * @param z the z to subtract
     * @return the current vector, after the operation
     */
    public T subtract(int x, int y, int z) {
        synchronized (this.lock) {
            this.subImpl((double) x, (double) y, (double) z);
        }

        return (T) this;
    }

    /**
     * Subtracts the values given by the parameters to those
     * associated values contained in this vector.
     *
     * @param x the x to subtract
     * @param y the y to subtract
     * @param z the z to subtract
     * @return the current vector, after the operation
     */
    public T subtract(double x, double y, double z) {
        synchronized (this.lock) {
            this.subImpl(x, y, z);
        }

        return (T) this;
    }

    @Internal
    @GuardedBy("lock")
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
     * @return the current vector, after the operation
     */
    public T multiply(T vector) {
        synchronized (this.lock) {
            synchronized (vector.lock) {
                this.mulImpl(vector.x, vector.y, vector.z);
            }
        }

        return (T) this;
    }

    /**
     * Multiplies the values given by the parameters to those
     * associated values contained in this vector.
     *
     * @param x the x to multiply
     * @param y the y to multiply
     * @param z the z to multiply
     * @return the current vector, after the operation
     */
    public T multiply(int x, int y, int z) {
        synchronized (this.lock) {
            this.mulImpl((double) x, (double) y, (double) z);
        }

        return (T) this;
    }

    /**
     * Multiplies the values given by the parameters to those
     * associated values contained in this vector.
     *
     * @param x the x to multiply
     * @param y the y to multiply
     * @param z the z to multiply
     * @return the current vector, after the operation
     */
    public T multiply(double x, double y, double z) {
        synchronized (this.lock) {
            this.mulImpl(x, y, z);
        }

        return (T) this;
    }

    @Internal
    @GuardedBy("lock")
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
     * @return the current vector, after the operation
     */
    public T divide(T vector) {
        synchronized (this.lock) {
            synchronized (vector.lock) {
                this.divImpl(vector.x, vector.y, vector.z);
            }
        }

        return (T) this;
    }

    /**
     * Divides the values given by the parameters to those
     * associated values contained in this vector.
     *
     * @param x the x to divide
     * @param y the y to divide
     * @param z the z to divide
     * @return the current vector, after the operation
     */
    public T divide(int x, int y, int z) {
        synchronized (this.lock) {
            this.divImpl((double) x, (double) y, (double) z);
        }

        return (T) this;
    }

    /**
     * Divides the values given by the parameters to those
     * associated values contained in this vector.
     *
     * @param x the x to divide
     * @param y the y to divide
     * @param z the z to divide
     * @return the current vector, after the operation
     */
    public T divide(double x, double y, double z) {
        synchronized (this.lock) {
            this.divImpl(x, y, z);
        }

        return (T) this;
    }

    @Internal
    @GuardedBy("lock")
    private void divImpl(double x, double y, double z) {
        this.x /= x;
        this.y /= y;
        this.z /= z;
    }

    /**
     * Writes the 3 vector fields to the given one.
     *
     * @param vector the vector to which this vector will
     *               write its fields
     */
    public void vecWrite(AbstractVector<?> vector) {
        synchronized (this.lock) {
            synchronized (vector.lock) {
                vector.x = this.x;
                vector.y = this.y;
                vector.z = this.z;
            }
        }
    }

    /**
     * Create a position based from this vector with
     * a provided world
     *
     * @param world The world of the position
     * @return A Position representation of this vector
     */
    public Position toPosition(World world){
        synchronized (this.lock) {
            return new Position(world, this.x, this.y, this.z);
        }
    }

    /**
     * Writes the fields of this vector to the fields of the
     * given vector, along with the additional fields that
     * may be present in the subclass.
     *
     * @param vector the vector to which this vector will
     *               write its fields
     */
    public void write(T vector) {
        synchronized (this.lock) {
            synchronized (vector.lock) {
                vector.x = this.x;
                vector.y = this.y;
                vector.z = this.z;

                this.writeFields(vector);
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AbstractVector) {
            AbstractVector v = (AbstractVector) obj;
            synchronized (this.lock) {
                synchronized (v.lock) {
                    return eq(this.x, v.x) && eq(this.y, v.y) && eq(this.z, v.z);
                }
            }
        }

        return false;
    }

    @Override
    public int hashCode() {
        // Ignore IntelliJ warning for final field not in hashcode
        // anyone who uses a vector or position object as a
        // key is probably mentally retarded
        synchronized (this.lock) {
            int hash = 1;
            hash = 31 * hash + Long.hashCode(Double.doubleToLongBits(this.x));
            hash = 31 * hash + Long.hashCode(Double.doubleToLongBits(this.y));
            hash = 31 * hash + Long.hashCode(Double.doubleToLongBits(this.z));
            return hash;
        }
    }

    @Override
    public String toString() {
        synchronized (this.lock) {
            return "Vector{" + this.x + ',' + this.y + ',' + this.z + '}';
        }
    }
}
