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

package net.tridentsdk.base.impl;

import net.tridentsdk.Coordinates;
import net.tridentsdk.Difficulty;
import net.tridentsdk.GameMode;
import net.tridentsdk.base.Block;
import net.tridentsdk.entity.Entity;
import net.tridentsdk.world.*;

import java.util.Set;

public class WorldImpl implements World {
    @Override
    public String name() {
        return getClass().getSimpleName();
    }

    @Override
    public Chunk chunkAt(final ChunkLocation loc, boolean generateIfNotFound) {
        return new Chunk() {
            @Override
            public void generate() {
            }

            @Override
            public ChunkLocation location() {
                return loc;
            }

            @Override
            public int x() {
                return loc.x();
            }

            @Override
            public int z() {
                return loc.z();
            }

            @Override
            public World world() {
                return WorldImpl.this;
            }

            @Override
            public Block tileAt(int relX, int y, int relZ) {
                return null;
            }

            @Override
            public ChunkSnapshot snapshot() {
                return null;
            }
        };
    }

    @Override
    public Chunk chunkAt(int x, int z, boolean generateIfNotFound) {
        return chunkAt(ChunkLocation.create(x, z), generateIfNotFound);
    }

    @Override
    public Chunk generateChunk(int x, int z) {
        return chunkAt(x, z, true);
    }

    @Override
    public Chunk generateChunk(ChunkLocation location) {
        return chunkAt(location, true);
    }

    @Override
    public Block tileAt(Coordinates location) {
        return new BlockImpl();
    }

    @Override
    public Dimension dimension() {
        return null;
    }

    @Override
    public Difficulty difficulty() {
        return null;
    }

    @Override
    public GameMode defaultGamemode() {
        return null;
    }

    @Override
    public WorldLoader loader() {
        return null;
    }

    @Override
    public LevelType levelType() {
        return null;
    }

    @Override
    public boolean gameRule(String rule) {
        return false;
    }

    @Override
    public long time() {
        return 0;
    }

    @Override
    public Coordinates spawnLocation() {
        return null;
    }

    @Override
    public boolean isRaining() {
        return false;
    }

    @Override
    public int rainTime() {
        return 0;
    }

    @Override
    public boolean isThundering() {
        return false;
    }

    @Override
    public int thunderTime() {
        return 0;
    }

    @Override
    public boolean canGenerateStructures() {
        return false;
    }

    @Override
    public double borderSize() {
        return 0;
    }

    @Override
    public Coordinates borderCenter() {
        return null;
    }

    @Override
    public int borderSizeContraction() {
        return 0;
    }

    @Override
    public int borderSizeContractionTime() {
        return 0;
    }

    @Override
    public Set<Entity> entities() {
        return null;
    }
}
