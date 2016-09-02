package net.tridentsdk.base;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

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

    private static final Map<Integer, HorseColor> dataToColor = new HashMap<>();

    static {
        for (HorseColor color : values()) {
            dataToColor.put(color.data, color);
        }
    }

    public static HorseColor of(int data) {
        HorseColor color = dataToColor.get(data);
        if (color == null) {
            throw new IllegalArgumentException("no horse color with id = " + data);
        }
        return color;
    }

}
