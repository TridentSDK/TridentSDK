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
public enum HorseMarkings {

    WHITE(0),
    CREAMY(1),
    CHESTNUT(2),
    BROWN(3),
    BLACK(4),
    GRAY(5),
    DARK_BROWN(6);

    @Getter
    private final int data;

    HorseMarkings(int data) {
        this.data = data;
    }

    private static final Map<Integer, HorseMarkings> dataToMarkings = new HashMap<>();

    static {
        for (HorseMarkings markings : values()) {
            dataToMarkings.put(markings.data, markings);
        }
    }

    public static HorseMarkings of(int data) {
        HorseMarkings markings = dataToMarkings.get(data);
        if (markings == null) {
            throw new IllegalArgumentException("no horse markings with id = " + data);
        }
        return markings;
    }

}

