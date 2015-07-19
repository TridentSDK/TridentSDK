package net.tridentsdk.registry;

import net.tridentsdk.concurrent.Scheduler;
import net.tridentsdk.event.Events;
import net.tridentsdk.plugin.PluginHandler;
import net.tridentsdk.plugin.channel.PluginChannels;
import net.tridentsdk.plugin.cmd.Commands;
import net.tridentsdk.service.ChatFormatter;
import net.tridentsdk.service.Transactions;
import net.tridentsdk.window.Inventories;

/**
 * Allows access to the instance of various server objects
 *
 * @author The TridentSDK Team
 */
public class Registered {
    public static final Events EVENT_HANDLER = Events.create();
    public static final PluginHandler PLUGIN_HANDLER = new PluginHandler();
    public static final Commands COMMAND_HANDLER = new Commands();
    public static final ChatFormatter CHAT_HANDLER = new ChatFormatter();
    public static final Transactions TRANSACTION_HANDLER = new Transactions();

    private static Implementation impl;

    public static void setProvider(Implementation implementation) {
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
    public static Events forEvents() {
        return EVENT_HANDLER;
    }

    /**
     * Obtains the plugin handler
     *
     * @return the plugin handler
     */
    public static PluginHandler plugins() {
        return PLUGIN_HANDLER;
    }

    /**
     * Obtains the command handler
     *
     * @return the command handler
     */
    public static Commands commands() {
        return COMMAND_HANDLER;
    }

    /**
     * Obtains the chat handler
     *
     * @return the chat handler
     */
    public static ChatFormatter chatFormatter() {
        return CHAT_HANDLER;
    }

    /**
     * Obtains the transaction handler
     *
     * @return the transaction handler
     */
    public static Transactions transactions() {
        return TRANSACTION_HANDLER;
    }
}
