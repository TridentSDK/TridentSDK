/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2017 The TridentSDK Team
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
package net.tridentsdk.ui.title;

import net.tridentsdk.Impl;
import net.tridentsdk.chat.ChatComponent;

import javax.annotation.concurrent.NotThreadSafe;

/**
 * Represents a Title which is displayed in the
 * center of the client's screen. A title consists
 * of a main title and a subtitle, both of which are
 * optional.
 *
 * <p>Titles are independent of the player they are sent to.
 * This means that updates made to a title must use
 * {@link net.tridentsdk.entity.living.Player#sendTitle(Title)}
 * for every change after it is already sent in order for
 * the new changes to be effected.</p>
 *
 * <p>Unlike most classes in the TridentSDK API, this class
 * is <strong>not thread-safe</strong>.<p>
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
@NotThreadSafe
public interface Title {
    /**
     * The default number of ticks that it takes the title
     * to become the full opaqueness
     */
    int DEFAULT_FADE_IN = 10;
    /**
     * The default number of ticks that the title will stay
     * on the client's screen
     */
    int DEFAULT_STAY = 70;
    /**
     * The default number of ticks that it takes for the
     * title to become fully transparent and disappear from
     * the player's screen
     */
    int DEFAULT_FADE_OUT = 20;

    /**
     * Creates a new title.
     *
     * @return The new title.
     */
    static Title newTitle() {
        return Impl.get().newTitle();
    }

    /**
     * Gets the main title.
     *
     * @return The main title.
     */
    ChatComponent getHeader();

    /**
     * Sets the main title.
     *
     * @param value The new title.
     * @return This title.
     */
    Title setHeader(ChatComponent value);

    /**
     * Gets the subtitle.
     *
     * @return The subtitle.
     */
    ChatComponent getSubtitle();

    /**
     * Sets the subtitle.
     *
     * @param value The new subtitle.
     * @return This title.
     */
    Title setSubtitle(ChatComponent value);

    /**
     * Gets the fade in timing for the title
     * and subtitle.
     *
     * @return The fade in timing.
     */
    int getFadeIn();

    /**
     * Sets the fade in timing for the title
     * and subtitle.
     *
     * @param fadeIn Number of ticks to fade in.
     * @return This title.
     */
    Title setFadeIn(int fadeIn);

    /**
     * Gets the stay timing for the title
     * and subtitle.
     *
     * @return The stay timing.
     */
    int getStay();

    /**
     * Sets the stay timing for the title
     * and subtitle.
     *
     * @param stay Number of ticks to stay on screen.
     * @return This title.
     */
    Title setStay(int stay);

    /**
     * Gets the fade out timing for the title
     * and subtitle.
     *
     * @return The fade out timing.
     */
    int getFadeOut();

    /**
     * Sets the fade out timing for the title
     * and subtitle.
     *
     * @param fadeOut Number of ticks to fade out.
     * @return This title.
     */
    Title setFadeOut(int fadeOut);

    /**
     * Gets if this title is using the default timings
     * or if it has been manually overridden using the
     * setters for this Title.
     *
     * @return If the title is using default timings
     */
    boolean isDefaultFadeTimes();
}