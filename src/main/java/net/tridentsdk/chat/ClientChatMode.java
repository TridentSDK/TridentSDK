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
