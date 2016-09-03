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
        return minecraftDirection != -1;
    }

    public BlockDirection anticlockwise() {
        return anticlockwise(false);
    }

    public BlockDirection anticlockwise(boolean includeDiagonals) {
        if (ordinal() >= 8)
            return this;
        return values()[(ordinal() + (includeDiagonals ? 7 : 6)) % 8];
    }

    public BlockDirection clockwise() {
        return clockwise(false);
    }

    public BlockDirection clockwise(boolean includeDiagonals) {
        if (ordinal() >= 8)
            return this;
        return values()[(ordinal() + (includeDiagonals ? 1 : 2)) % 8];
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
        if (ordinal() < 8)
            return BlockDirection.values()[(ordinal() + 4) % 8];
        return this == UP ? DOWN : UP;
    }

}
