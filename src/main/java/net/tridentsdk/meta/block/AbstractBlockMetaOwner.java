package net.tridentsdk.meta.block;

import net.tridentsdk.meta.component.MetaCollection;
import net.tridentsdk.meta.component.MetaFactory;
import net.tridentsdk.meta.component.MetaOwner;

/**
 * Implementation of a block meta owner which provides access to the collection factory
 *
 * @author The TridentSDK Team
 */
public abstract class AbstractBlockMetaOwner<T extends MetaOwner<T>> implements BlockMetaOwner<T> {
    private final MetaCollection<T> collection = collect();

    /**
     * Create the metadata storage collection
     *
     * @return the new collection
     */
    protected abstract MetaCollection<T> collect();

    @Override
    public <M extends BlockMeta<T>> M valueOf(Class<M> cls) {
        return collection.get(cls);
    }

    @Override
    public <M extends BlockMeta<T>> M makeIfEmpty(Class<M> cls) {
        M meta = valueOf(cls);
        if (meta == null) {
            meta = MetaFactory.newValue(cls);

            collection.put(cls, meta);
        }

        return meta;
    }

    @Override
    public <M extends BlockMeta<T>> boolean commit(M meta, boolean replace) {
        if (replace) {
            return collection.putIfAbsent(meta);
        }

        collection.put(meta);
        return true;
    }
}
