package net.tridentsdk.ui.tablist;

import net.tridentsdk.Impl;

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
