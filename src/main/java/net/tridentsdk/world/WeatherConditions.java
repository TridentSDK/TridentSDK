/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2014 The TridentSDK Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
