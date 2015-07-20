package net.tridentsdk.world;

/**
 * Obtains the state of the weather, and allows control of the Weather in a world
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 */
public interface WeatherConditions {
    /**
     * Checks if it is raining in a world
     *
     * @return True if it is raining in a world
     */
    boolean isRaining();

    /**
     * Gets the number of ticks before raining is toggled
     *
     * @return The number of ticks before raining is toggled
     */
    int rainTime();

    /**
     * Toggles raining within the world
     *
     * @param ticks the ticks to run rain further, or {@code 0} to stop raining
     */
    void toggleRain(int ticks);

    /**
     * Checks if it is thundering in a world
     *
     * @return True if it is thundering in a world
     */
    boolean isThundering();

    /**
     * Gets the number of ticks before thundering is toggled
     *
     * @return The number of ticks before thundering is toggled
     */
    int thunderTime();

    /**
     * Toggles thundering within the world
     *
     * @param ticks the amount of ticks to continue thundering, {@code 0} to stop thundering
     */
    void toggleThunder(int ticks);
}
