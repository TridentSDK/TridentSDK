/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2017 The TridentSDK Team
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

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

/**
 * Represents the compass direction which a block may be
 * facing, or in reference to the faces of a block.
 *
 * @author TridentSDK
 * @since 0.3-alpha-DP
 */
@Getter
@Immutable
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

    /**
     * The internal direction used by Minecraft itself.
     */
    private final int minecraftDirection;

    /**
     * The vector offset in the X direction.
     */
    private final int xDiff;

    /**
     * The vector offset in the Y direction.
     */

    private final int yDiff;
    /**
     * The vector offset in the Z direction.
     */
    private final int zDiff;

    BlockDirection(int minecraftDirection, int xDiff, int yDiff, int zDiff) {
        this.minecraftDirection = minecraftDirection;
        this.xDiff = xDiff;
        this.yDiff = yDiff;
        this.zDiff = zDiff;
    }

    /**
     * Gets whether or not this direction has a corresponding Minecraft direction.
     *
     * @return True iff it does.
     */
    public boolean hasMinecraftDirection() {
        return this.minecraftDirection != -1;
    }

    /**
     * Gets the first block direction in the anticlockwise direction, ignoring diagonals.
     *
     * @return The direction.
     */
    public BlockDirection anticlockwise() {
        return this.anticlockwise(false);
    }

    /**
     * Gets the first block direction in the anticlockwise direction, optionally ignoring diagonals.
     *
     * @return The direction.
     */
    public BlockDirection anticlockwise(boolean includeDiagonals) {
        if (this.ordinal() >= 8)
            return this;
        return values()[(this.ordinal() + (includeDiagonals ? 7 : 6)) % 8];
    }

    /**
     * Gets the first block direction in the clockwise direction, ignoring diagonals.
     *
     * @return The direction.
     */
    public BlockDirection clockwise() {
        return this.clockwise(false);
    }

    /**
     * Gets the first block direction in the clockwise direction, optionally ignoring diagonals.
     *
     * @return The direction.
     */
    public BlockDirection clockwise(boolean includeDiagonals) {
        if (this.ordinal() >= 8)
            return this;
        return values()[(this.ordinal() + (includeDiagonals ? 1 : 2)) % 8];
    }

    /**
     * Gets the block direction opposite to this one.
     *
     * @return The block direction.
     */
    public BlockDirection getOpposite() {
        if (this.ordinal() < 8)
            return BlockDirection.values()[(this.ordinal() + 4) % 8];
        return this == UP ? DOWN : UP;
    }

    /**
     * Gets the block direction corresponding to the given Minecraft direction.
     *
     * @param direction The Minecraft direction.
     * @return The block direction.
     */
    @Nonnull
    public static BlockDirection fromMinecraftDirection(int direction) {
        for (BlockDirection d : values()) {
            if(d.hasMinecraftDirection() && d.minecraftDirection == direction){
                return d;
            }
        }

        throw new IllegalArgumentException("no block direction with direction=" + direction);
    }
}