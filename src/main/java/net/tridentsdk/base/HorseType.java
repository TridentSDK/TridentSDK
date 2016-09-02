package net.tridentsdk.base;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
public enum HorseType {

    HORSE(0),
    DONKEY(1),
    MULE(2),
    ZOMBIE(3),
    SKELETON(4);

    @Getter
    private final int data;

    private HorseType(int data) {
        this.data = data;
    }

    private static final Map<Integer, HorseType> dataToType = new HashMap<>();

    static {
        for (HorseType type : values()) {
            dataToType.put(type.data, type);
        }
    }

    public static HorseType of(int data) {
        HorseType type = dataToType.get(data);
        if (type == null) {
            throw new IllegalArgumentException("no horse type with id = " + data);
        }
        return type;
    }

}
