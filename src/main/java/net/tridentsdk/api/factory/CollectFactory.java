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
package net.tridentsdk.api.factory;

import net.tridentsdk.api.threads.ConcurrentCache;

import java.util.concurrent.ConcurrentMap;

public abstract class CollectFactory {
    /**
     * Creates a netty ConcurrentHashMapV8 to provide the fastest platform map
     *
     * @param <K> the key type
     * @param <V> the value type
     * @return a new concurrent hashtable reimplemented backwards
     */
    public abstract <K, V> ConcurrentMap<K, V> createMap();

    /**
     * Creates a new concurrent cache facility
     *
     * @param <K> the key type
     * @param <V> the value type
     * @return a ConcurrentMap backed cache for storing values
     */
    public <K, V> ConcurrentCache<K, V> createCache() {
        return new ConcurrentCache<>();
    }
}
