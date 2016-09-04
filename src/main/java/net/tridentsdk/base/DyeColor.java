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
public enum DyeColor {

    BLACK(0),
    RED(1),
    DARK_GREEN(2),
    BROWN(3),
    DARK_BLUE(4),
    DARK_PURPLE(5),
    CYAN(6),
    LIGHT_GRAY(7),
    DARK_GRAY(8),
    PINK(9),
    LIGHT_GREEN(10),
    YELLOW(11),
    LIGHT_BLUE(12),
    MAGENTA(13),
    ORANGE(14),
    WHITE(15);

    @Getter
    private final int data;

    DyeColor(int data) {
        this.data = data;
    }

    private static final Map<Integer, DyeColor> dataToColor = new HashMap<>();

    static {
        for (DyeColor color : values()) {
            dataToColor.put(color.data, color);
        }
    }

    public static DyeColor of(int data) {
        DyeColor color = dataToColor.get(data);
        if (color == null) {
            throw new IllegalArgumentException("no dye color with id = " + data);
        }
        return color;
    }

}
