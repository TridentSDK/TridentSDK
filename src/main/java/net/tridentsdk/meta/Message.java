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
import net.tridentsdk.event.misc.ClickEvent;
import net.tridentsdk.event.misc.HoverEvent;

// TODO: JavaDoc
public final class Message {
    private final JsonObject message;

    public Message() {
        this.message = new JsonObject();
    }

    public Message text(String input) {
        this.getMessage().addProperty("text", input);
        return this;
    }

    public Message color(ChatColor color) {
        this.getMessage().addProperty("color", color.toString());

        return this;
    }

    public Message clickEvent(ClickEvent event) {
        JsonObject obj = new JsonObject();

        obj.addProperty("action", event.getAction().toString());
        obj.addProperty("value", event.getValue());

        this.getMessage().add("clickEvent", obj);
        return this;
    }

    public Message hoverEvent(HoverEvent event) {
        JsonObject obj = new JsonObject();

        obj.addProperty("action", event.getAction().toString());
        obj.addProperty("value", event.getValue());

        this.getMessage().add("hoverEvent", obj);
        return this;
    }

    public String toJson() {
        return MessageBuilder.GSON.toJson(this.getMessage());
    }

    public JsonObject getMessage() {
        return message;
    }
}
