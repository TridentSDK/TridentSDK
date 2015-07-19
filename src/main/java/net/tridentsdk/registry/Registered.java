package net.tridentsdk.registry;

import net.tridentsdk.concurrent.Scheduler;
import net.tridentsdk.plugin.channel.PluginChannels;
import net.tridentsdk.window.Inventories;

/**
 * Allows access to the instance of various server objects
 *
 * @author The TridentSDK Team
 */
public class Registered {
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
        return impl.chanHandler();
    }

    /**
     * Obtains the inventory handler
     *
     * @return the inventory handler
     */
    public static Inventories inventories() {
        return impl.winHandler();
    }

    /**
     * Obtains the scheduler
     *
     * @return the scheduler
     */
    public static Scheduler tasks() {
        return impl.scheduler();
    }
}
