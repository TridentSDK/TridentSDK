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

package net.tridentsdk.world;

public enum Dimension {
    NETHER(-1),
    OVERWORLD(0),
    END(1);

    private final byte b;

    Dimension(int i) {
        this.b = (byte) i;
    }

    public static Dimension getDimension(int i) {
        for (Dimension dimension : values()) {
            if (dimension.b == i) {
                return dimension;
            }
        }

        return null;
    }

    public byte asByte() {
        return this.b;
    }
}
