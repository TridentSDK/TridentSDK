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

package net.tridentsdk.meta;

import net.tridentsdk.ServerConsole;
import net.tridentsdk.plugin.cmd.PlatformColor;

import javax.annotation.Nullable;

/**
 * A color able to be used in the chat text
 *
 * <p>These should be self-explanatory, if you need more help, take a look at
 * <a href="http://minecraft.gamepedia.com/Formatting_codes">Minecraft Wiki</a>.</p>
 *
 * @author The TridentSDK Team
 * @since 0.3-alpha-DP
 */
public enum ChatColor {
    BLACK {
        @Override
        public String toString() {
            return "§0";
        }
    },
    DARK_BLUE {
        @Override
        public String toString() {
            return "§1";
        }
    },
    DARK_GREEN {
        @Override
        public String toString() {
            return "§2";
        }
    },
    DARK_AQUA {
        @Override
        public String toString() {
            return "§3";
        }
    },
    DARK_RED {
        @Override
        public String toString() {
            return "§4";
        }
    },
    DARK_PURPLE {
        @Override
        public String toString() {
            return "§5";
        }
    },
    GOLD {
        @Override
        public String toString() {
            return "§6";
        }
    },
    GRAY {
        @Override
        public String toString() {
            return "§7";
        }
    },
    DARK_GRAY {
        @Override
        public String toString() {
            return "§8";
        }
    },
    BLUE {
        @Override
        public String toString() {
            return "§9";
        }
    },
    GREEN {
        @Override
        public String toString() {
            return "§a";
        }
    },
    AQUA {
        @Override
        public String toString() {
            return "§b";
        }
    },
    RED {
        @Override
        public String toString() {
            return "§c";
        }
    },
    LIGHT_PURPLE {
        @Override
        public String toString() {
            return "§d";
        }
    },
    YELLOW {
        @Override
        public String toString() {
            return "§e";
        }
    },
    WHITE {
        @Override
        public String toString() {
            return "§f";
        }
    },
    OBFUSCATED {
        @Override
        public String toString() {
            return "§k";
        }
    },
    BOLD {
        @Override
        public String toString() {
            return "§l";
        }
    },
    STRIKETHROUGH {
        @Override
        public String toString() {
            return "§m";
        }
    },
    UNDERLINE {
        @Override
        public String toString() {
            return "§n";
        }
    },
    ITALIC {
        @Override
        public String toString() {
            return "§o";
        }
    },
    RESET {
        @Override
        public String toString() {
            return "§r";
        }
    };

    /**
     * Finds the color based on the character after a the section symbol (§)
     *
     * @param color the character after the section
     * @return the chat color with that character
     */
    @Nullable
    public static ChatColor forColor(char color) {
        String find = String.valueOf(color);
        for (ChatColor chatColor : values()) {
            if (chatColor.toString().contains(find))
                return chatColor;
        }

        return null;
    }

    /**
     * Returns the console color representation of the chat color
     *
     * <p>Supported colors:
     * <ul>
     * <li>{@link net.tridentsdk.meta.ChatColor#BLACK}</li>
     * <li>{@link net.tridentsdk.meta.ChatColor#DARK_PURPLE} -
     * {@link ServerConsole#PURPLE}</li>
     * <li>{@link net.tridentsdk.meta.ChatColor#BLUE}</li>
     * <li>{@link net.tridentsdk.meta.ChatColor#GREEN}</li>
     * <li>{@link net.tridentsdk.meta.ChatColor#AQUA} - {@link ServerConsole#CYAN}</li>
     * <li>{@link net.tridentsdk.meta.ChatColor#RED}</li>
     * <li>{@link net.tridentsdk.meta.ChatColor#YELLOW}</li>
     * <li>{@link net.tridentsdk.meta.ChatColor#WHITE}</li>
     * <li>{@link net.tridentsdk.meta.ChatColor#RESET}</li>
     * </ul></p>
     *
     * @param color the color to parse
     * @return the server console color code for that chatcolor
     */
    public static String consoleFormat(ChatColor color) {
        switch (color) {
            case BLACK:
                return ServerConsole.BLACK;
            case DARK_PURPLE:
                return ServerConsole.PURPLE;
            case BLUE:
                return ServerConsole.BLUE;
            case GREEN:
                return ServerConsole.GREEN;
            case AQUA:
                return ServerConsole.CYAN;
            case RED:
                return ServerConsole.RED;
            case YELLOW:
                return ServerConsole.YELLOW;
            case WHITE:
                return ServerConsole.WHITE;
            case RESET:
                return ServerConsole.RESET;
            default:
                return PlatformColor.EMPTY;
        }
    }
}
