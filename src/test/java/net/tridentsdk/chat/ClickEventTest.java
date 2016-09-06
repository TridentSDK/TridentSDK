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

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Nick Robson
 */
public class ClickEventTest {

    @Test
    public void testRunCommandJson() {
        assertEquals(new Gson().fromJson("{\"action\":\"run_command\",\"value\":\"/say hello\"}", JsonObject.class), ClickEvent.of(ClickAction.RUN_COMMAND, "/say hello").asJson());
    }

    @Test
    public void testSuggestCommandJson() {
        assertEquals(new Gson().fromJson("{\"action\":\"suggest_command\",\"value\":\"/say hello\"}", JsonObject.class), ClickEvent.of(ClickAction.SUGGEST_COMMAND, "/say hello").asJson());
    }

    @Test
    public void testOpenUrlJson() {
        assertEquals(new Gson().fromJson("{\"action\":\"open_url\",\"value\":\"http://google.com\"}", JsonObject.class), ClickEvent.of(ClickAction.OPEN_URL, "http://google.com").asJson());
    }

    @Test
    public void testOpenFileJson() {
        assertEquals(new Gson().fromJson("{\"action\":\"open_file\",\"value\":\"nope\"}", JsonObject.class), ClickEvent.of(ClickAction.OPEN_FILE, "nope").asJson());
    }

    @Test
    public void testJsonValidity() {
        ClickEvent event = ClickEvent.of(ClickAction.RUN_COMMAND, "/say hi there");
        assertEquals(event.getAction(), ClickEvent.fromJson(event.asJson()).getAction());
        assertEquals(event.getValue(), ClickEvent.fromJson(event.asJson()).getValue());
    }

}
