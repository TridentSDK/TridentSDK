/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2016 The TridentSDK Team
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