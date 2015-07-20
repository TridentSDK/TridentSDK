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

import net.tridentsdk.base.Position;
import net.tridentsdk.base.Substance;

/**
 * Change large amounts of blocks at one time
 *
 * <p>Don't forget to call {@link #commitChanges()} to finish the operation</p>
 *
 * <p>An instance of this class can be acquired using
 * {@link net.tridentsdk.registry.Factory#newMassChange(net.tridentsdk.world.World)}</p>
 *
 * <p>Normally, block changes cause changes to immediately be sent to the client
 * which can be expensive with large amounts of changes being sent at once near a lot of players</p>
 */
public interface MassChange {
    /**
     * Add a change to make at when this change is committed
     *
     * @param x  the x of the block to set
     * @param y  the y of the block to set
     * @param z  the z of the block to set
     * @param id the block id to change the block to
     * @throws java.lang.IllegalStateException if this change has already been committed
     */
    void setBlock(int x, int y, int z, int id) throws IllegalStateException;

    /**
     * Add a change to make at when this change is committed
     *
     * @param x         the x of the block to set
     * @param y         the y of the block to set
     * @param z         the z of the block to set
     * @param substance the substance to change the block to
     * @throws java.lang.IllegalStateException if this change has already been committed
     */
    void setBlock(int x, int y, int z, Substance substance) throws IllegalStateException;

    /**
     * Add a change to make at when this change is committed
     *
     * @param x    the x of the block to set
     * @param y    the y of the block to set
     * @param z    the z of the block to set
     * @param id   the block id to change the block to
     * @param data the data value to give the new block
     * @throws java.lang.IllegalStateException if this change has already been committed
     */
    void setBlock(int x, int y, int z, int id, byte data) throws IllegalStateException;

    /**
     * Add a change to make at when this change is committed
     *
     * @param x         the x of the block to set
     * @param y         the y of the block to set
     * @param z         the z of the block to set
     * @param substance the substance to change the block to
     * @param data      the data value to give the new block
     * @throws IllegalStateException
     */
    void setBlock(int x, int y, int z, Substance substance, byte data)
            throws IllegalStateException;

    /**
     * Add a change to make at when this change is committed
     *
     * @param coords the location to set, values will be rounded
     * @param id     the id to change the block to
     * @throws IllegalArgumentException if coords are not from the same world as this change
     * @throws IllegalStateException    if this change has already been committed
     */
    void setBlock(Position coords, int id)
            throws IllegalArgumentException, IllegalStateException;

    /**
     * Add a change to make at when this change is committed
     *
     * @param coords the location to set, values will be rounded
     * @param id     the id to change the block to
     * @param data   the data value to change the block to
     * @throws IllegalArgumentException if coords are not from the same world as this change
     * @throws IllegalStateException    if this change has already been committed
     */
    void setBlock(Position coords, int id, byte data)
            throws IllegalArgumentException, IllegalStateException;

    /**
     * Add a change to make at when this change is committed
     *
     * @param coords    the location to set, values will be rounded
     * @param substance the substance to change the block to
     * @throws IllegalArgumentException if coords are not from the same world as this change
     * @throws IllegalStateException    if this change has already been committed
     */
    void setBlock(Position coords, Substance substance)
            throws IllegalArgumentException, IllegalStateException;

    /**
     * Add a change to make at when this change is committed
     *
     * @param coords    the location to set, values will be rounded
     * @param substance the id to change the block to
     * @param data      the data value to change the block to
     * @throws IllegalArgumentException if coords are not from the same world as this change
     * @throws IllegalStateException    if this change has already been committed
     */
    void setBlock(Position coords, Substance substance, byte data)
            throws IllegalArgumentException, IllegalStateException;

    /**
     * Sends the block changes to the clients and does lighting recalculations
     *
     * <p>This instance of MassChange will be rendered useless after this method is called, and should be discarded</p>
     *
     * @return Whether or not the changes were successful.
     * @throws java.lang.IllegalStateException if this change has already been committed
     */
    boolean commitChanges() throws IllegalStateException;
}
