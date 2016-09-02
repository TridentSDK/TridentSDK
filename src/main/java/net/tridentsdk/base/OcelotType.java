package net.tridentsdk.base;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
public enum OcelotType {

    WILD(0),
    TUXEDO(1),
    TABBY(2),
    SIAMESE(3);

    @Getter
    private final int data;

    private OcelotType(int data) {
        this.data = data;
    }

    private static final Map<Integer, OcelotType> dataToType = new HashMap<>();

    static {
        for (OcelotType type : values()) {
            dataToType.put(type.data, type);
        }
    }

    public static OcelotType of(int data) {
        OcelotType type = dataToType.get(data);
        if (type == null) {
            throw new IllegalArgumentException("no ocelot type with id = " + data);
        }
        return type;
    }

}
