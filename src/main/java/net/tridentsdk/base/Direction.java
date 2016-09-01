package net.tridentsdk.base;

import lombok.Getter;

/**
 * Represents a direction.
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
public enum Direction {

    /**
     * Downwards
     */
    DOWN(0),

    /**
     * Upwards
     */
    UP(1),

    /**
     * North
     */
    NORTH(2),

    /**
     * South
     */
    SOUTH(3),

    /**
     * West
     */
    WEST(4),

    /**
     * East
     */
    EAST(5);

    @Getter
    private final int data;

    private Direction(int data) {
        this.data = data;
    }

}
