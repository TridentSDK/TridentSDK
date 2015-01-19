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

import net.tridentsdk.entity.living.Player;
import net.tridentsdk.event.Cancellable;
import net.tridentsdk.window.inventory.Item;

/**
 * Called when a player's fishing state changes,e.g. throws line, catches a fish, catches an entity, etc.
 *
 * @author The TridentSDK Team
 */
public class PlayerFishEvent extends PlayerEvent implements Cancellable {
    private final State state;
    private volatile int exp;
    private volatile Item item;
    private volatile boolean cancelled;

    public PlayerFishEvent(Player player, State state, int exp, Item item) {
        super(player);
        this.state = state;
        this.exp = exp;
        this.item = item;
    }

    @Override
    public boolean isIgnored() {
        return cancelled;
    }

    @Override
    public void cancel(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public State getState() {
        return this.state;
    }

    public int getExp() {
        return this.exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public Item getItem() {
        return this.item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * The state of a fishing hook when deployed
     *
     * @author The TridentSDK Team
     */
    public enum State {
        /**
         * When the hook is in the water
         */
        FISHING,
        /**
         * Undefined
         */
        FAILED_ATTEMPT,
        /**
         * A fish is caught on the hook
         */
        CAUGHT_FISH,
        /**
         * The hook attaches to an entity
         */
        CAUGHT_ENTITY,
        /**
         * The hook attaches to the ground
         */
        IN_GROUND
    }
}
