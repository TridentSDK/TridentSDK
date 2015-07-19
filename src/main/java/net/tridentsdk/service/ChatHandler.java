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

import net.tridentsdk.Trident;
import net.tridentsdk.entity.living.Player;
import net.tridentsdk.plugin.Plugin;
import net.tridentsdk.util.TridentLogger;

/**
 * Handles server side chat communication
 *
 * <p>To access this handler, use this code:
 * <pre><code>
 *     ChatHandler handler = Handler.forChat();
 * </code></pre></p>
 *
 * @author The TridentSDK Team
 */
public class ChatHandler {
    private volatile ChatIdentityFormatter provider = new ChatIdentityFormatter() {
        @Override
        public String format(String message, Player sender) {
            return "%n%d";
        }

        @Override
        public void overriden(ChatIdentityFormatter other, Plugin overrider) {
            TridentLogger.warn("Trident default chat overriden by " + overrider);
        }
    };

    /**
     * Do not instantiate
     *
     * <p>To access this handler, use this code:
     * <pre><code>
     *     ChatHandler handler = Handler.forChat();
     * </code></pre></p>
     */
    public ChatHandler() {
        if (!Trident.isTrident())
            TridentLogger.error(new IllegalAccessException("This class should only be instantiated by Trident"));
    }

    /**
     * Sets the provider of the chat format, performing the default overriding logic of the original provider
     *
     * <p>The provider is notified before it is replaced, so do not try to replace the provider in the
     * overriden method</p>
     *
     * @param provider the provider to use
     * @param plugin the plugin that registers the new provider
     */
    public void setFormatter(ChatIdentityFormatter provider, Plugin plugin) {
        this.provider.overriden(provider, plugin);
        this.provider = provider;
    }

    /**
     * Obtains the formatted string, not including the message that will be sent
     *
     * @param message the default chat identifier
     * @param player the player that is sending the message
     * @return the formatted string from the provider
     */
    public String format(String message, Player player) {
        return provider.format(message, player);
    }
}