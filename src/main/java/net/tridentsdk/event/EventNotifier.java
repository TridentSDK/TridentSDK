package net.tridentsdk.event;

import net.tridentsdk.plugin.Plugin;

/**
 * Represents a tool which is used to notify event listeners
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 */
public interface EventNotifier {
    /**
     * Obtains the plugin that contains the event listener
     *
     * @return the plugin
     */
    Plugin plugin();

    /**
     * Obtains the instance of the listener which was registered
     *
     * @return the listener instance
     */
    Listener listener();

    /**
     * Obtains the class of the event type that this notifier will be calling listeners for
     *
     * @return the event type
     */
    Class<? extends Event> eventType();

    /**
     * Obtains the importance of the event listener
     *
     * @return the importance
     */
    Importance importance();

    /**
     * Notifies the listener
     *
     * @param event the event to notify the listener
     */
    void handle(Event event);
}
