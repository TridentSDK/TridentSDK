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

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.tridentsdk.entity.living.Player;
import net.tridentsdk.event.Event;
import net.tridentsdk.event.Supertype;

import javax.annotation.concurrent.Immutable;

/**
 * Represents an event that is dispatched as a result of an
 * action taken by a player.
 *
 * @author TridentSDK
 * @since 0.4-alpha
 */
@Immutable
@Supertype
@AllArgsConstructor
public class PlayerEvent implements Event {
    @Getter
    private final Player player;
}