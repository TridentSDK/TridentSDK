/*
 * Copyright (C) 2014 The TridentSDK Team
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.tridentsdk.api;

import com.google.common.base.Preconditions;

/**
 * Utility accessor to the {@link net.tridentsdk.api.Server}
 *
 * @author The TridentSDK Team
 */
public final class Trident {
    private static Server server;

    private Trident() {}

    /**
     * Gets the server singleton that is currently running
     *
     * @return the server that is running
     */
    public static Server getServer() {
        return Trident.server;
    }

    /**
     * Do not call.
     *
     * @param s the server to set
     */
    public static void setServer(Server s) {
        Preconditions.checkArgument(Trident.server != null, "Can only set server instance once!");
        Trident.server = s;
    }
}
