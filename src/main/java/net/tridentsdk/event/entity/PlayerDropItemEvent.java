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

package net.tridentsdk.event.entity;

import com.google.common.base.Preconditions;
import net.tridentsdk.Coordinates;
import net.tridentsdk.entity.DroppedItem;
import net.tridentsdk.entity.Entity;
import net.tridentsdk.entity.living.Player;

/**
 * Called when a player drops an item
 *
 * @author The TridentSDK Team
 */
public class PlayerDropItemEvent extends EntitySpawnEvent {
    private final Player player;

    public PlayerDropItemEvent(Entity item, Coordinates location, Player player) {
        super(item, location);
        Preconditions.checkArgument(item instanceof DroppedItem, "Must drop an item!");
        this.player = player;
    }

    public Player player() {
        return this.player;
    }

    public DroppedItem item() {
        return (DroppedItem) super.entity();
    }

    public void setItem(Entity item) {
        super.setEntity(item);
    }
}
