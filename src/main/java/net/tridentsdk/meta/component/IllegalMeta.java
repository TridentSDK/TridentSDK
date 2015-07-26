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
 * Provides a marker class which represents an illegal type metadata
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 */
public interface IllegalMeta<T> extends Meta<T> {
    /**
     * Create a new illegal meta value
     *
     * @param <T> the meta type
     * @return the new illegal meta
     */
    static <T> IllegalMeta<T> newMeta() {
        return new IllegalMeta<T>() {
        };
    }

    @Override
    default Meta<T> decode(T instance, byte[] data) {
        return null;
    }

    @Override
    default byte encode() {
        return 0;
    }

    @Override
    default Meta<T> make() {
        return null;
    }

    @Override
    default Substance[] applyTo(MetaCollection collection) {
        return new Substance[0];
    }
}
