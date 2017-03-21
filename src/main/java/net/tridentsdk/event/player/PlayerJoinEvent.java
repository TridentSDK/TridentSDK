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

import net.tridentsdk.chat.ChatComponent;
import net.tridentsdk.entity.living.Player;

/**
 * Represents a player that has successfully logged onto the
 * server, and will commence with sending the necessary
 * packets in order to spawn the player entity later on.
 *
 * <p>This event is not cancellable because removing a
 * player requires more logic that is out of scope of this
 * event. Use {@link Player#kick(ChatComponent)} for this.
 * </p>
 *
 * @author TridentSDK
 * @since 0.4-alpha
 */
public class PlayerJoinEvent extends PlayerEvent {
    public PlayerJoinEvent(Player player) {
        super(player);
    }
}