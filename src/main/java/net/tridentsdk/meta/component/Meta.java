package net.tridentsdk.meta.component;

import net.tridentsdk.base.Substance;

/**
 * Represents an abstract metadata value which can be possessed by items, entities, blocks, among others
 *
 * @author The TridentSDK Team
 */
public interface Meta<T> {
    /**
     * Creates a new meta value containing the decoded data provided
     *
     * @param instance the data owner which the value will be applied upon
     * @param data     the data
     */
    Meta<T> decode(T instance, byte[] data);

    /**
     * Make a new instance of this meta object
     *
     * @return the new instance
     */
    Meta<T> make();

    /**
     * Invoked to register the meta tag
     *
     * @param collection the collection which will be used to hold this meta type
     * @return the substances to apply to
     */
    Substance[] applyTo(MetaCollection collection);
}
