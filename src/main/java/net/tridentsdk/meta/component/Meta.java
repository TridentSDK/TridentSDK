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
package net.tridentsdk.meta.component;

import net.tridentsdk.base.Substance;

/**
 * Represents an abstract metadata value which can be possessed by items, entities, blocks, among others
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 */
public interface Meta<T> {
    /**
     * Creates a new meta value containing the decoded data provided
     *
     * @param instance the data owner which the value will be applied upon
     * @param data     the data
     */
    Meta<T> decodeMeta(T instance, byte[] data);

    /**
     * Transforms the data into a byte which can be represented in a block data byte
     *
     * @return the byte data
     */
    byte[] encodeMeta();

    /**
     * Make a new instance of this meta object
     *
     * @return the new instance
     */
    Meta<T> make();

    /**
     * Invoked to register the meta tag
     *
     * @param collection the collection which will be used to hold this meta type
     * @return the substances to apply to
     */
    Substance[] applyTo(MetaCollection collection);
}
