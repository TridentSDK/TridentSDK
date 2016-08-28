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
import com.google.gson.JsonPrimitive;

public class ClickEvent {

    private Event event;
    private ClickAction action;

    private ClickEvent(ClickAction action, String text) {
        this.event = new Event(action.name().toLowerCase(), new JsonPrimitive(text));
        this.action = action;
    }

    public ClickAction getAction() {
        return action;
    }

    public String getValue() {
        return event.getValue().getAsString();
    }

    public JsonObject asJson() {
        return event.asJson();
    }

    public static ClickEvent of(ClickAction action, String text) {
        return new ClickEvent(action, text);
    }

}
