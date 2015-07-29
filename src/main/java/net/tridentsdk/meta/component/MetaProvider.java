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
import net.tridentsdk.util.Value;

import javax.annotation.concurrent.ThreadSafe;

/**
 * Provides metadata values
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 */
@ThreadSafe
public interface MetaProvider {
    /**
     * Obtains a new instance of a meta value
     *
     * @param cls the meta type class
     * @param <S> the meta owner type
     * @param <T> the meta value type
     * @return the new meta value
     */
    <S, T extends Meta<S>> T provide(Class<T> cls);

    /**
     * Puts the meta value into the server meta registry
     *
     * @param meta the meta
     */
    void register(Meta meta);

    /**
     * Decodes and applies the byte data to the block as specified in
     * {@link net.tridentsdk.meta.block.BlockMeta#decodeMeta(Object, byte[])}
     *
     * @param block the block
     * @param substance
     *@param data  the data
     * @param result  @return {@code true} if the block can be placed, {@code false} if otherwise
     */
    boolean decode(Block block, Substance substance, byte[] data, Value<Byte> result);
}
