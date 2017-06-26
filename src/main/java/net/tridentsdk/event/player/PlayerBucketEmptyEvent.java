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

package net.tridentsdk.event.player;

import net.tridentsdk.base.Block;
import net.tridentsdk.base.BlockDirection;
import net.tridentsdk.entity.living.Player;
import net.tridentsdk.event.block.BlockPlaceEvent;
import net.tridentsdk.inventory.Item;

/**
 * Called when a player empties a bucket
 *
 * @author The TridentSDK Team
 * @since 0.3-alpha-DP
 */
public class PlayerBucketEmptyEvent extends BlockPlaceEvent {
    private final Item blockInHand;

    public PlayerBucketEmptyEvent(Player player, Block block, Block blockClicked, BlockDirection faceClicked,
            Item blockInHand) {
        super(player, block, blockClicked, faceClicked);
        this.blockInHand = blockInHand;
    }

    public Item blockInHand() {
        return this.blockInHand;
    }
}
