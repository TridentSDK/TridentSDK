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

import net.tridentsdk.factory.Factories;
import net.tridentsdk.util.TridentLogger;

import java.util.Map;

/**
 * Manages data channels for sending information over the pipeline
 *
 * @author The TridentSDK Team
 */
public final class ChannelHandler {
    private static final ChannelHandler INSTANCE = new ChannelHandler();

    private final Map<String, PluginChannel> channels = Factories.collect().createMap();

    private ChannelHandler() {
    }

    /**
     * Obtains the static instance of the channel handler
     *
     * @return the channel handler instance
     */
    public static ChannelHandler getInstance() {
        return INSTANCE;
    }

    /**
     * Registers a channel in the listings
     *
     * @param name the name of the channel
     * @param channel the channel to register
     */
    public void register(String name, PluginChannel channel) {
        if (this.channels.containsKey(name)) {
            TridentLogger.error(new UnsupportedOperationException("Cannot register 2 channels of the same name"));
        }

        this.channels.put(name, channel);
    }

    /**
     * Removes a channel if it exists in the listings
     *
     * @param name the name of the channel to remove
     */
    public void unregister(String name) {
        this.channels.remove(name);
    }

    /**
     * Find a channel by its name
     *
     * @param name the name to find the channel by
     * @return the channel having the specified name
     */
    public PluginChannel forChannel(String name) {
        return this.channels.get(name);
    }
}
