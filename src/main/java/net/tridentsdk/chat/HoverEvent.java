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
package net.tridentsdk.chat;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import lombok.Data;
import net.tridentsdk.inventory.Item;

import javax.annotation.concurrent.Immutable;

/**
 * Represents an action done upon hovering over text.
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
@Data
@Immutable
public class HoverEvent {
    /**
     * The action that triggered this hover event
     */
    private final HoverAction action;
    /**
     * The text that triggerd this event
     */
    private final JsonElement value;

    /**
     * Creates a new hover event with the given action
     * and the given text that triggered it.
     *
     * @param action the triggering action
     * @param value the triggering text value
     */
    private HoverEvent(HoverAction action, JsonElement value) {
        this.action = action;
        this.value = value;
    }

    /**
     * Creates a show text hover action with text.
     *
     * @param text The text.
     * @return The hover action event.
     */
    public static HoverEvent text(String text) {
        return text(ChatComponent.text(text));
    }

    /**
     * Creates a show text hover action with a chat
     * component.
     *
     * @param chat The chat component.
     * @return The hover action event.
     */
    public static HoverEvent text(ChatComponent chat) {
        return new HoverEvent(HoverAction.SHOW_TEXT, chat.asJson());
    }

    /**
     * Creates a show achievement hover action with an
     * achievement.
     *
     * @param achievement The achievement.
     * @return The hover action event.
     */
    public static HoverEvent achievement(String achievement) {
        return new HoverEvent(HoverAction.SHOW_ACHIEVEMENT, new JsonPrimitive(achievement));
    }

    public static HoverEvent item(Item item) {
        JsonObject json = new JsonObject();
        json.addProperty("id", item.getSubstance().toString());

        return new HoverEvent(HoverAction.SHOW_ITEM, json);
    }

    /**
     * Parses a click event from the given JSON.
     *
     * @param json The JSON.
     * @return The click event.
     */
    public static HoverEvent fromJson(JsonObject json) {
        return new HoverEvent(HoverAction.valueOf(json.get("action").getAsString().toUpperCase()), json.get("value"));
    }

    /**
     * Gets the action to be performed.
     *
     * @return The action.
     */
    public HoverAction getAction() {
        return this.action;
    }

    /**
     * Gets what value the action is applied to.
     *
     * @return The value.
     */
    public JsonElement getValue() {
        return this.value;
    }

    /**
     * Gets this action performance as JSON, for
     * transmission.
     *
     * @return The JSON.
     */
    public JsonObject asJson() {
        JsonObject obj = new JsonObject();
        obj.addProperty("action", this.action.name().toLowerCase());
        obj.add("value", this.value);
        return obj;
    }
}
