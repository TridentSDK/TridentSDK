/*
 * Copyright (c) 2014, The TridentSDK Team
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     1. Redistributions of source code must retain the above copyright
 *        notice, this list of conditions and the following disclaimer.
 *     2. Redistributions in binary form must reproduce the above copyright
 *        notice, this list of conditions and the following disclaimer in the
 *        documentation and/or other materials provided with the distribution.
 *     3. Neither the name of the The TridentSDK Team nor the
 *        names of its contributors may be used to endorse or promote products
 *        derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL The TridentSDK Team BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package net.tridentsdk.api.msg;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.tridentsdk.api.ChatColor;
import net.tridentsdk.api.entity.living.Player;

// TODO: JavaDoc
public final class MessageBuilder {
    static final Gson GSON = new Gson();

    private final JsonObject obj;
    private final JsonArray extra;
    private Message buildingObject;

    public MessageBuilder(String message) {
        this.obj = new JsonObject();
        this.extra = new JsonArray();

        // setup required properties
        this.obj.addProperty("text", "");
        this.buildingObject = new Message().text(message);
    }

    public MessageBuilder color(ChatColor color) {
        this.buildingObject.color(color);
        return this;
    }

    public MessageBuilder link(String url) {
        this.buildingObject.clickEvent(new ClickEvent()
                .action(ClickEvent.ClickAction.OPEN_URL)
                .value(url));

        return this;
    }

    public MessageBuilder file(String file) {
        this.buildingObject.clickEvent(new ClickEvent()
                .action(ClickEvent.ClickAction.OPEN_FILE)
                .value(file));

        return this;
    }

    public MessageBuilder hover(String message) {
        this.buildingObject.hoverEvent(new HoverEvent()
                .action(HoverEvent.HoverAction.SHOW_TEXT)
                .value(message));

        return this;
    }

    public MessageBuilder then(String message) {
        this.extra.add(this.buildingObject.message);
        this.buildingObject = new Message().text(message);

        return this;
    }

    public MessageBuilder then(Message message) {
        this.extra.add(this.buildingObject.message);
        this.buildingObject = message;

        return this;
    }

    /**
     * Completes the building of the message, after this call no change should be made. If any change were to be made,
     * an NPE will be thrown
     */
    public MessageBuilder build() {
        this.obj.add("extra", this.extra);
        this.buildingObject = null;

        return this;
    }

    @Override
    public String toString() {
        return MessageBuilder.GSON.toJson(this.obj);
    }

    public String toJson() {
        return this.toString();
    }

    public MessageBuilder sendTo(Player... players) {
        for (Player p : players) {
            // TODO: send message
        }

        return this;
    }
}
