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

    public BoundingBox shift(double x, double y, double z) {
        return new BoundingBox(this.minX() + x, this.minY() + y, this.minZ() + z, this.maxX() + x, this.maxY() + y, this.maxZ() + z);
    }

    public BoundingBox weirdGrow(double x, double y, double z) {
        double newMinX = this.minX;
        double newMinY = this.minY;
        double newMinZ = this.minZ;
        double newMaxX = this.maxX;
        double newMaxY = this.maxY;
        double newMaxZ = this.maxZ;

        if (x < 0.0) {
            newMinX += x;
        } else if (x > 0.0) {
            newMaxX += x;
        }

        if (y < 0.0) {
            newMinY += y;
        } else if (y > 0.0) {
            newMaxY += y;
        }

        if (z < 0.0) {
            newMinZ += z;
        } else if (z > 0.0) {
            newMaxZ += z;
        }

        return new BoundingBox(newMinX, newMinY, newMinZ, newMaxX, newMaxY, newMaxZ);
    }

    public double somethingToDoWithX(BoundingBox boundingBox, double motionX) {
        if (boundingBox.maxY <= this.minY || boundingBox.minY >= this.maxY || boundingBox.maxZ <= this.minZ || boundingBox.minZ >= this.maxZ) {
            return motionX;
        }

        if (motionX > 0.0 && boundingBox.maxX <= this.minX) {
            double newMotionX = this.minX - boundingBox.maxX;
            if (newMotionX < motionX) {
                return newMotionX;
            }
        } else if (motionX < 0.0 && boundingBox.minX >= this.maxX) {
            double newMotionX = this.maxX - boundingBox.minX;
            if(newMotionX > motionX){
                return newMotionX;
            }
        }

        return motionX;
    }

    public double somethingToDoWithY(BoundingBox boundingBox, double motionY) {
        if (boundingBox.maxX <= this.minX || boundingBox.minX >= this.maxX || boundingBox.maxZ <= this.minZ || boundingBox.minZ >= this.maxZ) {
            return motionY;
        }

        if (motionY > 0.0 && boundingBox.maxY <= this.minY) {
            double newMotionY = this.minY - boundingBox.maxY;
            if (newMotionY < motionY) {
                return newMotionY;
            }
        } else if (motionY < 0.0 && boundingBox.minY >= this.maxY) {
            double newMotionY = this.maxY - boundingBox.minY;
            if(newMotionY > motionY){
                return newMotionY;
            }
        }

        return motionY;
    }

    public double somethingToDoWithZ(BoundingBox boundingBox, double motionZ) {
        if (boundingBox.maxX <= this.minX || boundingBox.minX >= this.maxX || boundingBox.maxY <= this.minY || boundingBox.minY >= this.maxY) {
            return motionZ;
        }

        if (motionZ > 0.0 && boundingBox.maxZ <= this.minZ) {
            double newMotionZ = this.minZ - boundingBox.maxZ;
            if (newMotionZ < motionZ) {
                return newMotionZ;
            }
        } else if (motionZ < 0.0 && boundingBox.minZ >= this.maxZ) {
            double newMotionZ = this.maxZ - boundingBox.minZ;
            if(newMotionZ > motionZ){
                return newMotionZ;
            }
        }

        return motionZ;
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