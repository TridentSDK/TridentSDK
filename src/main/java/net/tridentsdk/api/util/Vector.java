package net.tridentsdk.api.util;

import java.io.Serializable;

public class Vector implements Serializable, Cloneable {

    protected double x;
    protected double y;
    protected double z;

    public Vector() {
        this(0, 0, 0);
    }

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector add(Vector vector) {
        x += vector.x;
        y += vector.y;
        z += vector.z;

        return this;
    }

    public Vector add(double x, double y, double z) {
        return add(new Vector(x, y, z));
    }

    public Vector add(int x, int y, int z) {
        return add(new Vector(x, y, z));
    }

    public Vector subtract(Vector vector) {
        x -= vector.x;
        y -= vector.y;
        z -= vector.z;

        return this;
    }

    public Vector subtract(double x, double y, double z) {
        return subtract(new Vector(x, y, z));
    }

    public Vector subtract(int x, int y, int z) {
        return subtract(new Vector(x, y, z));
    }

    public Vector multiply(Vector vec) {
        x *= vec.x;
        y *= vec.y;
        z *= vec.z;

        return this;
    }

    public Vector multiply(double x, double y, double z) {
        return multiply(new Vector(x, y, z));
    }

    public Vector multiply(int x, int y, int z) {
        return multiply(new Vector(x, y, z));
    }

    public Vector divide(Vector vec) {
        x /= vec.x;
        y /= vec.y;
        z /= vec.z;

        return this;
    }

    public Vector divide(double x, double y, double z) {
        return divide(new Vector(x, y, z));
    }

    public Vector divide(int x, int y, int z) {
        return divide(new Vector(x, y, z));
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }
}
