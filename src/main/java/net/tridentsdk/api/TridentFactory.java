/*
 * Copyright (c) 2014, The TridentSDK Team
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     1. Redistributions of source code must retain the above copyright
 *        notice, this list of conditions and the following disclaimer.
 *     2. Redistributions in binary form must reproduce the above copyright
 *        notice, this list of conditions and the following disclaimer in the
 *        documentation and/or other materials provided with the distribution.
 *     3. Neither the name of the The TridentSDK Team nor the
 *        names of its contributors may be used to endorse or promote products
 *        derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL The TridentSDK Team BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package net.tridentsdk.api;

import net.tridentsdk.api.entity.Entity;
import net.tridentsdk.api.inventory.Inventory;
import net.tridentsdk.api.inventory.ItemStack;
import net.tridentsdk.api.nbt.CompoundTag;
import net.tridentsdk.api.nbt.builder.CompoundTagBuilder;
import net.tridentsdk.api.nbt.builder.NBTBuilder;
import net.tridentsdk.api.trade.ItemPair;
import net.tridentsdk.api.util.TridentLogger;
import net.tridentsdk.api.util.Vector;
import net.tridentsdk.api.world.*;

import java.util.logging.Logger;

/**
 * Factory creation methods for basic API classes
 *
 * @author The TridentSDK Team
 */
public final class TridentFactory {
    private TridentFactory() {
    }

    /* Block */

    /**
     * Creates a Block instance without location
     *
     * @return Block created instance without location
     */
    public static Block createBlock() {
        return new Block(null);
    }

    /**
     * Creates a Block instance with a location
     *
     * @param location Location of the block
     * @return Block created instance with a location
     */
    public static Block createBlock(Location location) {
        return new Block(location);
    }

    /**
     * Creates a Block instance from specified co-ordinates
     *
     * @param world World in which the Block belongs in
     * @param x     X co-ordinate of the block
     * @param y     Y co-ordinate of the block
     * @param z     Z co-ordinate of the block
     * @return Block created instance
     */
    public static Block createBlock(World world, double x, double y, double z) {
        return new Block(TridentFactory.createLocation(world, x, y, z));
    }

    /* Location */

    /**
     * Creates a Location with no world <p>The created Location's co-ordinates will default to 0.</p>
     *
     * @return Location created instance with no world
     */
    public static Location createLocation() {
        return new Location(null, 0.0, 0.0, 0.0);
    }

    /**
     * Creates a Location with no specified co-ordinates <p>The created Location's co-ordinates will default to 0.</p>
     *
     * @param world World in which the Location is targeting
     * @return Location with the specified world, but no co-coordinates
     */
    public static Location createLocation(World world) {
        return new Location(world, 0.0, 0.0, 0.0);
    }

    /**
     * Creates a Location with the specified co-ordinates
     *
     * @param world World
     * @param x     X co-ordinate
     * @param y     Y co-ordinate
     * @param z     Z co-ordinate
     * @return Location
     */
    public static Location createLocation(World world, double x, double y, double z) {
        return new Location(world, x, y, z);
    }

    /**
     * Creates a Location with the specified co-ordinates and direction
     *
     * @param world World
     * @param x     X co-ordinate
     * @param y     Y co-ordinate
     * @param z     Z co-ordinate
     * @param yaw   Yaw absolute rotation on the x-plane, in degrees
     * @param pitch Pitch absolute rotation on the y-plane, in degrees
     * @return Location
     */
    public static Location createLocation(World world, double x, double y, double z, float yaw, float pitch) {
        return new Location(world, x, y, z, yaw, pitch);
    }

    /**
     * Creates a Location from an existing Location, and a direction
     *
     * @param location Location
     * @param yaw      Yaw absolute rotation on the x-plane, in degrees
     * @param pitch    Pitch absolute rotation on the y-plane, in degrees
     * @return Location
     */
    public static Location createLocation(Location location, float yaw, float pitch) {
        return new Location(location.getWorld(), location.getX(), location.getY(), location.getZ(), yaw, pitch);
    }

    /* World */

    public static ChunkLocation createChunkLoc() {
        return new ChunkLocation(0, 0);
    }

    public static ChunkLocation createChunkLoc(int x, int z) {
        return new ChunkLocation(x, z);
    }

    public static ChunkLocation createChunkLoc(ChunkLocation chunkLocation) {
        return new ChunkLocation(chunkLocation);
    }

    // TODO: Not thread safe
    public static WorldLoader createWorldLoader() {
        return null;
        // TODO:
        /*return new TridentWorldLoader() {
            private World world;

            @Override public World load(String world) {
                this.world = super.load(world);
                return this.world;
            }

            @Override public boolean chunkExists(World world, ChunkLocation location) {
                return world.getChunkAt(location, false) != null;
            }

            @Override public Chunk loadChunk(World world, int x, int z) {
                return world.getChunkAt(TridentFactory.createChunkLoc(x, z), true);
            }

            @Override public Chunk loadChunk(World world, ChunkLocation location) {
                return world.getChunkAt(location, true);
            }

            @Override public void saveChunk(Chunk chunk) {
                // TODO
            }
        };*/
    }

    public static World createWorld(String name) {
        return TridentFactory.createWorldLoader().load(name);
    }

    /* Utils */

    public static Vector createVector() {
        return new Vector(0, 0, 0);
    }

    public static Vector createVector(double x, double y, double z) {
        return new Vector(x, y, z);
    }

    public static Vector createVector(int x, int y, int z) {
        return new Vector(x, y, z);
    }

    public static Logger createLogger() {
        return new TridentLogger();
    }

    /* Inventory */

    public static ItemStack createItemStack(Material mat) {
        return new ItemStack(mat);
    }

    public static ItemStack createItemStack(Material mat, short quantity) {
        return new ItemStack(mat, quantity);
    }

    public static Inventory createInventory() {
        return null; // do this properly
    }

    /* Trade */

    public static ItemPair createItemPair() {
        return new ItemPair(null, null);
    }

    public static ItemPair createItemPair(ItemStack itemStack) {
        return new ItemPair(itemStack);
    }

    public static ItemPair createItemPair(ItemStack itemStack, ItemStack itemStack0) {
        return new ItemPair(itemStack, itemStack0);
    }

    /* NBT */

    public static CompoundTagBuilder<NBTBuilder> createNbtBuilder(String tagName) {
        return NBTBuilder.newBase(tagName);
    }

    public static CompoundTagBuilder<NBTBuilder> createNbtBuilder(CompoundTag base) {
        return NBTBuilder.fromBase(base);
    }

    /* Entity */

    public static Entity createEntity(World world, Class<? extends Entity> entity) {
        // TODO
        return null;
    }

    /* Board not ready */

    /* Possibly create packets */
}
