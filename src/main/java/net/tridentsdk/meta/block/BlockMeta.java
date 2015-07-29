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
package net.tridentsdk.meta.block;

import net.tridentsdk.meta.component.Meta;

import javax.annotation.concurrent.ThreadSafe;

/**
 * Represents a {@link Meta} object which is possessed by blocks
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 */
@ThreadSafe
public interface BlockMeta<T> extends Meta<T> {
    /**
     * In BlockMeta, the data always has 8 elements laid out like so:
     * <ul>
     *     <li>0-1: Player yaw * 10</li>
     *     <li>2: Block direction</li>
     *     <li>3: Cursor x</li>
     *     <li>4: Cursor y</li>
     *     <li>5: Cursor z</li>
     *     <li>6-7: item damage</li>
     * </ul>
     *
     * @param instance the data owner which the value will be applied upon
     * @param data     the data
     * @return the new meta instance
     */
    @Override
    default Meta<T> decodeMeta(T instance, byte[] data) {
        return decode(instance, ByteArray.readShort(data[0], data[1]) / 10F, data[2], data[3], data[4], data[5],
                ByteArray.readShort(data[6], data[7]));
    }

    /**
     * In blocks, only one meta field byte is needed
     */
    @Override
    default byte[] encodeMeta() {
        return new byte[]{encode()};
    }

    /**
     * Used by the metadata compiler to order the specific metadata value before this one
     * to preserve bit order
     *
     * @return the classes to have dependencies on
     */
    default Class[] dependencies() {
        return new Class[0];
    }

    /**
     * Encodes the block metadata into a single byte, which is then combined with the rest of the metadata
     *
     * @return the byte data
     */
    byte encode();

    /**
     * Decodes the block meta
     *
     * @param instance    the block instance
     * @param yaw         the player yaw
     * @param direction   the block direction
     * @param cx          the cursor x
     * @param cy          the cursor y
     * @param cz          the cursor z
     * @param damageValue the item damage
     * @return the new meta instance
     */
    Meta<T> decode(T instance, float yaw, byte direction, byte cx, byte cy, byte cz, short damageValue);
}
