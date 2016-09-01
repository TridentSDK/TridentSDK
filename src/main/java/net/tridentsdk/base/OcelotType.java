package net.tridentsdk.base;

import lombok.Getter;

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

}
