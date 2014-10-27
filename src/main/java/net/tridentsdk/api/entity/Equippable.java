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
package net.tridentsdk.api.entity;

import net.tridentsdk.api.inventory.ItemStack;

/**
 * Represents an entity that can be equipped
 *
 * @author TridentSDK Team
 */
public interface Equippable extends Entity {
    /**
     * This entity's equipment
     *
     * <p>Layout:
     * <ul>
     *     <li>Index 0: Helmet</li>
     *     <li>Index 1: Chestplate</li>
     *     <li>Index 2: Leggings</li>
     *     <li>Index 3: Boots</li>
     * </ul></p>
     *
     * @return this entity's equipment
     */
    ItemStack[] getEquipment();
}
