package net.tridentsdk.base;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
public enum ZombieType {

    NORMAL(0),
    VILLAGER_FARMER(1),
    VILLAGER_LIBRARIAN(2),
    VILLAGER_PRIEST(3),
    VILLAGER_BLACKSMITH(4),
    VILLAGER_BUTCHER(5),
    HUSK(6);

    @Getter
    private final int data;

    @Getter
    private final boolean villager;

    private ZombieType(int data) {
        this.data = data;
        this.villager = data >= 1 && data <= 5;
    }

    private static final Map<Integer, ZombieType> dataToType = new HashMap<>();

    public static ZombieType of(int data) {
        ZombieType type = dataToType.get(data);
        if (type == null) {
            throw new IllegalArgumentException("no zombie type with id = " + data);
        }
        return type;
    }

    static {
        for (ZombieType type : values()) {
            dataToType.put(type.data, type);
        }
    }

}
