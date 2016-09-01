package net.tridentsdk.chat;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Nick Robson
 */
public class ChatColorTest {

    @Test
    public void testColorRegistration() {
        for (ChatColor c : ChatColor.values()) {
            assertEquals(c, ChatColor.getColor(c.getColorChar()));
        }
    }

    @Test
    public void testColorStrings() {
        String colorChar = "\u00A7";
        for (ChatColor c : ChatColor.values()) {
            assertEquals(colorChar + c.getColorChar(), c.toString());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testError() {
        ChatColor.getColor('g');
    }

}
