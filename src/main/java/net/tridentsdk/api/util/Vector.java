/*
 * Copyright (C) 2014 The TridentSDK Team
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.tridentsdk.api.util;

import java.io.Serializable;

/**
 * Just like in math, a vector represents magnitude and direction, where magnitude is usually the speed of an entity.
 *
 * @author The TridentSDK Team
 */
public class Vector implements Serializable, Cloneable {
    private static final long serialVersionUID = -7634050835106851288L;

    private double x;
    private double y;
    private double z;

    /**
     * Creates a default vector with x, y, and z set to (0, 0, 0)
     */
    public Vector() {
        this(0, 0, 0);
    }

    /**
     * Creates a vector with the directional values set to the provided values
     *
     * @param x the x value of the vector
     * @param y the y value of the vector
     * @param z the z value of the vector
     */
    public Vector(double x, double y, double z) {
        this.setX(x);
        this.setY(y);
        this.setZ(z);
    }

    /**
     * Creates a vector with the directional values set to the provided values
     *
     * @param x the x value of the vector
     * @param y the y value of the vector
     * @param z the z value of the vector
     */
    public Vector(int x, int y, int z) {
        this.setX((double) x);
        this.setY((double) y);
        this.setZ((double) z);
    }

    /**
     * Adds the vector x, y, and z to the current vector coordinates
     *
     * @param vector the vector to retrieve the values to be added
     * @return the current vector with updated coordinates
     */
    public Vector add(Vector vector) {
        return this.add(vector.getX(), vector.getY(), vector.getZ());
    }

    /**
     * Adds the coordinate values to the current vector's coordinates with double accuracy
     *
     * @param x the x value of the vector to add
     * @param y the y value of the vector to add
     * @param z the z value of the vector to add
     * @return the current vector with updated coordinates
     */
    public Vector add(double x, double y, double z) {
        this.setX(this.getX() + x);
        this.setY(this.getY() + y);
        this.setZ(this.getZ() + z);

        return this;
    }

    /**
     * Adds the coordinate values to the current vector's coordinates
     *
     * @param x the x value of the vector to add
     * @param y the y value of the vector to add
     * @param z the z value of the vector to add
     * @return the current vector with updated coordinates
     */
    public Vector add(int x, int y, int z) {
        return this.add((double) x, (double) y, (double) z);
        /* Implementation detail:
        DO NOT CREATE A NEW VECTOR HERE JUST BECAUSE (!)
        Doing so wastes memory and adds unnecessary object
        creation overhead, therefore, delegate to the
        setters instead of the previous implementation.
        Same with the other methods */
    }

    /**
     * Takes the current vector coordinates and subtract them with the vector x, y, and z
     *
     * @param vector the vector to retrieve the values to be subtracted
     * @return the current vector with updated coordinates
     */
    public Vector subtract(Vector vector) {
        return this.subtract(vector.getX(), vector.getY(), vector.getZ());
    }

    /**
     * Takes the the current vector's coordinates and subtracts them from the coordinate values with double accuracy
     *
     * @param x the x value of the vector to subtract
     * @param y the y value of the vector to subtract
     * @param z the z value of the vector to subtract
     * @return the current vector with updated coordinates
     */
    public Vector subtract(double x, double y, double z) {
        this.setX(this.getX() - x);
        this.setY(this.getY() - y);
        this.setZ(this.getZ() - z);

        return this;
    }

    /**
     * Takes the the current vector's coordinates and subtracts them from the coordinate values
     *
     * @param x the x value of the vector to subtract
     * @param y the y value of the vector to subtract
     * @param z the z value of the vector to subtract
     * @return the current vector with updated coordinates
     */
    public Vector subtract(int x, int y, int z) {
        return this.subtract((double) x, (double) y, (double) z);
    }

    /**
     * Multiplies the vector x, y, and z to the current vector coordinates
     *
     * @param vec the vector to retrieve the values to be multiplied
     * @return the current vector with updated coordinates
     */
    public Vector multiply(Vector vec) {
        return this.multiply(vec.getX(), vec.getY(), vec.getZ());
    }

    /**
     * Multiplies the coordinate values to the current vector's coordinates with double accuracy
     *
     * @param x the x value of the vector to multiply
     * @param y the y value of the vector to multiply
     * @param z the z value of the vector to multiply
     * @return the current vector with updated coordinates
     */
    public Vector multiply(double x, double y, double z) {
        this.setX(this.getX() * x);
        this.setY(this.getY() * y);
        this.setZ(this.getZ() * z);

        return this;
    }

    /**
     * Multiplies the coordinate values to the current vector's coordinates
     *
     * @param x the x value of the vector to multiply
     * @param y the y value of the vector to multiply
     * @param z the z value of the vector to multiply
     * @return the current vector with updated coordinates
     */
    public Vector multiply(int x, int y, int z) {
        return this.multiply((double) x, (double) y, (double) z);
    }

    /**
     * Takes the current vector coordinates and divide them with the vector x, y, and z
     *
     * @param vec the vector to retrieve the values to be divided
     * @return the current vector with updated coordinates
     */
    public Vector divide(Vector vec) {
        return this.divide(vec.getX(), vec.getY(), vec.getZ());
    }

    /**
     * Takes the the current vector's coordinates and divides them from the coordinate values with double accuracy
     *
     * @param x the x value of the vector to divide
     * @param y the y value of the vector to divide
     * @param z the z value of the vector to divide
     * @return the current vector with updated coordinates
     */
    public Vector divide(double x, double y, double z) {
        this.setX(this.getX() / x);
        this.setY(this.getY() / y);
        this.setZ(this.getZ() / z);

        return this;
    }

    /**
     * Takes the the current vector's coordinates and divides them from the coordinate values
     *
     * @param x the x value of the vector to divide
     * @param y the y value of the vector to divide
     * @param z the z value of the vector to divide
     * @return the current vector with updated coordinates
     */
    public Vector divide(int x, int y, int z) {
        return this.divide((double) x, (double) y, (double) z);
    }

    /**
     * Gets the x directional-magnitude value
     *
     * @return the vector x value
     */
    public double getX() {
        return this.x;
    }

    /**
     * Sets this vector's x value
     *
     * @param x the x value to set this vector
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Gets the y directional-magnitude value
     *
     * @return the vector y value
     */
    public double getY() {
        return this.y;
    }

    /**
     * Sets this vector's y value
     *
     * @param y the y value to set this vector
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Gets the z directional-magnitude value
     *
     * @return the vector z value
     */
    public double getZ() {
        return this.z;
    }

    /**
     * Sets this vector's z value
     *
     * @param z the z value to set this vector
     */
    public void setZ(double z) {
        this.z = z;
    }
}
