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
package net.tridentsdk.event.server;

import java.net.InetSocketAddress;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.Accessors;
import net.tridentsdk.event.Event;
import net.tridentsdk.ui.chat.ChatComponent;
import org.hjson.JsonArray;
import org.hjson.JsonObject;

/**
 * @author Nick Robson
 */
@Getter
@AllArgsConstructor
public class ServerPingEvent implements Event {

    private final InetSocketAddress pinger;
    private final ServerPingResponse response;

    @Data
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class ServerPingResponse {

        @NonNull
        private ServerPingResponseVersion version;

        @NonNull
        private ServerPingResponsePlayers players;

        @NonNull
        private ChatComponent description;
        private String serverIconBase64;

        public JsonObject asJson() {
            JsonObject json = new JsonObject();
            json.add("version", version.asJson());
            json.add("players", players.asJson());
            json.add("description", description.asJson());
            if (serverIconBase64 != null) {
                json.add("favicon", serverIconBase64);
            }
            return json;
        }

    }

    @Data
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class ServerPingResponseVersion {

        @NonNull
        private String name;
        private int protocol;

        public JsonObject asJson() {
            JsonObject json = new JsonObject();
            json.add("name", name);
            json.add("protocol", protocol);
            return json;
        }

    }

    @Data
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class ServerPingResponsePlayers {

        private int onlinePlayers;
        private int maxPlayers;

        @NonNull
        private ServerPingResponseSample[] samples;

        public ServerPingResponsePlayers setOnlinePlayers(int onlinePlayers) {
            if (onlinePlayers < 0)
                throw new IllegalArgumentException("invalid number of players");
            this.onlinePlayers = onlinePlayers;
            return this;
        }

        public ServerPingResponsePlayers setMaxPlayers(int maxPlayers) {
            if (maxPlayers < 0)
                throw new IllegalArgumentException("invalid number of players");
            this.maxPlayers = maxPlayers;
            return this;
        }

        public JsonObject asJson() {
            JsonObject json = new JsonObject();
            json.add("max", maxPlayers);
            json.add("online", onlinePlayers);
            JsonArray sampleArray = new JsonArray();
            for (ServerPingResponseSample sample : samples) {
                if (sample != null) {
                    sampleArray.add(sample.asJson());
                }
            }
            json.add("sample", sampleArray);
            return json;
        }

    }

    @Data
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class ServerPingResponseSample {

        @NonNull
        private String name;

        @NonNull
        private UUID uuid;

        public JsonObject asJson() {
            JsonObject json = new JsonObject();
            json.add("name", name);
            json.add("id", uuid.toString());
            return json;
        }

    }

}
