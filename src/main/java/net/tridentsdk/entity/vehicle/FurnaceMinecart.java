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

package net.tridentsdk.entity.vehicle;

import net.tridentsdk.entity.traits.InventoryHolder;

/**
 * Represents a Furnace Minecart
 *
 * @author TridentSDK Team
 */
public interface FurnaceMinecart extends InventoryHolder, MinecartBase {
    /**
     * The number of fuel ticks this Furnace Minecart has
     *
     * @return the number of fuel ticks
     */
    int getFuelTicks();

    /**
     * Set the number of fuel ticks this Furnace Minecart has
     *
     * @param ticks the number of fuel ticks
     */
    void setFuelTicks(int ticks);
}
