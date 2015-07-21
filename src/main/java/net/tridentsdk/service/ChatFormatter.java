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
package net.tridentsdk.service;

import net.tridentsdk.entity.living.Player;
import net.tridentsdk.plugin.Plugin;
import net.tridentsdk.registry.Registry;

/**
 * Handles server side chat communication
 *
 * <p>To access this handler, use this code:
 * <pre><code>
 *     ChatFormatter handler = Registered.chatFormatter();
 * </code></pre></p>
 *
 * @author The TridentSDK Team
 * @since 0.3-alpha-DP
 */
public interface ChatFormatter extends Registry<ChatIdentityFormatter> {
    /**
     * Sets the provider of the chat format, performing the default overriding logic of the original provider
     *
     * <p>The provider is notified before it is replaced, so do not try to replace the provider in the
     * overriden method</p>
     *
     * @param provider the provider to use
     * @param plugin the plugin that registers the new provider
     */
    void setFormatter(ChatIdentityFormatter provider, Plugin plugin);

    /**
     * Obtains the formatted string, not including the message that will be sent
     *
     * @param message the default chat identifier
     * @param player the player that is sending the message
     * @return the formatted string from the provider
     */
    String format(String message, Player player);
}
