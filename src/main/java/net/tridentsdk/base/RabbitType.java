package net.tridentsdk.base;

import lombok.Getter;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
public enum RabbitType {

    BROWN(0),
    WHITE(1),
    BLACK(2),
    BLACK_AND_WHITE(3),
    GOLD(4),
    SALT_AND_PEPPER(5),
    KILLER_BUNNY(99);

    @Getter
    private final int data;

    private RabbitType(int data) {
        this.data = data;
    }

}
