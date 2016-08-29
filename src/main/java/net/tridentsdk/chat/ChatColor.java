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
package net.tridentsdk.chat;

import com.google.common.collect.Maps;
import lombok.Getter;

import java.util.Map;

/**
 * Represents the different colors that can be sent in chat.
 *
 * @author Nick Robson
 * @since 0.5-alpha
 */
public enum ChatColor {

    /**
     * Black, &#167;0
     */
    BLACK('0'),

    /**
     * Dark Blue, &#167;1
     */
    DARK_BLUE('1'),

    /**
     * Dark Green, &#167;2
     */
    DARK_GREEN('2'),

    /**
     * Dark Aqua, &#167;3
     */
    DARK_AQUA('3'),

    /**
     * Dark Red, &#167;4
     */
    DARK_RED('4'),

    /**
     * Dark Purple, &#167;5
     */
    DARK_PURPLE('5'),

    /**
     * Gold, &#167;6
     */
    GOLD('6'),

    /**
     * Gray, &#167;7
     */
    GRAY('7'),

    /**
     * Gray, &#167;8
     */
    DARK_GRAY('8'),

    /**
     * Blue, &#167;9
     */
    BLUE('9'),

    /**
     * Green, &#167;a
     */
    GREEN('a'),

    /**
     * Aqua, &#167;b
     */
    AQUA('b'),

    /**
     * Red, &#167;c
     */
    RED('c'),

    /**
     * Light Purple, &#167;d
     */
    LIGHT_PURPLE('d'),

    /**
     * Yellow, &#167;e
     */
    YELLOW('e'),

    /**
     * White, &#167;f
     */
    WHITE('f'),

    /**
     * Obfuscated, &#167;k
     */
    OBFUSCATED('k'),

    /**
     * Bold, &#167;l
     */
    BOLD('l'),

    /**
     * Strikethrough, &#167;m
     */
    STRIKETHROUGH('m'),

    /**
     * Underline, &#167;n
     */
    UNDERLINE('n'),

    /**
     * Italic, &#167;o
     */
    ITALIC('o'),

    /**
     * Reset, &#167;r
     */
    RESET('r');

    @Getter
    private final char colorChar;

    private ChatColor(char colorChar) {
        this.colorChar = colorChar;
    }

    /**
     * Gets if this chat color is a format color.
     *
     * @return True iff it is.
     */
    public boolean isFormat() {
        return 'k' <= colorChar && colorChar <= 'r';
    }

    /**
     * Gets if this chat color is a color.
     *
     * @return The color.
     */
    public boolean isColor() {
        return !isFormat();
    }

    /**
     * Gets a string representation of this color, in the form &#167;{@code x}, where x is the color's character.
     *
     * @return The color.
     */
    @Override
    public String toString() {
        return "\u00A7" + colorChar;
    }

    private static final Map<Character, ChatColor> charToColor = Maps.newConcurrentMap();

    static {
        for (ChatColor c : ChatColor.values()) {
            charToColor.put(c.colorChar, c);
        }
    };

    /**
     * Gets a chat color from a given character.
     *
     * @param colorChar The color's character.
     * @return The color, or null if not found.
     */
    public static ChatColor getColor(char colorChar) {
        ChatColor c = charToColor.get(colorChar);
        if (c == null)
            throw new IllegalArgumentException("no color with character " + colorChar);
        return c;
    }

}
