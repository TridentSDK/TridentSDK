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
package net.tridentsdk;

import net.tridentsdk.event.EventHandler;
import net.tridentsdk.plugin.PluginHandler;
import net.tridentsdk.plugin.cmd.CommandHandler;
import net.tridentsdk.service.ChatHandler;
import net.tridentsdk.service.TransactionHandler;

/**
 * Contains the singleton instances for all the Trident handlers
 *
 * @author The TridentSDK Team
 */
public final class Handler {
    private static final EventHandler EVENT_HANDLER = EventHandler.create();
    private static final PluginHandler PLUGIN_HANDLER = new PluginHandler();
    private static final CommandHandler COMMAND_HANDLER = new CommandHandler();
    private static final ChatHandler CHAT_HANDLER = new ChatHandler();
    private static final TransactionHandler TRANSACTION_HANDLER = new TransactionHandler();

    private Handler() {
    }

    /**
     * Obtains the event handler
     *
     * @return the event handler
     */
    public static EventHandler forEvents() {
        return EVENT_HANDLER;
    }

    /**
     * Obtains the plugin handler
     *
     * @return the plugin handler
     */
    public static PluginHandler forPlugins() {
        return PLUGIN_HANDLER;
    }

    /**
     * Obtains the command handler
     *
     * @return the command handler
     */
    public static CommandHandler forCommands() {
        return COMMAND_HANDLER;
    }

    /**
     * Obtains the chat handler
     *
     * @return the chat handler
     */
    public static ChatHandler forChat() {
        return CHAT_HANDLER;
    }

    /**
     * Obtains the transaction handler
     *
     * @return the transaction handler
     */
    public static TransactionHandler forTransactions() {
        return TRANSACTION_HANDLER;
    }
}
