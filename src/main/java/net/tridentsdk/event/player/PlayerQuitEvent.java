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
import net.tridentsdk.entity.living.Player;
import net.tridentsdk.ui.chat.ChatComponent;

/**
 * Represents a player that has successfully logged off the
 * server, and will commence with sending the necessary
 * packets in order to spawn the player entity later on.
 *
 * @author TridentSDK
 * @since 0.4-alpha
 */
@Getter
public class PlayerQuitEvent extends PlayerEvent {

    @Setter private final ChatComponent message;

    public PlayerQuitEvent(Player player, ChatComponent message) {
        super(player);
        this.message = message;
    }

}
