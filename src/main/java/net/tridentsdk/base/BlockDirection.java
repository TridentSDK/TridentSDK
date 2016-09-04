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

@Getter
public enum BlockDirection {

    NORTH(2, 0, 0, -1),
    NORTH_EAST(-1, 1, 0, -1),
    EAST(5, 1, 0, 0),
    SOUTH_EAST(-1, 1, 0, 1),
    SOUTH(3, 0, 0, 1),
    SOUTH_WEST(-1, -1, 0, 1),
    WEST(4, -1, 0, 0),
    NORTH_WEST(-1, -1, 0, -1),

    UP(1, 0, 1, 0),
    DOWN(0, 0, -1, 0);

    private int minecraftDirection;
    private int xDiff;
    private int yDiff;
    private int zDiff;

    BlockDirection(int minecraftDirection, int xDiff, int yDiff, int zDiff) {
        this.minecraftDirection = minecraftDirection;
        this.xDiff = xDiff;
        this.yDiff = yDiff;
        this.zDiff = zDiff;
    }

    public boolean hasMinecraftDirection() {
        return this.minecraftDirection != -1;
    }

    public BlockDirection anticlockwise() {
        return this.anticlockwise(false);
    }

    public BlockDirection anticlockwise(boolean includeDiagonals) {
        if (this.ordinal() >= 8)
            return this;
        return values()[(this.ordinal() + (includeDiagonals ? 7 : 6)) % 8];
    }

    public BlockDirection clockwise() {
        return this.clockwise(false);
    }

    public BlockDirection clockwise(boolean includeDiagonals) {
        if (this.ordinal() >= 8)
            return this;
        return values()[(this.ordinal() + (includeDiagonals ? 1 : 2)) % 8];
    }

    public static BlockDirection fromMinecraftDirection(int direction) {
        for (BlockDirection d : values()) {
            if(d.minecraftDirection == direction){
                return d;
            }
        }
        return null;
    }

    public BlockDirection getOpposite() {
        if (this.ordinal() < 8)
            return BlockDirection.values()[(this.ordinal() + 4) % 8];
        return this == UP ? DOWN : UP;
    }

}
