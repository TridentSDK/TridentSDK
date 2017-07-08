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

import lombok.Getter;
import net.tridentsdk.doc.Internal;
import net.tridentsdk.world.World;

import javax.annotation.concurrent.Immutable;
import java.io.Serializable;

/**
 * A vector is an immutable tuple consisting of 3 arbitrary
 * {@code double} values that could be used to represent
 * locations, coordinates, etc...
 *
 * <p>Any method that is prefixed with {@code set}
 * <strong>WILL NOT</strong> produce any changes to the
 * existing {@code AbstractVector}. It will instead, return
 * a new {@code AbstractVector} containing the same fields,
 * save for the ones that were modified by that operation.
 * </p>
 *
 * @param <T> this must be the subclass type in order to
 *            provide the proper functionality
 * @author TridentSDK
 * @since 0.5-alpha
 */
@Immutable
@Internal("public access for convenience only")
public abstract class AbstractVector<T extends AbstractVector<T>> implements Serializable {
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
    @Getter
    protected final double x;
    @Getter
    protected final double y;
    @Getter
    protected final double z;

    /**
     * Creates a new AbstractVector object with all 3 values set to
     * {@code 0}.
     */
    public AbstractVector() {
        this(0D, 0D, 0D);
    }

    /**
     * Creates a new AbstractVector object using
     * {@code int}s.
     *
     * @param x the x value
     * @param y the y value
     * @param z the z value
     */
    public AbstractVector(int x, int y, int z) {
        this((double) x, (double) y, (double) z);
    }

    /**
     * Creates a new AbstractVector object using
     * {@code double}s.
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
     * Obtains the {@code int} representation of this
     * vector's x value.
     *
     * @return the x value
     */
    public int getIntX() {
        return (int) this.x;
    }

    /**
     * Obtains the {@code int} representation of this
     * vector's y value.
     *
     * @return the y value
     */
    public int getIntY() {
        return (int) this.y;
    }

    /**
     * Obtains the {@code int} representation of this
     * vector's z value.
     *
     * @return the z value
     */
    public int getIntZ() {
        return (int) this.z;
    }

    /**
     * Creates a new vector, containing all of the same
     * fields as this vector, but with the X value set to
     * the given number.
     *
     * @param x the new X value to set in the created vector
     * @return the vector with the new X value and all of
     * the rest of its fields unmodified
     */
    public T setX(int x) {
        return this.setX((double) x);
    }

    /**
     * Creates a new vector, containing all of the same
     * fields as this vector, but with the X value set to
     * the given number.
     *
     * @param x the new X value to set in the created vector
     * @return the vector with the new X value and all of
     * the rest of its fields unmodified
     */
    public abstract T setX(double x);

    /**
     * Creates a new vector, containing all of the same
     * fields as this vector, but with the Y value set to
     * the given number.
     *
     * @param y the new Y value to set in the created vector
     * @return the vector with the new Y value and all of
     * the rest of its fields unmodified
     */
    public T setY(int y) {
        return this.setY((double) y);
    }

    /**
     * Creates a new vector, containing all of the same
     * fields as this vector, but with the Y value set to
     * the given number.
     *
     * @param y the new Y value to set in the created vector
     * @return the vector with the new Y value and all of
     * the rest of its fields unmodified
     */
    public abstract T setY(double y);

    /**
     * Creates a new vector, containing all of the same
     * fields as this vector, but with the Z value set to
     * the given number.
     *
     * @param z the new Z value to set in the created vector
     * @return the vector with the new Z value and all of
     * the rest of its fields unmodified
     */
    public T setZ(int z) {
        return this.setZ((double) z);
    }

    /**
     * Creates a new vector, containing all of the same
     * fields as this vector, but with the Z value set to
     * the given number.
     *
     * @param z the new Z value to set in the created vector
     * @return the vector with the new Z value and all of
     * the rest of its fields unmodified
     */
    public abstract T setZ(double z);

    /**
     * Creates a new vector, with the given new set of XYZ
     * numbers, saving the non-vector fields.
     *
     * @param x the new X value
     * @param y the new Y value
     * @param z the new Z value
     * @return the new vector containing the specified XYZ
     * coordinates, but retaining the same fields that may
     * be possessed by a subclass
     */
    public T set(int x, int y, int z) {
        return this.set((double) x, (double) y, (double) z);
    }

    /**
     * Creates a new vector, with the given new set of XYZ
     * numbers, saving the non-vector fields.
     *
     * @param x the new X value
     * @param y the new Y value
     * @param z the new Z value
     * @return the new vector containing the specified XYZ
     * coordinates, but retaining the same fields that may
     * be possessed by a subclass
     */
    public abstract T set(double x, double y, double z);

    /**
     * Creates a new vector but its XYZ fields are those of
     * this vector added with the XYZ fields of the given
     * vector.
     *
     * @param vector the XYZ fields of which to add to this
     * vector
     * @return the new vector with this vector's XYZ values
     * added to the given vector's XYZ values
     */
    public T add(T vector) {
        return this.set(this.x + vector.x, this.y + vector.y, this.z + vector.z);
    }

    /**
     * Creates a new vector but its XYZ fields are those of
     * this vector added with the given values.
     *
     * @param x the amount to add to this vector's X value
     * @param y the amount to add to this vector's Y value
     * @param z the amount to add to this vector's Z value
     * @return the new vector with this vector's XYZ values
     * added to those given in the parameters
     */
    public T add(int x, int y, int z) {
        return this.set(this.x + x, this.y + y, this.z + z);
    }

    /**
     * Creates a new vector but its XYZ fields are those of
     * this vector added with the given values.
     *
     * @param x the amount to add to this vector's X value
     * @param y the amount to add to this vector's Y value
     * @param z the amount to add to this vector's Z value
     * @return the new vector with this vector's XYZ values
     * added to those given in the parameters
     */
    public T add(double x, double y, double z) {
        return this.set(this.x + x, this.y + y, this.z + z);
    }

    /**
     * Creates a new vector but its XYZ fields are those of
     * this vector subtracted with the XYZ fields of the
     * given vector.
     *
     * @param vector the XYZ fields of which to subtract
     * from this vector
     * @return the new vector with this vector's XYZ values
     * subtracted with the given vector's XYZ values
     */
    public T subtract(T vector) {
        return this.set(this.x - vector.x, this.y - vector.y, this.z - vector.z);
    }

    /**
     * Creates a new vector but its XYZ fields are those of
     * this vector subtracted with the given values.
     *
     * @param x the amount to subtract from this vector's X
     * value
     * @param y the amount to subtract from this vector's Y
     * value
     * @param z the amount to subtract from this vector's Z
     * value
     * @return the new vector with this vector's XYZ values
     * subtracted with those given in the parameters
     */
    public T subtract(int x, int y, int z) {
        return this.set(this.x - x, this.y - y, this.z - z);
    }

    /**
     * Creates a new vector but its XYZ fields are those of
     * this vector subtracted with the given values.
     *
     * @param x the amount to subtract from this vector's X
     * value
     * @param y the amount to subtract from this vector's Y
     * value
     * @param z the amount to subtract from this vector's Z
     * value
     * @return the new vector with this vector's XYZ values
     * subtracted with those given in the parameters
     */
    public T subtract(double x, double y, double z) {
        return this.set(this.x - x, this.y - y, this.z - z);
    }

    /**
     * Creates a new vector but its XYZ fields are those of
     * this vector multiplied with the XYZ fields of the
     * given vector.
     *
     * @param vector the XYZ fields of which to multiply
     * with this vector
     * @return the new vector with this vector's XYZ values
     * multiplied to the given vector's XYZ values
     */
    public T multiply(T vector) {
        return this.set(this.x * vector.x, this.y * vector.y, this.z * vector.z);
    }

    /**
     * Creates a new vector but its XYZ fields are those of
     * this vector multiplied with the given values.
     *
     * @param x the amount to multiply with this vector's X
     * value
     * @param y the amount to multiply with this vector's Y
     * value
     * @param z the amount to multiply with this vector's Z
     * value
     * @return the new vector with this vector's XYZ values
     * multiplied with those given in the parameters
     */
    public T multiply(int x, int y, int z) {
        return this.set(this.x * x, this.y * y, this.z * z);
    }

    /**
     * Creates a new vector but its XYZ fields are those of
     * this vector multiplied with the given values.
     *
     * @param x the amount to multiply with this vector's X
     * value
     * @param y the amount to multiply with this vector's Y
     * value
     * @param z the amount to multiply with this vector's Z
     * value
     * @return the new vector with this vector's XYZ values
     * multiplied with those given in the parameters
     */
    public T multiply(double x, double y, double z) {
        return this.set(this.x * x, this.y * y, this.z * z);
    }

    /**
     * Creates a new vector but its XYZ fields are those of
     * this vector divided with the XYZ fields of the given
     * vector.
     *
     * @param vector the XYZ fields of which to divide from
     * this vector
     * @return the new vector with this vector's XYZ values
     * divided with the given vector's XYZ values
     */
    public T divide(T vector) {
        return this.set(this.x / vector.x, this.y / vector.y, this.z / vector.z);
    }

    /**
     * Creates a new vector but its XYZ fields are those of
     * this vector divided with the given values.
     *
     * @param x the amount to divide the this vector's X
     * value
     * @param y the amount to divide the this vector's Y
     * value
     * @param z the amount to divide the this vector's Z
     * value
     * @return the new vector with this vector's XYZ values
     * divided with those given in the parameters
     */
    public T divide(int x, int y, int z) {
        return this.set(this.x / x, this.y / y, this.z / z);
    }

    /**
     * Creates a new vector but its XYZ fields are those of
     * this vector divided with the given values.
     *
     * @param x the amount to divide the this vector's X
     * value
     * @param y the amount to divide the this vector's Y
     * value
     * @param z the amount to divide the this vector's Z
     * value
     * @return the new vector with this vector's XYZ values
     * divided with those given in the parameters
     */
    public T divide(double x, double y, double z) {
        return this.set(this.x / x, this.y / y, this.z / z);
    }

    /**
     * Create a position based from this vector with
     * a provided world.
     *
     * @param world The world of the position
     * @return A Position representation of this vector
     */
    public Position toPosition(World world){
        return new Position(world, this.x, this.y, this.z);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AbstractVector) {
            AbstractVector v = (AbstractVector) obj;
            return eq(this.x, v.x) && eq(this.y, v.y) && eq(this.z, v.z);
        }

        return false;
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash = 31 * hash + Long.hashCode(Double.doubleToLongBits(this.x));
        hash = 31 * hash + Long.hashCode(Double.doubleToLongBits(this.y));
        hash = 31 * hash + Long.hashCode(Double.doubleToLongBits(this.z));
        return hash;
    }

    @Override
    public String toString() {
        return "Vector{" + this.x + ',' + this.y + ',' + this.z + '}';
    }
}