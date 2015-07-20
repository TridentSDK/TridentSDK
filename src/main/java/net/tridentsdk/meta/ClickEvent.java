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
 * This event occurs when a player clicks on a chat item
 *
 * @author The TridentSDK Team
 * @since 0.3-alpha-DP
 */
public class ClickEvent {
    private ClickAction action;
    private String value;

    /**
     * Sets the action that occurs when the player clicks the chat message
     *
     * @param action the action that occurs when the player clicks the chat
     * @return the instance of the event
     */
    public ClickEvent action(ClickAction action) {
        this.action = action;

        return this;
    }

    /**
     * Sets the value of the action when the player clicks the chat message
     *
     * @param value the value of the action that occurs when the player clicks the chat
     * @return the instance of the event
     */
    public ClickEvent value(String value) {
        this.value = value;

        return this;
    }

    /**
     * Obtains the action that occurs when the chat is clicked
     *
     * @return the action that occurs when the chat is clicked
     */
    public ClickAction action() {
        return action;
    }

    /**
     * Obtains the value of the action that occurs when the chat is clicked
     *
     * @return the value of the action that occurs when the chat is clicked
     */
    public String value() {
        return value;
    }

    /**
     * An action that occurs when the player clicks on the chat message
     *
     * @author The TridentSDK Team
     * @since 0.3-alpha-DP
     */
    public enum ClickAction {
        OPEN_URL,
        OPEN_FILE,
        RUN_COMMAND,
        SUGGEST_COMMAND;

        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }
    }
}
