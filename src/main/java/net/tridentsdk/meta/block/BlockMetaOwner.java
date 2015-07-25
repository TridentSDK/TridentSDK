package net.tridentsdk.meta.block;

import net.tridentsdk.meta.component.MetaOwner;

/**
 * Represents a block which can hold metadata
 *
 * @author The TridentSDK Team
 */
public interface BlockMetaOwner<T> extends MetaOwner<T> {
    /**
     * Obtains the meta tag from the class type
     *
     * @param cls the class type of the meta value
     * @param <M> the meta type
     * @return the meta value
     */
    <M extends BlockMeta<T>> M valueOf(Class<M> cls);


    <M extends BlockMeta<T>> M getOrMake(Class<M> cls);

    /**
     * Commits the changes from the meta value to the block
     *
     * @param meta the metadata
     * @param <M>  the meta type
     */
    <M extends BlockMeta<T>> void commit(M meta);
}
