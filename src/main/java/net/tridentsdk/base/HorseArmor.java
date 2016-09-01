package net.tridentsdk.base;

import lombok.Getter;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
public enum HorseArmor {

    LEATHER(3),
    IRON(5),
    GOLD(7),
    DIAMOND(11);

    @Getter
    private final int armor;

    private HorseArmor(int armor) {
        this.armor = armor;
    }

}
