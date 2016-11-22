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
package net.tridentsdk.world.gen;

import net.tridentsdk.base.Substance;

/**
 * This class represents the generation context in which a
 * world generator creates chunks and features.
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
public interface GeneratorContext {
    /**
     * Obtains the next random long value that this context
     * provides.
     *
     * @return the next random long
     */
    long nextLong();

    /**
     * Obtains the next random long value that this context
     * provides within the given bound.
     *
     * @param max the largest number, non-inclusive
     * @return the next long within the given bound
     */
    long nextLong(long max);

    /**
     * Obtains the next random integer that this context
     * provides.
     *
     * @return the next random integer
     */
    int nextInt();

    /**
     * Obtains the next random integer value that this
     * context provides within the given bound.
     *
     * @param max the largest number, non-inclusive
     * @return the next integer within the given bound
     */
    int nextInt(int max);

    /**
     * Obtains the seed that is used to generate the chunk
     * which uses this context.
     *
     * @return the seed used to generate the chunk
     */
    long seed();

    /**
     * Obtains the height of the max Y value at the given
     * x and z coordinates.
     *
     * @return the max generated Y value
     */
    int maxHeight(int x, int z);

    /**
     * Sets the block at the coordinates relative to the
     * chunk (0-15) to the given block substance and
     * metadata.
     *
     * @param x the x value
     * @param y the y value
     * @param z the z value
     * @param substance the substance of the block
     * @param meta the block meta
     */
    void set(int x, int y, int z, Substance substance, byte meta);

    /**
     * Sets the block at the coordinates relative to the
     * chunk (0-15) to the given block substance and
     * no metadata.
     *
     * @param x the x value
     * @param y the y value
     * @param z the z value
     * @param substance the substance of the block
     */
    void set(int x, int y, int z, Substance substance);

    /**
     * Sets the block at the coordinates relative to the
     * chunk (0-15) to the given block ID and metadata.
     *
     * @param x the x value
     * @param y the y value
     * @param z the z value
     * @param id the block ID
     * @param meta the block meta
     */
    void set(int x, int y, int z, int id, byte meta);

    /**
     * Schedules the given generation task to be run when
     * the command is given for the generator to proceed.
     *
     * @param r the task to run
     */
    void run(Runnable r);
}