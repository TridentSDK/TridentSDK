package net.tridentsdk.chat;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Nick Robson
 */
public class ChatTypeTest {

    @Test
    public void testChatTypes() {
        ChatType[] types = ChatType.values();
        assertEquals(types[0], ChatType.CHAT);
        assertEquals(types[1], ChatType.SYSTEM);
        assertEquals(types[2], ChatType.ABOVE_HOTBAR);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testError() {
        ChatType type = ChatType.values()[3];
        type.toString();
    }

}
