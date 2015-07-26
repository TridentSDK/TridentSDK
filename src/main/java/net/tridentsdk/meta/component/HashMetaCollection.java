package net.tridentsdk.meta.component;

import net.tridentsdk.util.Value;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Represents a hash-based lookup mapping of meta values
 *
 * @author The TridentSDK Team
 */
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
}
