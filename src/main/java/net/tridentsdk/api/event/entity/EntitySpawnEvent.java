/*
 *     TridentSDK - A Minecraft Server API
 *     Copyright (C) 2014, The TridentSDK Team
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.tridentsdk.api.event.entity;

import net.tridentsdk.api.Coordinates;
import net.tridentsdk.api.entity.Entity;

public class EntitySpawnEvent extends EntityEvent {

    private final Coordinates location;
    private boolean cancel;

    /**
     * @param entity   the entity spawned
     * @param location the location of the spawning
     */

    public EntitySpawnEvent(Entity entity, Coordinates location) {
        super(entity);
        this.location = location;
    }

    /**
     * @return return the location where the entity was spawned
     */

    public Coordinates getLocation() {
        return this.location;
    }

    @Override
    public boolean isCancelled() {
        return this.cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }
}
