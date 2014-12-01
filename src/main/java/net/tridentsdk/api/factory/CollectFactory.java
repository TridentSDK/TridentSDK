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
