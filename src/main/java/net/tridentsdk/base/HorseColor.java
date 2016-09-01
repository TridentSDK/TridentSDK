package net.tridentsdk.base;

import lombok.Getter;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
public enum HorseColor {

    NONE(0),
    WHITE(1),
    WHITE_FIELD(2),
    WHITE_DOTS(3),
    BLACK_DOTS(4);

    @Getter
    private final int data;

    private HorseColor(int data) {
        this.data = data;
    }

}
