package net.tridentsdk.meta.component;

/**
 * Represents a collection of {@link Meta} objects
 *
 * @author The TridentSDK Team
 */
public interface MetaCollection<S> {
    /**
     * Obtains the metadata value from the component type specified
     *
     * @param cls the component type
     * @param <T> the meta value type
     * @return the meta value
     */
    <T extends Meta<S>> T get(Class<T> cls);

    /**
     * Puts the meta value into the collection, inferring the type
     *
     * @param meta the meta to put
     * @param <T>  the meta type
     */
    <T extends Meta<S>> void put(T meta);

    /**
     * Puts the meta value into the collection if it does not exist as defined by {@link #contains(Class)}
     *
     * @param meta the meta value to put
     * @param <T>  the meta type
     * @return {@code true} if the operation changed the collection, {@code false} if it had no effect
     */
    <T extends Meta<S>> boolean putIfAbsent(T meta);

    /**
     * Puts a mapping of a meta class and the meta value into the collection, replacing the existing
     * value if necessary
     *
     * @param cls  the meta type
     * @param meta the meta value
     * @param <T>  the meta value type
     */
    <T extends Meta<S>> void put(Class<T> cls, T meta);

    /**
     * Appends the meta mapping only if it does not already exist as defined by {@link #contains(Class)}.
     * Otherwise has no effect.
     *
     * @param cls  the meta type
     * @param meta the meta value
     * @param <T>  the meta value type
     * @return {@code true} if this operation changed the collection, {@code false} if it had no effect
     */
    <T extends Meta<S>> boolean putIfAbsent(Class<T> cls, T meta);

    /**
     * Obtains whether a mapping of the type provided exists within the collection
     *
     * @param cls the meta type
     * @param <T> the meta value type
     * @return {@code true} if it exists, {@code false} if it doesn't
     */
    <T extends Meta<S>> boolean contains(Class<T> cls);

    /**
     * Removes the meta tag from this collection
     *
     * @param cls the meta type to remove
     * @param <T> the meta value type
     * @return the meta value associated with the type, or {@code null} if this operation had no effect
     */
    <T extends Meta<S>> T remove(Class<T> cls);
}
