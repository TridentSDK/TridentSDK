package net.tridentsdk.meta.component;

/**
 * Represents a collection of {@link Meta} objects
 *
 * @author The TridentSDK Team
 */
public interface MetaCollection<T extends MetaOwner<T>> {
    /**
     * Obtains the met
     *
     * @param cls
     * @return
     */
    Meta<T> get(Class<T> cls);
}
