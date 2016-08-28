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

public class HoverEvent {

    private Event event;

    private HoverEvent(HoverAction action, JsonElement value) {
        this.event = new Event(action.name().toLowerCase(), value);
    }

    public JsonObject asJson() {
        return event.asJson();
    }

    public static HoverEvent text(String text) {
        return new HoverEvent(HoverAction.SHOW_TEXT, new JsonPrimitive(text));
    }

    public static HoverEvent text(JsonObject text) {
        return new HoverEvent(HoverAction.SHOW_TEXT, text);
    }

    public static HoverEvent achievement(String achievement) {
        return new HoverEvent(HoverAction.SHOW_ACHIEVEMENT, new JsonPrimitive(achievement));
    }

    /* TODO :: once items are implemented
    public static HoverEvent item(ItemStack item) {

    }
    */

}
