package net.tridentsdk.registry;

import net.tridentsdk.Implementation;
import net.tridentsdk.plugin.channel.ChannelHandler;
import net.tridentsdk.window.WindowHandler;

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
    public static ChannelHandler channels() {
        return impl.chanHandler();
    }

    /**
     * Obtains the window handler
     *
     * @return the window handler
     */
    public static WindowHandler windows() {
        return impl.winHandler();
    }
}
