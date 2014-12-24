package net.tridentsdk.base.impl;

import net.tridentsdk.Coordinates;
import net.tridentsdk.Difficulty;
import net.tridentsdk.GameMode;
import net.tridentsdk.base.Tile;
import net.tridentsdk.entity.Entity;
import net.tridentsdk.world.*;

import java.util.Set;

public class WorldImpl implements World {
    @Override
    public String getName() {
        return getClass().getSimpleName();
    }

    @Override
    public Chunk getChunkAt(final ChunkLocation loc, boolean generateIfNotFound) {
        return new Chunk() {
            @Override
            public void generate() {
            }

            @Override
            public ChunkLocation getLocation() {
                return loc;
            }

            @Override
            public int getX() {
                return loc.getX();
            }

            @Override
            public int getZ() {
                return loc.getZ();
            }

            @Override
            public World getWorld() {
                return WorldImpl.this;
            }

            @Override
            public Tile getTileAt(int relX, int y, int relZ) {
                return null;
            }

            @Override
            public ChunkSnapshot snapshot() {
                return null;
            }
        };
    }

    @Override
    public Chunk getChunkAt(int x, int z, boolean generateIfNotFound) {
        return getChunkAt(ChunkLocation.create(x, z), generateIfNotFound);
    }

    @Override
    public Chunk generateChunk(int x, int z) {
        return getChunkAt(x, z, true);
    }

    @Override
    public Chunk generateChunk(ChunkLocation location) {
        return getChunkAt(location, true);
    }

    @Override
    public Tile getTileAt(Coordinates location) {
        return new TileImpl();
    }

    @Override
    public Dimension getDimension() {
        return null;
    }

    @Override
    public Difficulty getDifficulty() {
        return null;
    }

    @Override
    public GameMode getDefaultGamemode() {
        return null;
    }

    @Override
    public LevelType getLevelType() {
        return null;
    }

    @Override
    public boolean getGamerule(String rule) {
        return false;
    }

    @Override
    public long getTime() {
        return 0;
    }

    @Override
    public Coordinates getSpawn() {
        return null;
    }

    @Override
    public boolean isRaining() {
        return false;
    }

    @Override
    public int getRainTime() {
        return 0;
    }

    @Override
    public boolean isThundering() {
        return false;
    }

    @Override
    public int getThunderTime() {
        return 0;
    }

    @Override
    public boolean canGenerateStructures() {
        return false;
    }

    @Override
    public int getBorderSize() {
        return 0;
    }

    @Override
    public Coordinates getBorderCenter() {
        return null;
    }

    @Override
    public int getBorderSizeContraction() {
        return 0;
    }

    @Override
    public int getBorderSizeContractionTime() {
        return 0;
    }

    @Override
    public Set<Entity> getEntities() {
        return null;
    }
}
