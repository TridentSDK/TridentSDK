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

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import net.tridentsdk.Trident;
import net.tridentsdk.docs.InternalUseOnly;
import net.tridentsdk.util.TridentLogger;

import java.util.List;

/**
 * A channel which listens for data received by the opened pipeline
 *
 * @author The TridentSDK Team
 */
public abstract class PluginChannel {
    private final List<byte[]> history = Lists.newArrayList();

    @InternalUseOnly
    public void process(byte... message) {
        if (!Trident.isTrident()) {
            TridentLogger.error(new IllegalAccessException("Only TridentSDK is allowed to execute this method!"));
            return;
        }

        byte[] bytes = new byte[message.length - 1];
        System.arraycopy(message, 0, bytes, 0, message.length);

        this.history.add(bytes);
        this.onMessage(message);
    }

    /**
     * Overridden to perform a specified action upon reception of byte encoded messages
     *
     * @param message the message which was sent to this channel
     */
    public abstract void onMessage(byte... message);

    /**
     * Obtains the history of messages received by this channel
     *
     * @return an immutable list of bytes sent by this channel
     */
    public List<byte[]> history() {
        return ImmutableList.copyOf(this.history);
    }
}
