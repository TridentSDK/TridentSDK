package net.tridentsdk.effect.visual;

import net.tridentsdk.effect.RemoteEffect;

/**
 * Represents a visual effect
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 */
public interface VisualEffect extends RemoteEffect<VisualEffectType> {

    /**
     * Set the data of the effect<br>
     * Only used for FIRE_SMOKE, BLOCK_BREAK and SPLASH_POTION<br>
     * <br>
     * FIRE_SMOKE requires direction of the smoke<br>
     * 0 = South-East, 1 = South, 2 = South-West, 3 = East,<br>
     * 4 = Up/Middle, 5 = West, 6 = North-East, 7 = North, 8 = North-West<br>
     * <br>
     * BLOCK_BREAK requires block ID<br>
     * <br>
     * SPLASH_POTION requires potion ID (refer to <a href="http://tridentsdk.net/potions.txt">This List</a>)
     *
     * @param data Data of the effect
     */
    void setData(int data);

    /**
     * Returns the data of the effect
     *
     * @return The data of the effect
     */
    int data();

}
