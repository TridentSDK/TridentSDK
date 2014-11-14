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
package net.tridentsdk.api.event;

/**
 * Represents a callable, listenable occurence that is registered with the information regarding the event
 *
 * @author The TridentSDK Team
 */
public abstract class Listenable {
    private final boolean isAsync;

    /**
     * Creates a non-async event
     */
    public Listenable() {
        this(false);
    }

    /**
     * Creates an event that may or may not be thread-safe
     *
     * @param async if {@code true}, then the event must be guaranteed to be thread safe
     */
    public Listenable(boolean async) {
        this.isAsync = async;
    }

    /**
     * @return {@code true} if Listenable is asynchronous
     */
    public boolean isAsync() {
        return this.isAsync;
    }
}
