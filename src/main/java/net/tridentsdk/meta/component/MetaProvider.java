package net.tridentsdk.meta.component;

/**
 * Provides metadata values
 *
 * @author The TridentSDK Team
 */
public interface MetaProvider {
    /**
     * Obtains a new instance of a meta value
     *
     * @param cls the meta type class
     * @param <S> the meta owner type
     * @param <T> the meta value type
     * @return the new meta value
     */
    <S, T extends Meta<S>> T provide(Class<T> cls);

    /**
     * Puts the meta value into the server meta registry
     *
     * @param meta the meta
     */
    void register(Meta meta);
}
