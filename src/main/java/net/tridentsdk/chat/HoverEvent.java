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

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * Represents an action done upon hovering over text.
 *
 * @author Nick Robson
 * @since 0.5-alpha
 */
public class HoverEvent {

    private final HoverAction action;
    private final JsonElement value;

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
     * Creates a show text hover action with a chat component.
     *
     * @param chat The chat component.
     * @return The hover action event.
     */
    public static HoverEvent text(ChatComponent chat) {
        return new HoverEvent(HoverAction.SHOW_TEXT, chat.asJson());
    }

    /**
     * Creates a show achievement hover action with an achievement.
     *
     * @param achievement The achievement.
     * @return The hover action event.
     */
    public static HoverEvent achievement(String achievement) {
        return new HoverEvent(HoverAction.SHOW_ACHIEVEMENT, new JsonPrimitive(achievement));
    }

    /* TODO :: once items are implemented
    public static HoverEvent item(ItemStack item) {

    }
    */

    /**
     * Gets the action to be performed.
     *
     * @return The action.
     */
    public HoverAction getAction() {
        return action;
    }

    /**
     * Gets what value the action is applied to.
     *
     * @return The value.
     */
    public JsonElement getValue() {
        return value;
    }

    /**
     * Gets this action performance as JSON, for transmission.
     *
     * @return The JSON.
     */
    public JsonObject asJson() {
        JsonObject obj = new JsonObject();
        obj.addProperty("action", action.name().toLowerCase());
        obj.add("value", value);
        return obj;
    }

}
