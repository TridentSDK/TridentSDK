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
package net.tridentsdk.util;

import javax.annotation.concurrent.ThreadSafe;

/**
 * Holds a value to cheat inner class finalization requirements
 *
 * @author The TridentSDK Team
 */
@ThreadSafe
public final class Value<T> {
    private volatile T value;

    private Value(T t) {
        this.value = t;
    }

    /**
     * Creates a new value which has the initial state set to the provided object
     *
     * @param t   the value to hold
     * @param <T> the value type
     * @return the new value holder
     */
    public static <T> Value<T> of(T t) {
        return new Value<>(t);
    }

    /**
     * Sets the value
     *
     * @param t the value
     */
    public void set(T t) {
        this.value = t;
    }

    /**
     * Gets the value
     *
     * @return the value
     */
    public T get() {
        return this.value;
    }
}
