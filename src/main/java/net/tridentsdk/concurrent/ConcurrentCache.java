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

package net.tridentsdk.concurrent;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import net.tridentsdk.factory.Factories;
import net.tridentsdk.util.TridentLogger;

import javax.annotation.concurrent.ThreadSafe;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;

/**
 * Cache wrapping {@link java.util.concurrent.ConcurrentHashMap}
 *
 * @param <K> the key type
 * @param <V> the value type
 * @author The TridentSDK Team
 */
@ThreadSafe
public class ConcurrentCache<K, V> {
    private final ConcurrentMap<K, HeldValueLatch<V>> cache = Factories.collect().createMap();

    private ConcurrentCache() {
    }

    /**
     * Creates a new cache
     *
     * @param <K> the key type
     * @param <V> the value type
     * @return a new cache
     */
    public static <K, V> ConcurrentCache<K, V> create() {
        return new ConcurrentCache<>();
    }

    /**
     * Retrieves the key in the cache, or adds the return value of the callable provided
     *
     * @param k        the key to retrieve the value from, or assign it to
     * @param callable the result of which to assign the key a value if the key is not in the cache
     * @return the return value of the callable
     */
    public V retrieve(K k, Callable<V> callable) {
        while (true) {
            HeldValueLatch<V> value = cache.get(k);
            if (value == null) {
                HeldValueLatch<V> latch = HeldValueLatch.create();
                value = cache.putIfAbsent(k, latch);
                if (value == null) {
                    value = latch;
                    try {
                        value.countDown(callable.call());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            try {
                return value.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Retrieves the value associated with the key without removing or placing anything if the operation fails or
     * succeeds
     *
     * @param k the key to retrieve the value with
     * @return the value associated with the key, or {@code null} if none
     */
    public V retrieve(K k) {
        try {
            HeldValueLatch<V> latch = cache.get(k);
            if (latch == null)
                return null;
            return latch.await();
        } catch (InterruptedException e) {
            TridentLogger.error(e);
        }

        // Should never ever reach here
        return null;
    }

    /**
     * Removes the entry assigned to the specified key
     *
     * @param k the key to remove the entry for
     * @return the old value assigned to the key, otherwise, {@code null} if not in the cache
     */
    public V remove(K k) {
        HeldValueLatch<V> val = this.cache.get(k);

        if (val == null)
            return null;

        this.cache.remove(k);
        try {
            return val.await();
        } catch (InterruptedException e) {
            TridentLogger.error(e);
            return null;
        }
    }

    public Set<K> keys() {
        return this.cache.keySet();
    }

    /**
     * The values of the cache
     *
     * @return the cache values
     */
    public Collection<V> values() {
        Collection<V> list = Lists.newArrayList();
        for (HeldValueLatch<V> v : this.cache.values()) {
            try {
                list.add(v.await());
            } catch (InterruptedException e) {
                TridentLogger.error(e);
            }
        }

        return list;
    }

    /**
     * Returns the backing map of this cache
     *
     * @return the underlying map
     */
    public Set<Map.Entry<K, V>> entries() {
        Set<Map.Entry<K, V>> entries = Sets.newHashSet();
        for (Map.Entry<K, HeldValueLatch<V>> entry : cache.entrySet()) {
            try {
                entries.add(new AbstractMap.SimpleEntry<>(entry.getKey(), entry.getValue().await()));
            } catch (InterruptedException e) {
                TridentLogger.error(e);
            }
        }
        return entries;
    }

    /**
     * Clears the mappings from this cache
     */
    public void clear() {
        cache.clear();
    }
}