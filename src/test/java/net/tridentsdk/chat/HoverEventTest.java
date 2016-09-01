package net.tridentsdk.chat;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Nick Robson
 */
public class HoverEventTest {

    @Test
    public void testTextJson() {
        assertEquals("{\"action\":\"show_text\",\"value\":{\"text\":\"hello!\"}}", HoverEvent.text("hello!").asJson().toString());

        ChatComponent cc = ChatComponent.text("hi there").setColor(ChatColor.AQUA);
        assertEquals("{\"action\":\"show_text\",\"value\":{\"text\":\"hi there\",\"color\":\"aqua\"}}", HoverEvent.text(cc).asJson().toString());
    }

    @Test
    public void testAchievementJson() {
        assertEquals("{\"action\":\"show_achievement\",\"value\":\"the achievement\"}", HoverEvent.achievement("the achievement").asJson().toString());
    }

}
