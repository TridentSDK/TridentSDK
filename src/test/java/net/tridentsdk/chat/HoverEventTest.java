package net.tridentsdk.chat;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.Test;

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

}
