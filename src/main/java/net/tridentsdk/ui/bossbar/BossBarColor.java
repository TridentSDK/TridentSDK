package net.tridentsdk.ui.bossbar;

import lombok.Getter;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
@Getter
public enum BossBarColor {

    PINK(0),
    BLUE(1),
    RED(2),
    GREEN(3),
    YELLOW(4),
    PURPLE(5),
    WHITE(6);

    private final int id;

    BossBarColor(int id) {
        this.id = id;
    }

}
