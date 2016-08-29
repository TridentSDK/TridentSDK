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

public class ChatTest {

    @Test
    public void testPlain() {
        assertEquals(this.getJson("test"), ChatComponent.text("test").toString());
    }

    @Test
    public void testEmpty() {
        assertEquals(this.getJson(""), ChatComponent.empty().toString());
    }

    @Test
    public void testFormat() {
        ChatComponent cc = ChatComponent.fromFormat("\u00A7k\u00A7eHello! \u00A7r\u00A7cNice to meet you! :)");
        assertEquals("{\"text\":\"Hello! \",\"extra\":[{\"text\":\"Nice to meet you! :)\",\"obfuscated\":false,\"color\":\"red\"}],\"obfuscated\":true,\"color\":\"yellow\"}", cc.toString());
    }

    public String getJson(String base) {
        JsonObject msg = new JsonObject();
        msg.addProperty("text", base);
        return new Gson().toJson(msg);
    }
}
