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
package net.tridentsdk.registry;

import com.google.common.base.Preconditions;
import net.tridentsdk.Trident;
import net.tridentsdk.concurrent.Scheduler;
import net.tridentsdk.docs.InternalUseOnly;
import net.tridentsdk.event.Events;
import net.tridentsdk.inventory.Inventories;
import net.tridentsdk.plugin.Plugins;
import net.tridentsdk.plugin.channel.PluginChannels;
import net.tridentsdk.plugin.cmd.Commands;
import net.tridentsdk.service.ChatFormatter;
import net.tridentsdk.service.Transactions;
import net.tridentsdk.util.TridentLogger;
import net.tridentsdk.world.World;

import java.util.Map;

/**
 * Allows access to the instance of various server objects
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 */
public class Registered {
    private static final Events EVENTS = Events.create();
    private static final Plugins PLUGINS = new Plugins();
    private static final Commands CMDS = new Commands();
    private static final ChatFormatter CHAT = new ChatFormatter();
    private static final Transactions TRANSACTIONS = new Transactions();

    private static volatile Implementation impl;

    @InternalUseOnly
    public static void setProvider(Implementation implementation) {
        Preconditions.checkArgument(impl == null, "You may not set the provider of Registered");
        impl = implementation;
    }

    /**
     * Obtains the static instance of the channel handler
     *
     * @return the channel handler instance
     */
    public static PluginChannels channels() {
        return impl.channels();
    }

    /**
     * Obtains access to the players on the server
     *
     * @return the players access
     */
    public static Players players() {
        return impl.players();
    }

    /**
     * Obtains the loaded worlds, name to world pairs
     *
     * @return the worlds that have been loaded
     */
    public static Map<String, World> worlds() {
        return impl.worlds();
    }

    /**
     * Obtains the inventory handler
     *
     * @return the inventory handler
     */
    public static Inventories inventories() {
        return impl.inventories();
    }

    /**
     * Obtains the scheduler
     *
     * @return the scheduler
     */
    public static Scheduler tasks() {
        return impl.scheduler();
    }

    /**
     * Obtains the event handler
     *
     * @return the event handler
     */
    public static Events events() {
        return EVENTS;
    }

    /**
     * Obtains the plugin handler
     *
     * @return the plugin handler
     */
    public static Plugins plugins() {
        return PLUGINS;
    }

    /**
     * Obtains the command handler
     *
     * @return the command handler
     */
    public static Commands commands() {
        return CMDS;
    }

    /**
     * Obtains the root logger which contains the collection of all the loggers
     *
     * @return the root collection of loggers
     */
    public static TridentLogger loggers() {
        return Trident.instance().logger();
    }

    /**
     * Obtains the chat handler
     *
     * @return the chat handler
     */
    public static ChatFormatter chatFormatter() {
        return CHAT;
    }

    /**
     * Obtains the transaction handler
     *
     * @return the transaction handler
     */
    public static Transactions transactions() {
        return TRANSACTIONS;
    }
}
