package net.tridentsdk.base;

import lombok.Getter;

public enum BlockDirection {

    NORTH(0, 0, -1),
    NORTH_EAST(1, 0, -1),
    EAST(1, 0, 0),
    SOUTH_EAST(1, 0, 1),
    SOUTH(0, 0, 1),
    SOUTH_WEST(-1, 0, 1),
    WEST(-1, 0, 0),
    NORTH_WEST(-1, 0, -1),

    UP(0, 1, 0),
    DOWN(0, -1, 0);

    @Getter
    private int xDiff;
    @Getter
    private int yDiff;
    @Getter
    private int zDiff;

    BlockDirection(int xDiff, int yDiff, int zDiff) {
        this.xDiff = xDiff;
        this.yDiff = yDiff;
        this.zDiff = zDiff;
    }

    public BlockDirection left() {
        return left(false);
    }

    public BlockDirection left(boolean includeDiagonals) {
        int direction = ordinal() + (includeDiagonals ? 1 : 2);

        if(direction < 0){
            direction += 8;
        }

        return values()[direction];
    }

    public BlockDirection right() {
        return right(false);
    }

    public BlockDirection right(boolean includeDiagonals) {
        return values()[(ordinal() + (includeDiagonals ? 1 : 2)) % 8];
    }

}
