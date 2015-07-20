/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2014 The TridentSDK Team
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
package net.tridentsdk.inventory;

import net.tridentsdk.registry.Registry;

/**
 * Handles windows opened and closed by the server
 * <p>
 * <p>To access this handler, use this code:
 * <pre><code>
 *     Inventories handler = Registered.inventories();
 * </code></pre></p>
 *
 * @author The TridentSDK Team
 * @since 0.3-alpha-DP
 */
public interface Inventories extends Registry<Inventory> {
    /**
     * Gets a inventory by its ID
     *
     * @param id the ID of a inventory
     * @return the inventory with the ID, or {@code null} if it doesn't exist
     */
    Inventory fromId(int id);

    /**
     * Registers the inventory with the manager
     *
     * @param window the inventory to be registered
     */
    void register(Inventory window);
}
