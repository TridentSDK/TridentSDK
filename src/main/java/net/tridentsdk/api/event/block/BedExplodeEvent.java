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
package net.tridentsdk.api.event.block;

import net.tridentsdk.api.Block;
import net.tridentsdk.api.event.Ignorable;

/**
 * Called when a Bed explodes
 */
public class BedExplodeEvent extends BlockEvent implements Ignorable {
    private float strength;
    private boolean cancel;

    /**
     * @param block    Block associated with this event
     * @param strength Strength of the explosion
     */
    public BedExplodeEvent(Block block, float strength) {
        super(block);
        this.strength = strength;
    }

    @Override
    public boolean isIgnored() {
        return cancel;
    }

    @Override
    public void ignore(boolean cancel) {
        this.cancel = cancel;
    }

    /**
     * Get the strength of the explosion
     *
     * @return Float strength of explosion
     */
    public float getStrength() {
        return this.strength;
    }

    /**
     * Set the strength of the explosion
     *
     * @param strength Float strength of explosion
     */
    public void setStrength(float strength) {
        this.strength = strength;
    }
}
