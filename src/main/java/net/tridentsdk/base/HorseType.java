package net.tridentsdk.base;

import lombok.Getter;

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

}
