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

package net.tridentsdk.world.gen;

import net.tridentsdk.Position;
import net.tridentsdk.base.Substance;
import net.tridentsdk.factory.Factories;
import net.tridentsdk.world.Chunk;

/**
 * Represents a tile that is awaiting pending status to be set in a generated world
 *
 * @author The TridentSDK Team
 */
public class TempGenBlock {
    private final int x;
    private final int y;
    private final int z;
    private final Substance substance;
    private final byte data;

    private TempGenBlock(int x, int y, int z, Substance substance, byte data) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.substance = substance;
        this.data = data;
    }

    /**
     * Creates a new pending chunk tile
     *
     * @param x         x coordinate of the tile
     * @param y         y coordinate of the tile
     * @param z         z coordinate of the tile
     * @param substance the material to set at the location
     * @return the new pending chunk tile
     */
    public static TempGenBlock create(int x, int y, int z, Substance substance) {
        return new TempGenBlock(x, y, z, substance, (byte) 0);
    }

    public static TempGenBlock create(int x, int y, int z, Substance substance, byte data) {
        return new TempGenBlock(x, y, z, substance, data);
    }

    public Position coordinates() {
        return Position.create(null, x, y, z);
    }

    public Substance substance() {
        return substance;
    }

    public byte meta() {
        return data;
    }

    /**
     * Sets the block at the location in the specified world the pending tile
     *
     * @param chunk the chunk to apply to
     */
    public void apply(Chunk chunk) {
        Factories.gen().putBlock(this, chunk);
    }
}
