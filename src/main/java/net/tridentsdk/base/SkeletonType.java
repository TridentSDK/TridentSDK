package net.tridentsdk.base;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
public enum SkeletonType {

    NORMAL(0),
    WITHER(1),
    STRAY(2);

    @Getter
    private final int data;

    private SkeletonType(int data) {
        this.data = data;
    }

    private static final Map<Integer, SkeletonType> dataToType = new HashMap<>();

    public static SkeletonType of(int data) {
        SkeletonType type = dataToType.get(data);
        if (type == null) {
            throw new IllegalArgumentException("no skeleton type with id = " + data);
        }
        return type;
    }

    static {
        for (SkeletonType type : values()) {
            dataToType.put(type.data, type);
        }
    }

}
