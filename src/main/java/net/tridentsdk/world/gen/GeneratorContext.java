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
     * Obtains the value that the block is given when
     * compressed in memory.
     *
     * @param id the block ID
     * @param meta the block metadata
     * @return the block's value to be stored
     */
    short build(int id, byte meta);

    /**
     * Obtains the index of the nibble array which a block
     * is placed relative to the chunk (x/z 0-15, y 0-255).
     *
     * @param x the x value
     * @param y the y value
     * @param z the z value
     * @return the nibble index
     */
    int idx(int x, int y, int z);

    /**
     * Obtains the chunk section that contains the given
     * Y value.
     *
     * @param y the y value.
     * @return the section index
     */
    int section(int y);

    /**
     * Sets the block at the coordinates relative to the
     * chunk (as explained in {@link #idx(int, int, int)}
     * to
     * the given value.
     *
     * @param x the x value
     * @param y the y value
     * @param z the z value
     * @param state the block value given by
     * {@link #build(int, byte)}
     */
    void set(int x, int y, int z, short state);
}