package net.tridentsdk.world;

import net.tridentsdk.base.Position;

/**
 * Access to the world border properties
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 */
public interface WorldBorder {
    /**
     * Gets the size of the worldborder
     *
     * @return The size of the worldborder
     */
    double borderSize();

    /**
     * Gets the location where the worldborder is centered
     *
     * @return The location where the worldborder is centered
     */
    Position borderCenter();

    /**
     * Gets to what size a border is contracting, 60000000 by default
     *
     * @return To what size a border is contracting, 60000000 by default
     */
    int borderSizeContraction();

    /**
     * Gets the time the border has to contract to the contraction target
     *
     * @return The time the border has to contract to the contraction target
     */
    int borderSizeContractionTime();
}
