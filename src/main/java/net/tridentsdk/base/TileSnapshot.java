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

import net.tridentsdk.Coordinates;
import net.tridentsdk.entity.projectile.Projectile;

import javax.annotation.concurrent.Immutable;
import java.util.Collection;

/**
 * Represents an instance or "snapshot" of a tile when viewed by the construction method
 *
 * <p>Snapshot using the {@link #of(Tile)} method</p>
 *
 * @author The TridentSDK Team
 */
@Immutable
public final class TileSnapshot {
    private final Coordinates location;
    private final Substance material;
    private final byte data;
    private final Collection<Projectile> projectiles;

    private TileSnapshot(Coordinates location, Substance material, byte data, Collection<Projectile> projectiles) {
        this.location = location;
        this.material = material;
        this.data = data;
        this.projectiles = projectiles;
    }

    /**
     * Creates a view of the block at the time when "snapshotted"
     *
     * @param tile the tile to view
     * @return the snapshot of the tile
     */
    public static TileSnapshot of(Tile tile) {
        return new TileSnapshot(tile.getLocation(), tile.getSubstance(), tile.getMeta(), tile.projectiles());
    }

    /**
     * Places the data stored in the snapshot into the original block
     *
     * <p>Does not clear data from this snapshot</p>
     */
    public void load() {
        Tile tile = location.getWorld().getTileAt(location);
        tile.setSubstance(material);
        tile.setMeta(data);
        tile.clear();
        for (Projectile projectile : projectiles)
            tile.put(projectile);
    }
}
