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
package net.tridentsdk.chat;

import com.google.gson.JsonObject;
import lombok.Data;

import javax.annotation.concurrent.Immutable;

/**
 * Represents an action done upon clicking text.
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
@Data
@Immutable
public class ClickEvent {
    /**
     * The action that triggers this click event
     */
    private final ClickAction action;
    /**
     * The value of the click
     */
    private final String value;

    /**
     * Creates a new click event with the given action and
     * value.
     *
     * @param action the action that triggered the event
     * @param value the text value
     */
    private ClickEvent(ClickAction action, String value) {
        this.action = action;
        this.value = value;
    }

    /**
     * Creates a click action with the given action and text.
     *
     * @param action The action.
     * @param text The text.
     * @return The click action event.
     */
    public static ClickEvent of(ClickAction action, String text) {
        return new ClickEvent(action, text);
    }

    /**
     * Parses a click event from the given JSON.
     *
     * @param json The JSON.
     * @return The click event.
     */
    public static ClickEvent fromJson(JsonObject json) {
        return of(ClickAction.valueOf(json.get("action").getAsString().toUpperCase()), json.get("value").getAsString());
    }

    /**
     * Gets the action to be performed.
     *
     * @return The action.
     */
    public ClickAction getAction() {
        return this.action;
    }

    /**
     * Gets what value the action is applied to.
     *
     * @return The value.
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Gets this action performance as JSON, for transmission.
     *
     * @return The JSON.
     */
    public JsonObject asJson() {
        JsonObject obj = new JsonObject();
        obj.addProperty("action", this.action.name().toLowerCase());
        obj.addProperty("value", this.value);
        return obj;
    }

}
