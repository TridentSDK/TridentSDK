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

import com.google.common.collect.ForwardingCollection;
import com.google.common.collect.ImmutableList;
import net.tridentsdk.Trident;
import net.tridentsdk.registry.Registry;
import net.tridentsdk.util.TridentLogger;

import javax.annotation.concurrent.ThreadSafe;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Manages data channels for sending information over the pipeline
 *
 * <p>To access this handler, use this code:
 * <pre><code>
 *     PluginChannels handler = Registered.channels();
 * </code></pre></p>
 *
 * @author The TridentSDK Team
 * @since 0.3-alpha-DP
 */
@ThreadSafe
public abstract class PluginChannels extends ForwardingCollection<PluginChannel> implements Registry<PluginChannel> {
    private final Map<String, PluginChannel> channels = new ConcurrentHashMap<>();

    /**
     * Do not instantiate
     *
     * <p>To access this handler, use this code:
     * <pre><code>
     *     PluginChannels handler = Registered.channels();
     * </code></pre></p>
     */
    public PluginChannels() {
        if (!Trident.isTrident()) {
            TridentLogger.error(new IllegalAccessException("Only Trident should instantiate this class"));
        }
    }

    /**
     * Send a plugin message
     *
     * @param channel name of the channel
     * @param data    the data to send
     */
    protected abstract void sendPluginMessage(String channel, byte... data);

    /**
     * Registers a channel in the listings
     *
     * @param name    the name of the channel
     * @param channel the channel to register
     */
    public void register(String name, PluginChannel channel) {
        if (this.channels.containsKey(name)) {
            TridentLogger.error(new UnsupportedOperationException("Cannot register 2 channels of the same name"));
            return;
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
    public PluginChannel fromName(String name) {
        return this.channels.get(name);
    }

    @Override
    protected Collection<PluginChannel> delegate() {
        return ImmutableList.copyOf(channels.values());
    }
}
