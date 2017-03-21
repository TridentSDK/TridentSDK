/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2017 The TridentSDK Team
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
package net.tridentsdk.meta;

import net.tridentsdk.meta.nbt.TagCompound;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

/**
 * A class representing an item's extra metadata such as
 * enchantments, attributes, item-specific data such as
 * potion and skull metas, etc...
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
@ThreadSafe
public class ItemMeta {
    /**
     * The NBT data which contains modifications made to
     * this item.
     */
    private final TagCompound nbt = new TagCompound();

    /**
     * Obtains the NBT data of this particular item.
     *
     * @return the NBT data, or {@code null} if the item was
     * not modified
     */
    @Nullable
    public TagCompound toNbt() {
        if (this.nbt.isEmpty()) {
            return null;
        }

        return this.nbt;
    }
}