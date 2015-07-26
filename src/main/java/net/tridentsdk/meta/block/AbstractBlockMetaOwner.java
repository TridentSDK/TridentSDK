/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2014 The TridentSDK Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
