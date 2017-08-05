/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2017 The TridentSDK Team
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

import lombok.Getter;
import lombok.Setter;
import net.tridentsdk.entity.living.EntityPlayer;
import net.tridentsdk.event.Cancellable;

import javax.annotation.concurrent.NotThreadSafe;

/**
 * This event is dispatched whenever a player right-clicks
 * the air.
 *
 * @author TridentSDK
 * @since 0.4-alpha
 */
@NotThreadSafe
public class PlayerInteractEvent extends PlayerEvent implements Cancellable {
    @Getter
    @Setter
    private boolean cancelled;

    public PlayerInteractEvent(EntityPlayer player) {
        super(player);
    }
}