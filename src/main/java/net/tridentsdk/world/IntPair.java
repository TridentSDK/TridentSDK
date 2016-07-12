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
package net.tridentsdk.world;

import javax.annotation.concurrent.Immutable;

/**
 * This class is a wrapper over a pair of {@code int}s,
 * useful for applications where only two numbers are needed
 * such as a replacement vector, located chunks, or world
 * borders.
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
@Immutable
public final class IntPair {
    /**
     * The first integer
     */
    private final int x;
    /**
     * The second integer
     */
    private final int z;

    /**
     * Creates a new {@code int} pair.
     *
     * @param x the first {@code int}
     * @param z the second {@code int}
     */
    private IntPair(int x, int z) {
        this.x = x;
        this.z = z;
    }

    /**
     * Pairs together the two given {@code int}s.
     *
     * @param x the first {@code int}
     * @param z the second {@code int}
     * @return the paired {@code int}s
     */
    public static IntPair make(int x, int z) {
        return new IntPair(x, z);
    }

    /**
     * Obtains the first {@code int} in the pair.
     *
     * @return the beginning {@code int}
     */
    public int x() {
        return this.x;
    }

    /**
     * Obtains the second {@code int} in the pair.
     *
     * @return the last {@code int}
     */
    public int z() {
        return this.z;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof IntPair) {
            IntPair pair = (IntPair) obj;
            return pair.x == this.x && pair.z == this.z;
        }

        return false;
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash = 31 * hash + this.x;
        hash = 31 * hash + this.z;
        return hash;
    }

    @Override
    public String toString() {
        return String.format("IntPair{%d,%d}", this.x, this.z);
    }
}