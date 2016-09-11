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
package net.tridentsdk.ui.bossbar;

import net.tridentsdk.Impl;

/**
 * This class manages boss bars.
 *
 * <p>The instance of this manager can be obtained using
 * the static factory {@link #getInstance()}.</p>
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
public interface BossBarManager {

    /**
     * Get the singleton instance of the Boss Bar manager
     *
     * @return The singleton Boss Bar manager
     */
    static BossBarManager getInstance(){
        return Impl.get().getBossBarManager();
    }

    /**
     * Gets whether or not a given boss bar is the default one.
     *
     * @param bar The boss bar.
     *
     * @return True iff it is a default boss bar.
     */
    boolean isDefault(BossBar bar);

    /**
     * Get a new boss bar.
     *
     * @return A new boss bar.
     */
    BossBar newBossBar();

}
