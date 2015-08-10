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
package net.tridentsdk.meta.item;

import net.tridentsdk.meta.nbt.NBTSerializable;

/**
 * This type represents additional metadata attached to an Item.
 */
public interface ItemMeta extends NBTSerializable {
    /**
     * Gets the display properties of this item
     * @return Display properties of this item
     */
    ItemDisplayProperties displayProperties();

    /**
     * Sets the display properties of this item
     * @param properties the new display properties
     */
    void setDisplayProperties(ItemDisplayProperties properties);
}
