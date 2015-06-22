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

package net.tridentsdk.factory;

import javax.annotation.concurrent.ThreadSafe;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Collections that are able to be accessed
 *
 * @author The TridentSDK Team
 */
@ThreadSafe
public class CollectFactory {
    /**
     * Creates a netty ConcurrentHashMap to provide the fastest platform map
     *
     * @param <K> the key type
     * @param <V> the value type
     * @return a new concurrent hashtable reimplemented backwards
     */
    public <K, V> ConcurrentMap<K, V> createMap() {
        return new ConcurrentHashMap<>();
    }

    public <E> Set<E> createSet() {
        return Collections.newSetFromMap(this.<E, Boolean>createMap());
    }
}
