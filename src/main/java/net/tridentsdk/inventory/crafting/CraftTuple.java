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
package net.tridentsdk.inventory.crafting;

import net.tridentsdk.inventory.Item;

/**
 * Represents a pair of an item and character used to describe the crafting recipe
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 */
public class CraftTuple {
    private char reference;
    private Item source;

    /**
     * Creates a new char-item pair
     *
     * @param reference the character that represents the item
     * @param source    the item represented by the character
     */
    public CraftTuple(char reference, Item source) {
        this.reference = reference;
        this.source = source;
    }

    /**
     * The item referenced by the char
     *
     * @return the reference item
     */
    public Item source() {
        return source;
    }

    /**
     * The character used to reference the item
     *
     * @return the reference char
     */
    public char reference() {
        return reference;
    }
}
