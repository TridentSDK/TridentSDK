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

import net.tridentsdk.GameMode;
import net.tridentsdk.entity.living.Player;
import net.tridentsdk.event.Cancellable;

/**
 * Called <i>before</i> a Player's game mode changes
 *
 * @author The TridentSDK Team
 */
public class PlayerGameModeChangeEvent extends PlayerEvent implements Cancellable {
    private GameMode gameMode;
    private boolean cancelled;

    public PlayerGameModeChangeEvent(Player player, GameMode gameMode) {
        super(player);
        this.gameMode = gameMode;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public GameMode newGameMode() {
        return this.gameMode;
    }

    public GameMode currentGameMode() {
        return this.player().getGameMode();
    }

    public void setNewGamemode(GameMode gameMode) {
        this.gameMode = gameMode;
    }
}
