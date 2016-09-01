package net.tridentsdk.chat;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Nick Robson
 */
public class ClickEventTest {

    @Test
    public void testRunCommandJson() {
        assertEquals("{\"action\":\"run_command\",\"value\":\"/say hello\"}", ClickEvent.of(ClickAction.RUN_COMMAND, "/say hello").asJson().toString());
    }

    @Test
    public void testSuggestCommandJson() {
        assertEquals("{\"action\":\"suggest_command\",\"value\":\"/say hello\"}", ClickEvent.of(ClickAction.SUGGEST_COMMAND, "/say hello").asJson().toString());
    }

    @Test
    public void testOpenUrlJson() {
        assertEquals("{\"action\":\"open_url\",\"value\":\"http://google.com\"}", ClickEvent.of(ClickAction.OPEN_URL, "http://google.com").asJson().toString());
    }

    @Test
    public void testOpenFileJson() {
        assertEquals("{\"action\":\"open_file\",\"value\":\"nope\"}", ClickEvent.of(ClickAction.OPEN_FILE, "nope").asJson().toString());
    }

}
