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

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the different colors that can be sent in chat.
 */
public enum ChatColor {

    /**
     * Black, §0
     */
    BLACK('0'),

    /**
     * Dark Blue, §1
     */
    DARK_BLUE('1'),

    /**
     * Dark Green, §2
     */
    DARK_GREEN('2'),

    /**
     * Dark Aqua, §3
     */
    DARK_AQUA('3'),

    /**
     * Dark Red, §4
     */
    DARK_RED('4'),

    /**
     * Dark Purple, §5
     */
    DARK_PURPLE('5'),

    /**
     * Gold, §6
     */
    GOLD('6'),

    /**
     * Gray, §7
     */
    GRAY('7'),

    /**
     * Gray, §8
     */
    DARK_GRAY('8'),

    /**
     * Blue, §9
     */
    BLUE('9'),

    /**
     * Green, §a
     */
    GREEN('a'),

    /**
     * Aqua, §b
     */
    AQUA('b'),

    /**
     * Red, §c
     */
    RED('c'),

    /**
     * Light Purple, §d
     */
    LIGHT_PURPLE('d'),

    /**
     * Yellow, §e
     */
    YELLOW('e'),

    /**
     * White, §f
     */
    WHITE('f'),

    /**
     * Obfuscated, §k
     */
    OBFUSCATED('k'),

    /**
     * Bold, §l
     */
    BOLD('l'),

    /**
     * Strikethrough, §m
     */
    STRIKETHROUGH('m'),

    /**
     * Underline, §n
     */
    UNDERLINE('n'),

    /**
     * Italic, §o
     */
    ITALIC('o'),

    /**
     * Reset, §r
     */
    RESET('r');

    @Getter
    private char colorChar;

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
     * Gets a string representation of this color, in the form §{@code x}, where x is the color's character.
     *
     * @return The color.
     */
    public String toString() {
        return "\u00A7" + colorChar;
    }

    private static Map<Character, ChatColor> charToColor = new HashMap<Character, ChatColor>() {
        {
            for (ChatColor c : ChatColor.values()) {
                put(c.colorChar, c);
            }
        }
    };

    /**
     * Gets a chat color from a given character.
     *
     * @param colorChar The color's character.
     *
     * @return The color, or null if not found.
     */
    public static ChatColor getColor(char colorChar) {
        return charToColor.get(colorChar);
    }

}
