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

package net.tridentsdk;

/**
 * Minecraft game modes
 *
 * <p>If you need more help, take a look at <a href="http://minecraft.gamepedia.com/Gameplay#Game_modes">Wiki</a></p>
 *
 * @author The TridentSDK Team
 */
public enum GameMode {
    SURVIVAL(0),
    CREATIVE(1),
    ADVENTURE(2),
    SPECTATE(3),
    HARDCORE(8);

    private final byte b;

    GameMode(int i) {
        this.b = (byte) i;
    }

    /**
     * Returns the {@code byte} value of the GameMode
     *
     * @param gameMode GameMode
     * @return {@code byte} value of the GameMode
     */
    public static byte asByte(GameMode gameMode) {
        return gameMode.asByte();
    }

    public static GameMode getById(int i) {
        for (GameMode mode : values()) {
            if (mode.b == i) {
                return mode;
            }
        }

        return null;
    }

    /**
     * Returns the {@code byte} value of the GameMode
     *
     * @return {@code byte} value of the GameMode
     */
    public byte asByte() {
        return this.b;
    }
}
