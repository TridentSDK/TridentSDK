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
public enum RabbitType {

    BROWN(0),
    WHITE(1),
    BLACK(2),
    BLACK_AND_WHITE(3),
    GOLD(4),
    SALT_AND_PEPPER(5),
    KILLER_BUNNY(99);

    @Getter
    private final int data;

    private RabbitType(int data) {
        this.data = data;
    }

    private static final Map<Integer, RabbitType> dataToType = new HashMap<>();

    static {
        for (RabbitType type : values()) {
            dataToType.put(type.data, type);
        }
    }

    public static RabbitType of(int data) {
        RabbitType type = dataToType.get(data);
        if (type == null) {
            throw new IllegalArgumentException("no rabbit type with id = " + data);
        }
        return type;
    }

}
