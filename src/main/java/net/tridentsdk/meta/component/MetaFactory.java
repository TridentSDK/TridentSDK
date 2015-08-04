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
package net.tridentsdk.meta.component;

import net.tridentsdk.base.Block;
import net.tridentsdk.base.Substance;
import net.tridentsdk.registry.Registered;
import net.tridentsdk.util.Value;

import javax.annotation.concurrent.ThreadSafe;

/**
 * Allows Factory access to metadata components
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 */
@ThreadSafe
public final class MetaFactory {
    private static volatile MetaProvider provider;

    /**
     * Creates a new meta collection
     *
     * @return the new meta collection
     */
    public static <T extends MetaOwner> MetaCollection<T> newCollection() {
        return new HashMetaCollection<>();
    }

    /**
     * @see MetaProvider#decode(Block, net.tridentsdk.util.Value, byte[], net.tridentsdk.util.Value)
     */
    public static boolean decode(Block block, Value<Substance> substance, byte[] data, Value<Byte> result) {
        ensureInit();
        return provider.decode(block, substance, data, result);
    }

    /**
     * Obtains a new instance of a meta value
     *
     * @param type the meta type class
     * @param <S>  the meta owner type
     * @param <T>  the meta value type
     * @return the new meta value
     */
    public static <S, T extends Meta<S>> T newValue(Class<T> type) {
        ensureInit();
        return provider.provide(type);
    }

    /**
     * Registers the meta value into the server meta registry
     *
     * @param meta the meta
     */
    public static void register(Meta meta) {
        ensureInit();
        provider.register(meta);
    }

    private static void ensureInit() {
        if (provider == null) {
            // hacky initializations
            provider = Registered.impl().meta();
        }
    }
}
