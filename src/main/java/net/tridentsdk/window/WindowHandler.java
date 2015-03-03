package net.tridentsdk.window;

import java.util.Collection;

/**
 * Handles windows opened and closed by the server
 *
 * @author The TridentSDK Team
 */
public interface WindowHandler {
    /**
     * Gets a window by its ID
     *
     * @param id the ID of a window
     * @return the window with the ID, or {@code null} if it doesn't exist
     */
    public Window windowBy(int id);

    /**
     * Registers the window with the manager
     *
     * @param window the window to be registered
     */
    public void registerWindow(Window window);

    /**
     * Gets all registered windows with the manager
     *
     * @return the windows registered
     */
    public Collection<Window> windows();
}
