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

import javax.annotation.concurrent.ThreadSafe;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Represents a collection of {@link Meta} objects
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 */
@ThreadSafe
public interface MetaCollection<S> {
    /**
     * Obtains the metadata value from the component type specified
     *
     * @param cls the component type
     * @param <T> the meta value type
     * @return the meta value
     */
    <T extends Meta<S>> T get(Class<T> cls);

    /**
     * Puts the meta value into the collection, inferring the type
     *
     * @param meta the meta to put
     * @param <T>  the meta type
     */
    <T extends Meta<S>> void put(T meta);

    /**
     * Puts the meta value into the collection if it does not exist as defined by {@link #contains(Class)}
     *
     * @param meta the meta value to put
     * @param <T>  the meta type
     * @return {@code true} if the operation changed the collection, {@code false} if it had no effect
     */
    <T extends Meta<S>> boolean putIfAbsent(T meta);

    /**
     * Puts a mapping of a meta class and the meta value into the collection, replacing the existing
     * value if necessary
     *
     * @param cls  the meta type
     * @param meta the meta value
     * @param <T>  the meta value type
     */
    <T extends Meta<S>> void put(Class<T> cls, T meta);

    /**
     * Appends the meta mapping only if it does not already exist as defined by {@link #contains(Class)}.
     * Otherwise has no effect.
     *
     * @param cls  the meta type
     * @param meta the meta value
     * @param <T>  the meta value type
     * @return {@code true} if this operation changed the collection, {@code false} if it had no effect
     */
    <T extends Meta<S>> boolean putIfAbsent(Class<T> cls, T meta);

    /**
     * Obtains whether a mapping of the type provided exists within the collection
     *
     * @param cls the meta type
     * @param <T> the meta value type
     * @return {@code true} if it exists, {@code false} if it doesn't
     */
    <T extends Meta<S>> boolean contains(Class<T> cls);

    /**
     * Removes the meta tag from this collection
     *
     * @param cls the meta type to remove
     * @param <T> the meta value type
     * @return the meta value associated with the type, or {@code null} if this operation had no effect
     */
    <T extends Meta<S>> T remove(Class<T> cls);

    /**
     * Iterates the collection
     *
     * @param consumer the iteration function
     */
    void iterate(Consumer<Map.Entry<Class<? extends Meta<S>>, Meta<S>>> consumer);

    /**
     * Clear the meta collection of meta values
     */
    void clear();
}
