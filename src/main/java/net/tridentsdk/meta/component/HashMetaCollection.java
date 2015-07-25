package net.tridentsdk.meta.component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Represents a hash-based lookup mapping of meta values
 *
 * @author The TridentSDK Team
 */
class HashMetaCollection<T extends MetaOwner<T>> implements MetaCollection<T> {
    private final Map<Class<T>, Meta<T>> metaMap = new ConcurrentHashMap<>();

    @Override
    public Meta<T> get(Class<T> cls) {
        return metaMap.get(cls);
    }
}
