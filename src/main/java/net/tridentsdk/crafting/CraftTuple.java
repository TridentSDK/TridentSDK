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
package net.tridentsdk.crafting;

import net.tridentsdk.inventory.Item;

/**
 * TODO Write Description
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 */
public class CraftTuple {

    private char reference;
    private Item source;

    public CraftTuple(char reference, Item source) {
        this.reference = reference;
        this.source = source;
    }

    public Item source() {
        return source;
    }

    public char reference() {
        return reference;
    }
}
