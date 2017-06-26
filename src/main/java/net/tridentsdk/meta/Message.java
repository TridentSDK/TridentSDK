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

import com.google.gson.JsonObject;

import javax.annotation.concurrent.NotThreadSafe;

/**
 * Chat message properties, encoded in the JSON format
 *
 * @author The TridentSDK Team
 * @since 0.3-alpha-DP
 */
@NotThreadSafe
public final class Message {
    private final JsonObject message;

    /**
     * Starts building a new chat message
     */
    public Message() {
        this.message = new JsonObject();
    }

    /**
     * The text to display to the messageable
     *
     * @param input the text
     * @return the current message
     */
    public Message text(String input) {
        this.message().addProperty("text", input);
        return this;
    }

    /**
     * Adds color to the message
     *
     * @param color the color to add
     * @return the current message
     */
    public Message color(ChatColor color) {
        this.message().addProperty("color", color.name().toLowerCase());

        return this;
    }

    /**
     * Adds an action when the message is clicked
     *
     * @param event the clickevent to use
     * @return the current message
     */
    public Message clickEvent(ClickEvent event) {
        JsonObject obj = new JsonObject();

        obj.addProperty("action", event.action().toString());
        obj.addProperty("value", event.value());

        this.message().add("clickEvent", obj);
        return this;
    }

    /**
     * Adds an action when the message is hovered by the mouse
     *
     * @param event the hoverevent to use
     * @return the current message
     */
    public Message hoverEvent(HoverEvent event) {
        JsonObject obj = new JsonObject();

        obj.addProperty("action", event.action().toString());
        obj.addProperty("value", event.value());

        this.message().add("hoverEvent", obj);
        return this;
    }

    /**
     * The underlying JSON formatted string of the message
     *
     * @return the JSON text
     */
    public String asJson() {
        return MessageBuilder.GSON.toJson(this.message());
    }

    /**
     * The object representation of the JSON
     *
     * @return the message in object form of JSON
     */
    public JsonObject message() {
        return message;
    }
}
