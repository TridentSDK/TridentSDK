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

import jdk.nashorn.internal.ir.annotations.Immutable;

import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

/**
 * A pair of objects
 *
 * <p>This class has no constructor. Rather, it has several different factory methods that instantiate
 * internal implementations of the Pair. For most intents and purposes, one would use the {@code mutable[XXX]()}
 * factory methods, depending on the context of usage.</p>
 *
 * @author The TridentSDK Team
 */
public abstract class Pair<K, V> {
    /**
     * Creates a new mutable pair
     *
     * @param <K> the key type
     * @param <V> the value type
     * @return the new mutable pair
     */
    public static <K, V> Pair<K, V> mutable() {
        return new MutablePair<>(null, null);
    }

    /**
     * Creates a new mutable pair with the given start arguments
     *
     * @param key   the key initial value
     * @param value the value initial value
     * @param <K>   the key type
     * @param <V>   the value type
     * @return the new mutable pair
     */
    public static <K, V> Pair<K, V> mutable(K key, V value) {
        return new MutablePair<>(key, value);
    }

    /**
     * Creates a new thread safe pair
     *
     * @param <K> the key type
     * @param <V> the value type
     * @return the new thread safe pair
     */
    public static <K, V> Pair<K, V> threadSafe() {
        return new VolatilePair<>(null, null);
    }

    /**
     * Creates a new thread safe pair with the given start arguments
     *
     * @param key   the key initial value
     * @param value the value initial value
     * @param <K>   the key type
     * @param <V>   the value type
     * @return the new thread safe pair
     */
    public static <K, V> Pair<K, V> threadSafe(K key, V value) {
        return new VolatilePair<>(key, value);
    }

    /**
     * Creates a new immutable pair with the given arguments
     *
     * @param key   the key value
     * @param value the value value
     * @param <K>   the key type
     * @param <V>   the value type
     * @return the new immutable pair
     */
    public static <K, V> Pair<K, V> immutable(K key, V value) {
        return new ImmutablePair<>(key, value);
    }

    private final boolean immutable;

    Pair(boolean immutable) {
        this.immutable = immutable;
    }

    /**
     * Obtains the key in this pair
     *
     * @return the key
     */
    public abstract K key();

    /**
     * Obtains the value in this pair
     *
     * @return the value
     */
    public abstract V val();

    /**
     * Processes the pair
     *
     * @param consumer the key-value reader
     */
    public void read(BiConsumer<K, V> consumer) {
        consumer.accept(key(), val());
    }

    /**
     * Reads both key and value and returns a result
     *
     * @param func the reader
     * @param <T>  the type to be returned
     * @return the function's result
     */
    public <T> T read(BiFunction<K, V, T> func) {
        return func.apply(key(), val());
    }

    /**
     * Copies the key and value of the current pair to another pair
     *
     * @param pair the pair to transfer to
     * @throws UnsupportedOperationException if the given pair is immutable
     */
    public void transfer(Pair<K, V> pair) {
        pair.set(key(), val());
    }

    /**
     * Sets the key of this pair
     *
     * @param key the key to set
     * @throws UnsupportedOperationException if the pair is immutable
     */
    public abstract void setKey(K key);

    /**
     * Sets the value of this pair
     *
     * @param val the value to set
     * @throws UnsupportedOperationException if the pair is immutable
     */
    public abstract void setVal(V val);

    /**
     * Sets both the key and values of this pair
     *
     * @param key the key to set
     * @param val the value to set
     * @throws UnsupportedOperationException if the pair is immutable
     */
    public void set(K key, V val) {
        setKey(key);
        setVal(val);
    }

    /**
     * Checks this pair in order to ensure whether it is immutable or not
     *
     * @return {@code true} if the pair is immutable, {@code false} if not
     */
    public boolean isImmutable() {
        return this.immutable;
    }

    @ThreadSafe
    private static class VolatilePair<K, V> extends Pair<K, V> {
        private volatile K key;
        private volatile V value;

        public VolatilePair(K key, V value) {
            super(false);
            this.key = key;
            this.value = value;
        }

        @Override
        public K key() {
            return key;
        }

        @Override
        public V val() {
            return value;
        }

        @Override
        public void setKey(K key) {
            this.key = key;
        }

        @Override
        public void setVal(V val) {
            this.value = val;
        }
    }

    @NotThreadSafe
    private static class MutablePair<K, V> extends Pair<K, V> {
        private K key;
        private V value;

        public MutablePair(K key, V value) {
            super(false);
            this.key = key;
            this.value = value;
        }

        @Override
        public K key() {
            return key;
        }

        @Override
        public V val() {
            return value;
        }

        @Override
        public void setKey(K key) {
            this.key = key;
        }

        @Override
        public void setVal(V val) {
            this.value = val;
        }
    }

    @Immutable
    private static class ImmutablePair<K, V> extends Pair<K, V> {
        private final K key;
        private final V value;

        public ImmutablePair(K key, V value) {
            super(true);
            this.key = key;
            this.value = value;
        }

        @Override
        public K key() {
            return key;
        }

        @Override
        public V val() {
            return value;
        }

        @Override
        public void setKey(K key) {
            throw new UnsupportedOperationException("Immutable pair");
        }

        @Override
        public void setVal(V val) {
            throw new UnsupportedOperationException("Immutable pair");
        }
    }
}