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

package net.tridentsdk.world;

import javax.annotation.concurrent.Immutable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Stores the position (the X and Z coordinates) of a Chunk
 *
 * <p>One produces a new ChunkLocation using {@link #create(int, int)}</p>
 *
 * <p>You may reuse ChunkLocations, but never modify them. This is so Chunks occupying a ChunkLocation within a world
 * cannot chnage its position. This comes at a cost of memory, but offers advantages of low overhead thread-safety and
 * defensive programming.</p>
 *
 * @author The TridentSDK Team
 * @since 0.3-alpha-DP
 */
@Immutable
public final class ChunkLocation implements Serializable, Cloneable {
    private static final long serialVersionUID = 9083698035337137603L;
    private final int x;
    private final int z;

    private ChunkLocation(int x, int z) {
        this.x = x;
        this.z = z;
    }

    /**
     * Produces a new chunk coordinate using the two positions specified
     *
     * @param x the X coordinate
     * @param z the Z coordinate
     * @return the new chunk location
     */
    public static ChunkLocation create(int x, int z) {
        return new ChunkLocation(x, z);
    }

    /**
     * Obtains the X coordinate for the ChunkLocation
     *
     * @return the X coordinate
     */
    public int x() {
        return this.x;
    }

    /**
     * Obtains the Z coordinate ofr the ChunkLocation
     *
     * @return the Z coordinate
     */
    public int z() {
        return this.z;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof ChunkLocation) &&
                ((ChunkLocation) obj).x == x && ((ChunkLocation) obj).z == z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, z);
    }

    @Override
    public Object clone() {
        return ChunkLocation.create(x, z);
    }

    @Override
    public String toString() {
        return "ChunkLocation(" + x() + ", " + z() + ")@" + hashCode();
    }
}
