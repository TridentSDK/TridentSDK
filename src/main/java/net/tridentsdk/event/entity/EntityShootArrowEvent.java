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

import net.tridentsdk.entity.Entity;
import net.tridentsdk.entity.projectile.Arrow;
import net.tridentsdk.entity.projectile.Projectile;

/**
 * Called when a skeleton shoots an arrow
 *
 * @author The TridentSDK Team
 */
public class EntityShootArrowEvent extends EntityLaunchProjectileEvent {
    public EntityShootArrowEvent(Entity entity, Projectile projectile, Entity target) {
        super(entity, projectile, target);
    }

    public Arrow getArrow() {
        return (Arrow) super.getProjectile();
    }
}
