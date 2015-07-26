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

/**
 * Represents an object which carries metadata
 *
 * @author The TridentSDK Team
 */
public interface MetaOwner<T> {
    /**
     * Obtains the meta tag from the class type
     *
     * @param cls the class type of the meta value
     * @param <M> the meta type
     * @return the meta value
     */
    <M extends T> M valueOf(Class<M> cls);

    /**
     * Creates a new metadata value for the type specified if this block supports it, and it is not already
     * created in the block
     *
     * @param cls the meta type
     * @param <M> the meta
     * @return the meta value, or {@code null} if it could not be made
     */
    <M extends T> M makeIfEmpty(Class<M> cls);

    /**
     * Commits the changes from the meta value to the block
     *
     * @param meta    the metadata
     * @param replace {@code true} to commit anyways if the data mapping already exists
     * @param <M>     the meta type
     * @return {@code true} if the change took effect
     */
    <M extends T> boolean commit(M meta, boolean replace);
}
