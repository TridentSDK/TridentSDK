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

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.annotation.concurrent.ThreadSafe;

/**
 * This class represents a chat message that may be sent to
 * a player.
 *
 * @author TridentSDK
 * @since 0.3-alpha-DP
 */
@ThreadSafe
public class Chat {
    /**
     * The GSON object used for chat serialization
     */
    private static final Gson GSON = new Gson();
    /**
     * An empty chat object
     */
    private static final Chat EMPTY = new Chat("");

    /**
     * The base string for this chat object
     */
    private final String base;

    // Use static factories
    private Chat(String base) {
        this.base = base;
    }

    /**
     * Creates a new chat message without any special
     * formatting or colors.
     *
     * @param text the text
     * @return the chat object with the given text
     */
    public static Chat plain(String text) {
        return new Chat(text);
    }

    /**
     * Obtains an empty chat object.
     *
     * @return a chat object with no text
     */
    public static Chat empty() {
        return EMPTY;
    }

    /**
     * Converts the chat message to a JSON String.
     *
     * @return the chat message as JSON
     */
    public String asJson() {
        JsonObject msg = new JsonObject();
        msg.addProperty("text", this.base);

        return GSON.toJson(msg);
    }
}