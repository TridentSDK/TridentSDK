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
package net.tridentsdk.chat;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.tridentsdk.base.Substance;
import net.tridentsdk.inventory.Item;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

/**
 * @author Nick Robson
 */
public class HoverEventTest {
    @Test
    public void testTextJson() {
        assertEquals(new Gson().fromJson("{\"action\":\"show_text\",\"value\":{\"text\":\"hello!\"}}", JsonObject.class), HoverEvent.text("hello!").asJson());

        ChatComponent cc = ChatComponent.text("hi there").setColor(ChatColor.AQUA);
        assertEquals(new Gson().fromJson("{\"action\":\"show_text\",\"value\":{\"text\":\"hi there\",\"color\":\"aqua\"}}", JsonObject.class), HoverEvent.text(cc).asJson());
    }

    @Test
    public void testAchievementJson() {
        assertEquals(new Gson().fromJson("{\"action\":\"show_achievement\",\"value\":\"the achievement\"}", JsonObject.class), HoverEvent.achievement("the achievement").asJson());
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