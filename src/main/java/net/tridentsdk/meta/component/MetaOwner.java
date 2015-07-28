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
 * @since 0.4-alpha
 */
public interface MetaOwner<T> {
    /**
     * Obtains the meta tag from the class type
     *
     * @param cls the class type of the meta value
     * @param <M> the meta type
     * @return the meta value, or {@code null} if the meta is not owned
     */
    <M extends T> M obtainMeta(Class<M> cls);

    /**
     * Creates a new metadata value for the type specified if this block supports it, and it is not already
     * created in the block
     *
     * @param cls the meta type
     * @param <M> the meta
     * @return the meta value, or {@code null} if it could not be made
     */
    <M extends T> M newMetaIfNull(Class<M> cls);

    /**
     * Gets all of the metadata values currently owned by this meta owner
     *
     * @return the currently owned collection of meta values
     */
    <M extends MetaOwner> MetaCollection<M> ownedMeta();

    /**
     * Applies the specified metas to the owner, replacing the previous value if present
     *
     * @param meta the metas to apply
     * @param <M> the meta type
     */
    <M extends T> void applyMeta(M... meta);

    /**
     * Commits the changes from the meta value to the block
     *
     * @param meta    the metadata
     * @param replace {@code true} to apply meta anyways if the data mapping already exists
     * @param <M>     the meta type
     * @return {@code true} if the <em>all</em> of the meta changes took effect
     */
    <M extends T> boolean applyMeta(boolean replace, M... meta);

    /**
     * Removes all meta values and sets the block meta to {@code (byte) 0}
     */
    void clearMeta();
}
