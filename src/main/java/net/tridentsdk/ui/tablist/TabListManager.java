package net.tridentsdk.ui.tablist;

import net.tridentsdk.Impl;

/**
 * This class manages the player's tab list.
 *
 * <p>The instance of this manager can be obtained using
 * the
 * static factory {@link #getInstance()}.</p>
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
public interface TabListManager {
    /**
     * Obtain an instance of the TabList Manager
     *
     * @return an instance of TabList Manager
     */
    static TabListManager getInstance(){
        return Impl.get().tabListManager();
    }

    /**
     * Obtain the global tablist shared between all players.
     *
     * @return the global tablist
     */
    TabList getGlobalTabList();

    /**
     * Obtain a new tablist instance.
     *
     * @return a new tablist instance.
     */
    TabList newTabList();
}