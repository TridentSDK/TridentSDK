package net.tridentsdk.event;

import net.tridentsdk.docs.InternalUseOnly;
import net.tridentsdk.plugin.Plugin;
import net.tridentsdk.registry.Registry;

/**
 * The access point to the server's event handler
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 */
public interface Events extends Registry<EventNotifier> {
    /**
     * Notifies listeners of the event type of the event to be fired
     *
     * @param event the event to fire
     */
    void fire(Event event);

    /**
     * Normally not needed to be used. Plugin listeners are automatically registered when they are loaded.
     *
     * @param listener the listener instance to use to register
     */
    @InternalUseOnly
    void registerListener(Plugin plugin, Listener listener);

    /**
     * Removes the listener from the caller queue, preventing it from being invoked
     *
     * @param cls the listener class to unregister
     */
    void unregister(Class<? extends Listener> cls);
}
