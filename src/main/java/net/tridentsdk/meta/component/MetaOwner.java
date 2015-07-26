package net.tridentsdk.meta.component;

/**
 * Represents an object which carries metadata
 *
 * @author The TridentSDK Team
 */
public interface MetaOwner<T> {
    /**
     * Obtains the meta tag from the class type
     *
     * @param cls the class type of the meta value
     * @param <M> the meta type
     * @return the meta value
     */
    <M extends T> M valueOf(Class<M> cls);

    /**
     * Creates a new metadata value for the type specified if this block supports it, and it is not already
     * created in the block
     *
     * @param cls the meta type
     * @param <M> the meta
     * @return the meta value, or {@code null} if it could not be made
     */
    <M extends T> M makeIfEmpty(Class<M> cls);

    /**
     * Commits the changes from the meta value to the block
     *
     * @param meta    the metadata
     * @param replace {@code true} to commit anyways if the data mapping already exists
     * @param <M>     the meta type
     * @return {@code true} if the change took effect
     */
    <M extends T> boolean commit(M meta, boolean replace);
}
