package net.tridentsdk.base;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

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

    private HorseMarkings(int data) {
        this.data = data;
    }

    private static final Map<Integer, HorseMarkings> dataToMarkings = new HashMap<>();

    static {
        for (HorseMarkings markings : values()) {
            dataToMarkings.put(markings.data, markings);
        }
    }

    public static HorseMarkings of(int data) {
        HorseMarkings markings = dataToMarkings.get(data);
        if (markings == null) {
            throw new IllegalArgumentException("no horse markings with id = " + data);
        }
        return markings;
    }

}

