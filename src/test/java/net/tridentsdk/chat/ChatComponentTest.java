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

import static org.junit.Assert.*;

public class ChatComponentTest {

    @Test
    public void testPlain() {
        assertEquals(new Gson().fromJson("{\"text\":\"test\"}", JsonObject.class), ChatComponent.text("test").asJson());
    }

    @Test
    public void testEmpty() {
        assertEquals(new Gson().fromJson("{\"text\":\"\"}", JsonObject.class), ChatComponent.empty().asJson());
    }

    @Test
    public void testFromFormatString() {
        ChatComponent cc = ChatComponent.fromFormat("\u00A7k\u00A7eHello! \u00A7r\u00A7cNice to meet you! :)");
        assertEquals(new Gson().fromJson("{\"text\":\"Hello! \",\"extra\":[{\"text\":\"Nice to meet you! :)\",\"obfuscated\":false,\"color\":\"red\"}],\"obfuscated\":true,\"color\":\"yellow\"}", JsonObject.class), cc.asJson());
    }

    @Test
    public void testTranslateWith() {
        assertEquals(new Gson().fromJson("{\"translate\":\"chat.type.text\",\"with\":[\"my awesome message\"]}", JsonObject.class), ChatComponent.create().setTranslate("chat.type.text").addWith("my awesome message").asJson());
    }

    @Test
    public void testColors() {
        JsonObject json = ChatComponent.create().setColor(ChatColor.BLUE).setBold(true).setItalic(true).setUnderlined(false).asJson().getAsJsonObject();
        assertTrue(json.has("color"));
        assertTrue(json.has("bold"));
        assertTrue(json.has("italic"));
        assertTrue(json.has("underlined"));
        assertFalse(json.has("obfuscated"));
        assertEquals("blue", json.get("color").getAsString());
        assertTrue(json.get("bold").getAsBoolean());
        assertTrue(json.get("italic").getAsBoolean());
        assertFalse(json.get("underlined").getAsBoolean());
        assertEquals(new Gson().fromJson("{\"bold\":true,\"italic\":true,\"underlined\":false,\"color\":\"blue\"}", JsonObject.class), json);
    }

}
