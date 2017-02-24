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

import net.tridentsdk.base.Substance;
import net.tridentsdk.inventory.Item;
import org.json.JSONObject;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

/**
 * @author Nick Robson
 */
public class HoverEventTest {
    @Test
    public void testTextJson() {
        assertEquals(new JSONObject("{\"action\":\"show_text\",\"value\":{\"text\":\"hello!\"}}").toMap(), HoverEvent.text("hello!").asJson().toMap());

        ChatComponent cc = ChatComponent.text("hi there").setColor(ChatColor.AQUA);
        assertEquals(new JSONObject("{\"action\":\"show_text\",\"value\":{\"text\":\"hi there\",\"color\":\"aqua\"}}").toMap(), HoverEvent.text(cc).asJson().toMap());
    }

    @Test
    public void testAchievementJson() {
        assertEquals(new JSONObject("{\"action\":\"show_achievement\",\"value\":\"the achievement\"}").toMap(), HoverEvent.achievement("the achievement").asJson().toMap());
    }

    @Test
    public void testJsonValidity() {
        HoverEvent event = HoverEvent.text("hi there");
        assertEquals(event.getAction(), HoverEvent.fromJson(event.asJson()).getAction());
        assertEquals(event.getValue(), HoverEvent.fromJson(event.asJson()).getValue());
    }

    @Test
    public void testItem() {
        Item item = Mockito.mock(Item.class);
        Mockito.when(item.getSubstance()).thenReturn(Substance.AIR);

        HoverEvent event = HoverEvent.item(item);
        assertEquals(event.getValue(), HoverEvent.fromJson(event.asJson()).getValue());
    }
}
