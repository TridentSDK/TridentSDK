package net.tridentsdk.meta.block;

import net.tridentsdk.meta.component.Meta;

/**
 * Represents a {@link Meta} object which is possessed by blocks
 *
 * @author The TridentSDK Team
 */
public interface BlockMeta<T> extends Meta<T> {
    /**
     * In BlockMeta, the data always has 2 elements: the first to represent the block damage, and
     * the second is the block data
     *
     * @param instance the data owner which the value will be applied upon
     * @param data     the data
     * @return the new meta instance
     */
    @Override
    Meta<T> decode(T instance, byte[] data);
}
