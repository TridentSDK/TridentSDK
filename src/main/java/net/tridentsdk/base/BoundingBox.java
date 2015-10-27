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

/**
 * An axis-aligned, rectangular polygon in which entities reside
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 */
public class BoundingBox implements Cloneable {
    private double minX;
    private double minY;
    private double minZ;
    private double maxX;
    private double maxY;
    private double maxZ;

    public BoundingBox(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        set(minX, minY, minZ, maxX, maxY, maxZ);
    }

    public BoundingBox set(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        this.minX = minX;
        this.minY = minY;
        this.minZ = minZ;
        this.maxX = maxX;
        this.maxY = maxY;
        this.maxZ = maxZ;
        return this;
    }

    public BoundingBox add(double x, double y, double z) {
        this.minX = this.minX() + x;
        this.minY = this.minY() + y;
        this.minZ = this.minZ() + z;
        this.maxX = this.maxX() + x;
        this.maxY = this.maxY() + y;
        this.maxZ = this.maxZ() + z;
        return this;
    }

    public BoundingBox grow(double x, double y, double z) {
        return new BoundingBox(this.minX() - x, this.minY() - y, this.minZ() - z, this.maxX() + x, this.maxY() + y, this.maxZ() + z);
    }

    public BoundingBox shrink(double x, double y, double z) {
        return new BoundingBox(this.minX() + x, this.minY() + y, this.minZ() + z, this.maxX() - x, this.maxY() - y, this.maxZ() - z);
    }

    public boolean collidesWith(BoundingBox box){
        return !(this.maxX() < box.minX() || this.minX() > box.maxX()) && !(this.maxY() < box.minY() || this.minY() > box.maxY()) && !(this.maxZ() < box.minZ() || this.minZ() > box.maxZ());
    }

    public BoundingBox clone() {
        return new BoundingBox(this.minX(), this.minY(), this.minZ(), this.maxX(), this.maxY(), this.maxZ());
    }

    public String toString() {
        return "BoundingBox{" + this.minX() + ", " + this.minY() + ", " + this.minZ() + " -> " + this.maxX() + ", " + this.maxY() + ", " + this.maxZ() + "}";
    }

    public double minX() {
        return minX;
    }

    public double minY() {
        return minY;
    }

    public double minZ() {
        return minZ;
    }

    public double maxX() {
        return maxX;
    }

    public double maxY() {
        return maxY;
    }

    public double maxZ() {
        return maxZ;
    }
}