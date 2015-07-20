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

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.tridentsdk.entity.living.Player;

/**
 * Builds a formatted message using JSON to be sent to the player(s)
 *
 * @author The TridentSDK Team
 * @since 0.3-alpha-DP
 */
// FIXME
public final class MessageBuilder {
    static final Gson GSON = new Gson();

    private final JsonObject obj;
    private final JsonArray extra;
    private Message buildingObject;

    /**
     * Starts off with the text of the message
     *
     * @param message the text to send
     */
    public MessageBuilder(String message) {
        this.obj = new JsonObject();
        this.extra = new JsonArray();

        // setup required properties
        this.obj.addProperty("text", "");
        this.buildingObject = new Message().text(message);
    }

    /**
     * Sets the text color of the message
     *
     * @param color the color the message should be
     * @return the instance of this message builder
     */
    public MessageBuilder color(ChatColor color) {
        this.buildingObject.color(color);
        return this;
    }

    /**
     * Makes the message a clickable link
     *
     * @param url the URL to lead the player to when the message is clicked
     * @return the instance of this message builder
     */
    public MessageBuilder link(String url) {
        this.buildingObject.clickEvent(new ClickEvent().action(ClickEvent.ClickAction.OPEN_URL).value(url));

        return this;
    }

    /**
     * Makes the message a clickable link that opens a file on the player's client
     *
     * @param file the file to open, must be available on the client
     * @return the instance of this message builder
     */
    public MessageBuilder file(String file) {
        this.buildingObject.clickEvent(new ClickEvent().action(ClickEvent.ClickAction.OPEN_FILE).value(file));

        return this;
    }

    /**
     * Causes a popup to open when the the message is hovered over
     *
     * @param message the message in the popup
     * @return the instance of this message builder
     */
    public MessageBuilder hover(String message) {
        this.buildingObject.hoverEvent(new HoverEvent().action(HoverEvent.HoverAction.SHOW_TEXT).value(message));

        return this;
    }

    /**
     * Appends extra text to the end of this message
     *
     * @param message the message to be appended after the current text
     * @return the instance of this message builder
     */
    public MessageBuilder then(String message) {
        this.extra.add(this.buildingObject.message());
        this.buildingObject = new Message().text(message);

        return this;
    }

    /**
     * Appends extra text to the end of this message
     *
     * @param message the message to be appended after the current text
     * @return the instance of this message builder
     */
    public MessageBuilder then(Message message) {
        this.extra.add(this.buildingObject.message());
        this.buildingObject = message;

        return this;
    }

    /**
     * Completes the building of the message, after this call no change should be made. If any change were to be made,
     * an NPE will be thrown
     *
     * @return the finalized MessageBuilder, only {@link #asJson()} and
     * {@link #sendTo(net.tridentsdk.entity.living.Player...)} may be called on the returned object
     */
    public MessageBuilder build() {
        this.extra.add(buildingObject.message());
        this.obj.add("extra", this.extra);
        this.buildingObject = null;

        return this;
    }

    @Override
    public String toString() {
        return GSON.toJson(this.obj);
    }

    /**
     * The String format of the JSON to be sent as the formatted text
     *
     * @return the String format of the built JSON
     */
    public String asJson() {
        return this.toString();
    }

    /**
     * Sends the formatted text to the specified players
     *
     * @param players the players to send the formatted text
     * @return the instance of this message builder
     */
    public MessageBuilder sendTo(Player... players) {
        for (Player p : players) {
            p.sendRaw(asJson());
        }

        return this;
    }
}
