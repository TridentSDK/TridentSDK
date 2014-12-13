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

import net.tridentsdk.base.TileSnapshot;

/**
 * Represents a Command Block Minecart
 *
 * @author TridentSDK Team
 */
public interface CommandMinecart extends MinecartBase {

    /**
     * Gets the BlockState that represents this Minecart's command block
     *
     * @return the state of this Minecart's command block
     */
    TileSnapshot getCommandBlockState();
}
