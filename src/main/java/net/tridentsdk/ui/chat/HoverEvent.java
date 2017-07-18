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
package net.tridentsdk.ui.chat;

import lombok.Data;
import net.tridentsdk.inventory.Item;
import org.hjson.JsonObject;
import org.hjson.JsonValue;

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
    private final JsonValue value;

    /**
     * Creates a new hover event with the given action
     * and the given text that triggered it.
     *
     * @param action the triggering action
     * @param value the triggering text value
     */
    private HoverEvent(HoverAction action, JsonValue value) {
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
        return new HoverEvent(HoverAction.SHOW_ACHIEVEMENT, JsonValue.valueOf(achievement));
    }

    /**
     * Adds an item property to the chat component.
     *
     * @param item the item
     * @return the new hover action event
     */
    public static HoverEvent item(Item item) {
        JsonObject json = new JsonObject();
        json.add("id", item.getSubstance().toString().replaceAll("minecraft:", ""));
        json.add("Damage", item.getDamage());
        json.add("Count", item.getCount());
        json.add("tag", "{}");

        String string = json.toString().replaceAll("\"", "");
        return new HoverEvent(HoverAction.SHOW_ITEM, JsonValue.valueOf(string));
    }

    /**
     * Parses a click event from the given JSON.
     *
     * @param json The JSON.
     * @return The click event.
     */
    public static HoverEvent fromJson(JsonValue json) {
        return new HoverEvent(HoverAction.valueOf(json.asObject().get("action").asString().toUpperCase()), json.asObject().get("value"));
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
    public JsonValue getValue() {
        return this.value;
    }

    /**
     * Gets this action performance as JSON, for
     * transmission.
     *
     * @return The JSON.
     */
    public JsonValue asJson() {
        JsonObject obj = new JsonObject();
        obj.add("action", this.action.name().toLowerCase());
        obj.add("value", this.value);
        return obj;
    }
}
