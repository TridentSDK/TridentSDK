/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2016 The TridentSDK Team
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

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
public enum ZombieType {

    NORMAL(0),
    VILLAGER_FARMER(1),
    VILLAGER_LIBRARIAN(2),
    VILLAGER_PRIEST(3),
    VILLAGER_BLACKSMITH(4),
    VILLAGER_BUTCHER(5),
    HUSK(6);

    @Getter
    private final int data;

    @Getter
    private final boolean villager;

    private ZombieType(int data) {
        this.data = data;
        this.villager = data >= 1 && data <= 5;
    }

    private static final Map<Integer, ZombieType> dataToType = new HashMap<>();

    public static ZombieType of(int data) {
        ZombieType type = dataToType.get(data);
        if (type == null) {
            throw new IllegalArgumentException("no zombie type with id = " + data);
        }
        return type;
    }

    static {
        for (ZombieType type : values()) {
            dataToType.put(type.data, type);
        }
    }

}
