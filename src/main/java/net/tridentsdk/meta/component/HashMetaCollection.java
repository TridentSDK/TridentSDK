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

import net.tridentsdk.util.Value;

import javax.annotation.concurrent.ThreadSafe;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/**
 * Represents a hash-based lookup mapping of meta values
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 */
@ThreadSafe
class HashMetaCollection<S> implements MetaCollection<S> {
    private final Map<Class<? extends Meta<S>>, Meta<S>> metaMap = new ConcurrentHashMap<>();

    @Override
    public <T extends Meta<S>> T get(Class<T> cls) {
        return (T) metaMap.get(cls);
    }

    @Override
    public <T extends Meta<S>> void put(T meta) {
        put((Class<T>) meta.getClass(), meta);
    }

    @Override
    public <T extends Meta<S>> boolean putIfAbsent(T meta) {
        return putIfAbsent((Class<T>) meta.getClass(), meta);
    }

    @Override
    public <T extends Meta<S>> void put(Class<T> cls, T meta) {
        metaMap.put(cls, meta);
    }

    @Override
    public <T extends Meta<S>> boolean putIfAbsent(Class<T> cls, T meta) {
        Value<Boolean> needsCompute = Value.of(false);
        metaMap.computeIfAbsent(cls, (k) -> {
            needsCompute.set(true);
            return meta;
        });
        return needsCompute.get();
    }

    @Override
    public <T extends Meta<S>> boolean contains(Class<T> cls) {
        return metaMap.containsKey(cls);
    }

    @Override
    public <T extends Meta<S>> T remove(Class<T> cls) {
        return (T) metaMap.remove(cls);
    }

    @Override
    public void iterate(Consumer<Map.Entry<Class<? extends Meta<S>>, Meta<S>>> consumer) {
        for (Map.Entry<Class<? extends Meta<S>>, Meta<S>> entry : metaMap.entrySet()) {
            consumer.accept(entry);
        }
    }

    @Override
    public void clear() {
        this.metaMap.clear();
    }
}
