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

import java.util.concurrent.atomic.AtomicBoolean;

import net.tridentsdk.entity.living.EntityPlayer;
import net.tridentsdk.event.Cancellable;

/**
 * @author Nick Robson
 */
public abstract class CancellablePlayerEvent extends PlayerEvent implements Cancellable {

    private final AtomicBoolean cancelled = new AtomicBoolean(false);

    public CancellablePlayerEvent(EntityPlayer player) {
        super(player);
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled.get();
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled.set(cancelled);
    }

}
