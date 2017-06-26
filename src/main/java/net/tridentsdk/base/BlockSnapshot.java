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

import net.tridentsdk.docs.InternalUseOnly;

import javax.annotation.concurrent.Immutable;

/**
 * Represents an instance or "snapshot" of a tile when viewed by the construction method
 *
 * <p>Snapshot using the {@link #of(Block)} method</p>
 *
 * @author The TridentSDK Team
 * @since 0.3-alpha-DP
 */
@Immutable
public final class BlockSnapshot {
    private final Position position;
    private final Substance substance;
    private final byte meta;

    private BlockSnapshot(Position position, Substance substance, byte data) {
        this.position = position;
        this.substance = substance;
        this.meta = data;
    }

    /**
     * Creates a view of the block at the time when taken
     *
     * @param block the tile to view
     * @return the snapshot of the tile
     */
    public static BlockSnapshot of(Block block) {
        return new BlockSnapshot(block.position(), block.substance(), block.meta());
    }

    @InternalUseOnly
    public static BlockSnapshot from(Position position, Substance substance, byte data) {
        return new BlockSnapshot(position, substance, data);
    }

    /**
     * Places the data stored in the snapshot into the original block
     *
     * <p>Does not clear data from this snapshot</p>
     */
    public void load() {
        Block block = position.world().blockAt(position);
        block.setSubstance(substance);
        block.setMeta(meta);
    }

    /**
     * Obtains the position of the block
     *
     * @return the position
     */
    public Position position() {
        return position;
    }

    /**
     * Obtains the block substance
     *
     * @return the substance
     */
    public Substance type() {
        return substance;
    }

    /**
     * Obtains the block meta
     *
     * @return the block meta
     */
    public byte meta() {
        return meta;
    }
}
