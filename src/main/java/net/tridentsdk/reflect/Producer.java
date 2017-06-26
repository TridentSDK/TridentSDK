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
package net.tridentsdk.reflect;

/**
 * Produces an object for a particular injection class
 *
 * @author The TridentSDK Team
 * @since 0.3-alpha-DP
 */
@FunctionalInterface
public interface Producer<T> {
    /**
     * Produces an empty inject object
     *
     * @return the produced object
     */
    T produce();

    /**
     * Produce an object for an {@link Inject#meta()} class provided by the annotation
     *
     * <p>This is for producing different implementations of the same superclass</p>
     *
     * <p>The best practice is throwing an {@link java.lang.IllegalArgumentException} if you do not have more than one
     * implementation</p>
     *
     * @param metadata the provided class in the {@link Inject} annotation
     * @return the produced object
     */
    default T produce(Class<?> metadata) {
        return produce();
    }
}
