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

import com.google.common.collect.Maps;

import javax.annotation.concurrent.Immutable;
import java.util.Map;

/**
 * Represents an RGB color. This class is immutable, any changes will
 * return a new instance and make no modifications to the original
 */
@Immutable
public class Color {
    public static final Color WHITE = fromRGB(0xFFFFFF);
    public static final Color SILVER = fromRGB(0xC0C0C0);
    public static final Color GRAY = fromRGB(0x808080);
    public static final Color BLACK = fromRGB(0x000000);
    public static final Color RED = fromRGB(0xFF0000);
    public static final Color MAROON = fromRGB(0x800000);
    public static final Color YELLOW = fromRGB(0xFFFF00);
    public static final Color OLIVE = fromRGB(0x808000);
    public static final Color LIME = fromRGB(0x00FF00);
    public static final Color GREEN = fromRGB(0x008000);
    public static final Color TEAL = fromRGB(0x008080);
    public static final Color BLUE = fromRGB(0x0000FF);
    public static final Color NAVY = fromRGB(0x000080);
    public static final Color FUCHSIA = fromRGB(0xFF00FF);
    public static final Color PURPLE = fromRGB(0x800080);
    public static final Color ORANGE = fromRGB(0xFFA500);

    private static Map<Integer, Color> cache = Maps.newConcurrentMap();

    private final byte red;
    private final byte green;
    private final byte blue;

    private Color(int red, int green, int blue) {
        this.red = (byte) red;
        this.green = (byte) green;
        this.blue = (byte) blue;
    }

    public static Color fromRGB(int red, int green, int blue) {
        int id = red << 16 | green << 8 | blue;

        if (!cache.containsKey(id)) {
            cache.put(id, new Color(red, green, blue));
        }

        return cache.get(id);
    }

    public static Color fromRGB(int rgb) {
        return fromRGB(rgb >> 16 & 0xff, rgb >> 8 & 0xff, rgb & 0xff);
    }

    public int red() {
        return 0xff & red;
    }

    public int green() {
        return 0xff & green;
    }

    public int blue() {
        return 0xff & blue;
    }

    public Color setRed(int red) {
        return fromRGB(red, green, blue);
    }

    public Color setGreen(int green) {
        return fromRGB(red, green, blue);
    }

    public Color setBlue(int blue) {
        return fromRGB(red, green, blue);
    }

    public int asRGB() {
        return red() << 16 | green() << 8 | blue;
    }

    public int asBGR() {
        return blue() << 16 | green() << 8 | red;
    }
}
