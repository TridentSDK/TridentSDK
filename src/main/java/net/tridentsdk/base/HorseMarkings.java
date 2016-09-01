package net.tridentsdk.base;

import lombok.Getter;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
public enum HorseMarkings {

    WHITE(0),
    CREAMY(1),
    CHESTNUT(2),
    BROWN(3),
    BLACK(4),
    GRAY(5),
    DARK_BROWN(6);

    @Getter
    private final int data;

    private HorseColor(int data) {
        this.data = data;
    }

}

