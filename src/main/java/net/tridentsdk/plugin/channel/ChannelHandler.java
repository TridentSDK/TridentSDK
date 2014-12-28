/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2014 The TridentSDK Team
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

package net.tridentsdk.plugin.channel;

import net.tridentsdk.util.TridentLogger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class ChannelHandler {
    private static final ChannelHandler INSTANCE = new ChannelHandler();

    private final Map<String, PluginChannel> channels = new ConcurrentHashMap<>();

    private ChannelHandler() {
    }

    public static ChannelHandler getInstance() {
        return INSTANCE;
    }

    public void registerChannel(String name, PluginChannel channel) {
        if (this.channels.containsKey(name)) {
            TridentLogger.error(
                    new UnsupportedOperationException("Only TridentSDK is allowed to execute this method!"));
        }

        this.channels.put(name, channel);
    }

    public void unregisterChannel(String name) {
        this.channels.remove(name);
    }

    public PluginChannel getPluginChannel(String name) {
        return this.channels.get(name);
    }
}
