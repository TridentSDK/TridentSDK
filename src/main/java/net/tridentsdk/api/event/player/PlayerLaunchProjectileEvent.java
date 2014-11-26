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
package net.tridentsdk.api.event.player;

import net.tridentsdk.api.entity.Entity;
import net.tridentsdk.api.entity.living.Player;
import net.tridentsdk.api.event.Ignorable;

/**
 * Called when a player launches a projectile e.g. an arrow or snowball
 */
public class PlayerLaunchProjectileEvent extends PlayerEvent implements Ignorable {
    private final Entity projectile;
    private boolean cancel;

    public PlayerLaunchProjectileEvent(Player player, Entity projectile) {
        super(player);
        this.projectile = projectile;
    }

    @Override
    public boolean isIgnored() {
        return cancel;
    }

    @Override
    public void ignore(boolean cancel) {
        this.cancel = cancel;
    }

    public Entity getProjectile() {

        return this.projectile;
    }
}
