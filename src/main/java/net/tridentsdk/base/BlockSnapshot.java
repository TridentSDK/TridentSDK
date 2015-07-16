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

import net.tridentsdk.Position;
import net.tridentsdk.docs.InternalUseOnly;

import javax.annotation.concurrent.Immutable;

/**
 * Represents an instance or "snapshot" of a tile when viewed by the construction method
 *
 * <p>Snapshot using the {@link #of(Block)} method</p>
 *
 * @author The TridentSDK Team
 */
@Immutable
public final class BlockSnapshot {
    private final Position location;
    private final Substance material;
    private final byte data;

    private BlockSnapshot(Position location, Substance material, byte data) {
        this.location = location;
        this.material = material;
        this.data = data;
    }

    /**
     * Creates a view of the block at the time when "snapshotted"
     *
     * @param block the tile to view
     * @return the snapshot of the tile
     */
    public static BlockSnapshot of(Block block) {
        return new BlockSnapshot(block.getPosition(), block.getSubstance(), block.getMeta());
    }

    @InternalUseOnly
    public static BlockSnapshot from(Position location, Substance material, byte data) {
        return new BlockSnapshot(location, material, data);
    }

    /**
     * Places the data stored in the snapshot into the original block
     *
     * <p>Does not clear data from this snapshot</p>
     */
    public void load() {
        Block block = location.getWorld().getBlockAt(location);
        block.setSubstance(material);
        block.setMeta(data);
    }

    public Position getPosition() {
        return location;
    }

    public Substance getSubstance() {
        return material;
    }

    public byte getData() {
        return data;
    }
}
