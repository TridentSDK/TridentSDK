package net.tridentsdk.meta.component;

import com.google.common.collect.ImmutableMap;

import java.util.Map;
import java.util.function.Consumer;

/**
 * Represents an immutable view of a metacollection
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 */
public class ImmutableMetaCollection<S> implements MetaCollection<S> {
    private final Map<Class<? extends Meta<S>>, Meta<S>> map;

    private ImmutableMetaCollection(ImmutableMap<Class<? extends Meta<S>>, Meta<S>> map) {
        this.map = map;
    }

    public static <T extends MetaOwner> MetaCollection<T> copyOf(MetaCollection<T> collection) {
        ImmutableMap.Builder<Class<? extends Meta<T>>, Meta<T>> b = ImmutableMap.builder();
        collection.iterate((e) -> b.put(e.getKey(), e.getValue()));
        return new ImmutableMetaCollection<>(b.build());
    }

    @Override
    public <T extends Meta<S>> T get(Class<T> cls) {
        return (T) map.get(cls);
    }

    @Override
    public <T extends Meta<S>> void put(T meta) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T extends Meta<S>> boolean putIfAbsent(T meta) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T extends Meta<S>> void put(Class<T> cls, T meta) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T extends Meta<S>> boolean putIfAbsent(Class<T> cls, T meta) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T extends Meta<S>> boolean contains(Class<T> cls) {
        return map.containsKey(cls);
    }

    @Override
    public <T extends Meta<S>> T remove(Class<T> cls) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void iterate(Consumer<Map.Entry<Class<? extends Meta<S>>, Meta<S>>> consumer) {
        for (Map.Entry<Class<? extends Meta<S>>, Meta<S>> e : map.entrySet()) {
            consumer.accept(e);
        }
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }
}
