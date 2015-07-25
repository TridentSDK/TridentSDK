package net.tridentsdk.meta.component;

import net.tridentsdk.meta.block.BlockMetaOwner;

/**
 * Allows Factory access to metadata components
 *
 * @author The TridentSDK Team
 */
public final class MetaFactory {
    /**
     * Creates a new meta collection
     *
     * @param <T> the meta collection type
     * @return the new meta collection
     */
    public static <T extends BlockMetaOwner<T>> MetaCollection<T> newCollection() {
        return new HashMetaCollection<>();
    }
}
