package net.tridentsdk.chat;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Nick Robson
 */
public class ClientChatModeTest {

    @Test
    public void testModeRegistration() {
        for (ClientChatMode mode : ClientChatMode.values()) {
            assertEquals(mode, ClientChatMode.of(mode.getData()));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testError() {
        ClientChatMode.of(3);
    }

}
