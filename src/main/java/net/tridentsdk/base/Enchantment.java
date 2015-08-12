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
package net.tridentsdk.base;

import java.util.Map;

import com.google.common.collect.Maps;

public enum Enchantment {

    ;

    private static final Map<Short, Enchantment> enchsById = Maps.newConcurrentMap();
    private static final Map<String, Enchantment> enchsByName = Maps.newConcurrentMap();

    static {

        for (Enchantment ench : values()) {
            if (!enchsById.containsKey(ench.id)) {
                enchsById.put(ench.id, ench);
            }
            if (!enchsByName.containsKey(ench.name.toLowerCase())) {
                enchsByName.put(ench.name.toLowerCase(), ench);
            }
        }
    }

    public static Enchantment fromId(short id) {
        return Enchantment.enchsById.get(id);
    }

    public static Enchantment fromName(String name) {
        return Enchantment.enchsByName.get(name.toLowerCase());
    }

    private final short id;
    private final String name;

    // accepts int because you don't need to cast it in calls
    private Enchantment(int id, String name) {
        this.id = (short) id;
        this.name = name;
    }

    public short id() {
        return this.id;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
