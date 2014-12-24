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

import net.tridentsdk.factory.Factories;

import javax.annotation.concurrent.ThreadSafe;
import java.util.*;
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
    private final Object PLACE_HOLDER = new Object();
    private final ConcurrentMap<K, Object> cache = Factories.collect().createMap();

    /**
     * Retrieves the key in the cache, or adds the return value of the callable provided
     *
     * @param k        the key to retrieve the value from, or assign it to
     * @param callable the result of which to assign the key a value if the key is not in the cache
     * @return the return value of the callable
     */
    public V retrieve(K k, Callable<V> callable) {
        Object value = cache.get(k);
        if (value == null) {
            V v = null;
            value = cache.putIfAbsent(k, PLACE_HOLDER);
            if (value == null) {
                try {
                    v = callable.call();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                cache.replace(k, PLACE_HOLDER, v);
                value = v;
            }
        }

        return (V) value;
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
        Collection<V> list = new ArrayList<>();
        for (Object v : this.cache.values())
            list.add((V) v);

        return list;
    }

    /**
     * Removes the entry assigned to the specified key
     *
     * @param k the key to remove the entry for
     * @return the old value assigned to the key, otherwise, {@code null} if not in the cache
     */
    public V remove(K k) {
        Object val = this.cache.get(k);

        if (val == null)
            return null;

        this.cache.remove(k);
        return (V) val;
    }

    /**
     * Returns the backing map of this cache
     *
     * @return the underlying map
     */
    public Set<Map.Entry<K, V>> entries() {
        Set<Map.Entry<K, V>> entries = new HashSet<>();
        for (Map.Entry<K, Object> entry : cache.entrySet())
            entries.add(new AbstractMap.SimpleEntry<>(entry.getKey(), (V) entry.getValue()));
        return entries;
    }
}