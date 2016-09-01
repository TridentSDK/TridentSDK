package net.tridentsdk.chat;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
public enum ClientChatMode {

    CHAT_AND_COMMANDS(0),
    COMMANDS_ONLY(1),
    NONE(2);

    @Getter
    private final int data;

    private ClientChatMode(int data) {
        this.data = data;
    }

    private static final Map<Integer, ClientChatMode> dataToMode = new HashMap<>();

    public static ClientChatMode of(int data) {
        ClientChatMode mode = dataToMode.get(data);
        if (mode == null) {
            throw new IllegalArgumentException("no client chat mode with id=" + data);
        }
        return mode;
    }

    static {
        for (ClientChatMode mode : values()) {
            dataToMode.put(mode.data, mode);
        }
    }

}
