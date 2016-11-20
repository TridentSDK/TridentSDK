/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2016 The TridentSDK Team
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
package net.tridentsdk.ui.bossbar;

import net.tridentsdk.Impl;
import net.tridentsdk.chat.ChatComponent;

import java.util.UUID;

/**
 * Represents a boss bar displayed at the top
 * of a client's game window, containing text,
 * health, a color and dividing lines.
 * <br>
 * There are optional graphical and audio effects
 * associated with the boss bar that can be enabled
 * if desired.
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
public interface BossBar {
    /**
     * Creates a new boss bar instance which can be
     * modified
     * by the caller to customize the interface.
     *
     * @return the new boss bar
     */
    static BossBar newBossBar() {
        return Impl.get().newBossBar();
    }

    /**
     * Gets this boss bar's unique ID.
     *
     * @return The unique ID.
     */
    UUID getUuid();

    /**
     * Checks to see whether the current boss bar is the
     * default.
     *
     * @return {@code true} if the current bar is default,
     * {@code false} otherwise
     */
    boolean isDefault();

    /**
     * Gets the title of this boss bar.
     *
     * @return The title.
     */
    ChatComponent getTitle();

    /**
     * Sets the title of this boss bar.
     *
     * @param title The new title.
     */
    void setTitle(ChatComponent title);

    /**
     * Gets the boss bar's health.
     *
     * Between 0 and 1.
     *
     * @return The health.
     */
    float getHealth();

    /**
     * Sets this bar's health.
     *
     * Between 0 and 1 is considered normal,
     * values above 1.5 start rendering a second bar.
     *
     * @param health The new health.
     */
    void setHealth(float health);

    /**
     * Gets this boss bar's color.
     *
     * @return The color.
     */
    BossBarColor getColor();

    /**
     * Sets this boss bar's color.
     *
     * @param color The new color.
     */
    void setColor(BossBarColor color);

    /**
     * Gets this boss bar's division style.
     *
     * @return The division style.
     */
    BossBarDivision getDivision();

    /**
     * Sets this boss bar's division style.
     *
     * @param division The new division style.
     */
    void setDivision(BossBarDivision division);

    /**
     * Gets whether or not this boss bar darkens users' sky.
     *
     * @return True iff the sky is darkened.
     */
    boolean isDarkenSky();

    /**
     * Sets whether or not to darken the sky.
     *
     * @param darkenSky True to darken, false to not.
     */
    void setDarkenSky(boolean darkenSky);

    /**
     * Gets whether or not this boss bar is a dragon bar.
     *
     * Used by the client to play End music if true.
     *
     * @return True iff it is a dragon bar.
     */
    boolean isDragonBar();

    /**
     * Sets whether or not this bar is a dragon bar.
     *
     * @param dragonBar True to set as a dragon bar, false to not be.
     */
    void setDragonBar(boolean dragonBar);

    /**
     * Clones this boss bar.
     *
     * @return A cloned copy of this boss bar.
     */
    BossBar clone();
}