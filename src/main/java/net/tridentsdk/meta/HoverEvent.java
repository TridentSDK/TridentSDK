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

package net.tridentsdk.meta;

/**
 * This even occurs when the player hovers the mouse over the chat message
 *
 * @author The TridentSDK Team
 * @since 0.3-alpha-DP
 */
public class HoverEvent {
    private HoverAction action;
    private String value;

    /**
     * Sets the action associated with the player hovers over the chat
     *
     * @param action the action to occur
     * @return the instance of the event
     */
    public HoverEvent action(HoverAction action) {
        this.action = action;

        return this;
    }

    /**
     * Sets the value associated with the player hovers over the chat
     *
     * @param value the action to occur
     * @return the instance of the event
     */
    public HoverEvent value(String value) {
        this.value = value;

        return this;
    }

    /**
     * Gets the action that occurs when the player hovers over the chat
     *
     * @return the action that occurs when the chat is hovered
     */
    public HoverAction action() {
        return action;
    }

    /**
     * Gets the value of the event player hovers over the chat
     *
     * @return the value of the event that occurs when the chat is hovered
     */
    public String value() {
        return value;
    }

    /**
     * An action that occurs when the player hovers over the chat message
     *
     * @author The TridentSDK Team
     * @since 0.3-alpha-DP
     */
    public enum HoverAction {
        SHOW_TEXT,
        SHOW_ACHIEVEMENT,
        SHOW_ITEM;

        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }
    }
}
