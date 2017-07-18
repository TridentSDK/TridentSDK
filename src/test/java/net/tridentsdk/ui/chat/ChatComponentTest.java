/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2017 The TridentSDK Team
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
package net.tridentsdk.ui.chat;

import org.hjson.JsonObject;
import org.hjson.JsonValue;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChatComponentTest {

    @Test
    public void testPlain() {
        assertEquals(JsonValue.readJSON("{\"text\":\"test\"}"), ChatComponent.text("test").asJson());
    }

    @Test
    public void testEmpty() {
        assertEquals(JsonValue.readJSON("{\"text\":\"\"}"), ChatComponent.empty().asJson());
    }

    @Test
    public void testFromFormatString() {
        ChatComponent cc = ChatComponent.fromFormat("\u00A7e\u00A7kHello! \u00A7r\u00A7cNice to meet you! :)");
        assertEquals(JsonValue.readJSON("{\"text\":\"Hello! \",\"extra\":[{\"text\":\"Nice to meet you! :)\",\"obfuscated\":false,\"color\":\"red\"}],\"obfuscated\":true,\"color\":\"yellow\"}"), cc.asJson());
    }

    @Test
    public void testFromFormatString2() {
        ChatComponent cc = ChatComponent.fromFormat("\u00A7la\u00A7mb\u00A7nc\u00A7od\u00A7re");
        ChatComponent cc2 = ChatComponent.create()
                .setBold(true).setText("a")
                .addExtra(ChatComponent.create().setStrikethrough(true).setText("b"))
                .addExtra(ChatComponent.create().setUnderlined(true).setText("c"))
                .addExtra(ChatComponent.create().setItalic(true).setText("d"))
                .addExtra(ChatComponent.create().setBold(false).setItalic(false).setStrikethrough(false).setUnderlined(false).setText("e"));
        assertEquals(cc2, cc);
    }

    @Test
    public void testTranslateWith() {
        assertEquals(JsonValue.readJSON("{\"translate\":\"chat.type.text\",\"with\":[\"my awesome message\"]}"), ChatComponent.create().setTranslate("chat.type.text").addWith("my awesome message").asJson());
    }

    @Test
    public void testColors() {
        JsonObject json = ChatComponent.create().setColor(ChatColor.BLUE).setBold(true).setItalic(true).setUnderlined(false).asJson().asObject();
        assertNotNull(json.get("color"));
        assertNotNull(json.get("bold"));
        assertNotNull(json.get("italic"));
        assertNotNull(json.get("underlined"));
        assertNull(json.get("obfuscated"));
        assertEquals("blue", json.get("color").asString());
        assertTrue(json.get("bold").asBoolean());
        assertTrue(json.get("italic").asBoolean());
        assertFalse(json.get("underlined").asBoolean());
        assertEquals(JsonValue.readJSON("{\"bold\":true,\"italic\":true,\"underlined\":false,\"color\":\"blue\"}"), json);
    }

    @Test
    public void testGettersAndSetters() {
        ChatComponent cc = ChatComponent.create();
        assertEquals("my text", cc.setText("my text").getText());
        assertEquals("my translate", cc.setTranslate("my translate").getTranslate());

        ChatComponent childExtra = ChatComponent.create().setText("childExtra");
        ChatComponent childWith = ChatComponent.create().setText("childWith");
        ChatComponent subExtra = ChatComponent.create().setText("subExtra").addExtra(childExtra);
        ChatComponent subWith = ChatComponent.create().setText("subWith").addWith(childWith);
        cc.addExtra(subExtra).addWith(subWith).addWith("yo");
        assertTrue(cc.hasWith(subWith, false));
        assertTrue(cc.hasExtra(subExtra, false));
        assertFalse(cc.hasExtra(subWith, false));
        assertFalse(cc.hasWith(subExtra, false));

        assertTrue(cc.hasWith(subWith, true));
        assertTrue(cc.hasExtra(subExtra, true));
        assertFalse(cc.hasExtra(subWith, true));
        assertFalse(cc.hasWith(subExtra, true));

        assertFalse(cc.hasWith(childWith, false));
        assertFalse(cc.hasExtra(childExtra, false));
        assertFalse(cc.hasExtra(childWith, true));
        assertFalse(cc.hasWith(childExtra, true));

        assertTrue(cc.hasWith(childWith, true));
        assertTrue(cc.hasExtra(childExtra, true));
        assertFalse(cc.hasExtra(childWith, true));
        assertFalse(cc.hasWith(childExtra, true));

        assertEquals(2, cc.getWith().size());
        assertEquals(1, cc.getExtra().size());
        assertTrue(cc.getWith().contains(subWith));
        assertTrue(cc.getExtra().contains(subExtra));

        assertEquals(1, subWith.getWith().size());
        assertEquals(1, subExtra.getExtra().size());
        assertTrue(subWith.getWith().contains(childWith));
        assertTrue(subExtra.getExtra().contains(childExtra));

        subWith.addWith("yo").addExtra("yo");

        assertEquals("my score username", cc.setScoreUsername("my score username").getScoreUsername());
        assertEquals("my score objective", cc.setScoreObjective("my score objective").getScoreObjective());
        assertEquals("my insertion", cc.setInsertion("my insertion").getInsertion());

        assertTrue(cc.setBold(true).isBold());
        assertTrue(cc.setItalic(true).isItalic());
        assertTrue(cc.setUnderlined(true).isUnderlined());
        assertTrue(cc.setStrikethrough(true).isStrikethrough());
        assertTrue(cc.setObfuscated(true).isObfuscated());
        assertFalse(cc.setBold(false).isBold());
        assertFalse(cc.setItalic(false).isItalic());
        assertFalse(cc.setUnderlined(false).isUnderlined());
        assertFalse(cc.setStrikethrough(false).isStrikethrough());
        assertFalse(cc.setObfuscated(false).isObfuscated());

        for (ChatColor c : ChatColor.values()) {
            assertEquals(c, cc.setColor(c).getColor());
        }

        assertEquals("my selector", cc.setSelector("my selector").getSelector());

        ClickEvent clickEvent = ClickEvent.of(ClickAction.RUN_COMMAND, "/say hello");
        HoverEvent hoverEvent = HoverEvent.text("hello!");

        assertEquals(clickEvent, cc.setClickEvent(clickEvent).getClickEvent());
        assertEquals(hoverEvent, cc.setHoverEvent(hoverEvent).getHoverEvent());

        assertEquals(cc, ChatComponent.fromJson(cc.asJson().asObject()));
        assertEquals(cc.asJson(), JsonValue.readJSON(cc.toString()));
    }

}
