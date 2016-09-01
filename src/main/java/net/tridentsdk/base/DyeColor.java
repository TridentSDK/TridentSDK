package net.tridentsdk.base;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
public enum DyeColor {

    BLACK(0),
    RED(1),
    DARK_GREEN(2),
    BROWN(3),
    DARK_BLUE(4),
    DARK_PURPLE(5),
    CYAN(6),
    LIGHT_GRAY(7),
    DARK_GRAY(8),
    PINK(9),
    LIGHT_GREEN(10),
    YELLOW(11),
    LIGHT_BLUE(12),
    MAGENTA(13),
    ORANGE(14),
    WHITE(15);

    @Getter
    private final int data;

    private DyeColor(int data) {
        this.data = data;
    }

    private static final Map<Integer, DyeColor> dataToColor = new HashMap<>();

    static {
        for (DyeColor color : values()) {
            dataToColor.put(color.data, color);
        }
    }

}
