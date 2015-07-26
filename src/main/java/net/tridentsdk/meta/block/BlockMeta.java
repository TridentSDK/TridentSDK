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

/**
 * Represents a {@link Meta} object which is possessed by blocks
 *
 * @author The TridentSDK Team
 */
public interface BlockMeta<T> extends Meta<T> {
    /**
     * In BlockMeta, the data always has 2 elements: the first to represent the block damage, and
     * the second is the block data
     *
     * @param instance the data owner which the value will be applied upon
     * @param data     the data
     * @return the new meta instance
     */
    @Override
    Meta<T> decode(T instance, byte[] data);
}
