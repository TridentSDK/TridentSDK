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

/**
 * Represents the type of an Ocelot
 *
 * @author TridentSDK Team
 */
public enum OcelotType {
    /**
     * Wild
     */
    WILD(0),

    /**
     * Tuxedo
     */
    TUXEDO(1),

    /**
     * Tabby
     */
    TABBY(2),

    /**
     * Siamese
     */
    SIAMESE(3);

    private static final OcelotType[] byId = new OcelotType[4];

    static {
        for (OcelotType type : OcelotType.values()) {
            byId[type.id] = type;
            // TODO by ordinal?
        }
    }

    private final int id;

    OcelotType(int id) {
        this.id = id;
    }

}
