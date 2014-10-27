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

public enum VillagerProfession {
    /**
     * Farmer
     */
    FARMER(0),

    /**
     * Librarian
     */
    LIBRARAIAN(1),

    /**
     * Priest
     */
    PRIEST(2),

    /**
     * Blacksmith
     */
    BLACKSMITH(3),

    /**
     * Butcher
     */
    BUTCHER(4);
    private static final VillagerProfession[] byId = new VillagerProfession[5];

    static {
        for (VillagerProfession profession : VillagerProfession.values()) {
            byId[profession.id] = profession;
        }
    }

    private final int id;

    VillagerProfession(int id) {
        this.id = id;
    }

    /**
     * Returns the {@code int} value of the VillagerProfession
     *
     * @return {@code int} value of the VillagerProfession
     */
    public int toInt() {
        return this.id;
    }

    /**
     * Returns the {@code int} value of the VillagerProfession
     *
     * @param villagerProfession VillagerProfession
     * @return {@code int} value of the VillagerProfession
     */
    public static int toInt(VillagerProfession villagerProfession) {
        return villagerProfession.toInt();
    }
}
