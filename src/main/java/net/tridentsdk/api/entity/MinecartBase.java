/*
 * Copyright (c) 2014, The TridentSDK Team
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     1. Redistributions of source code must retain the above copyright
 *        notice, this list of conditions and the following disclaimer.
 *     2. Redistributions in binary form must reproduce the above copyright
 *        notice, this list of conditions and the following disclaimer in the
 *        documentation and/or other materials provided with the distribution.
 *     3. Neither the name of the The TridentSDK Team nor the
 *        names of its contributors may be used to endorse or promote products
 *        derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL The TridentSDK Team BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package net.tridentsdk.api.entity;

/**
 * Represents the generic Minecart
 *
 * @author TridentSDK Team
 */
public interface MinecartBase extends Vehicle {
    /**
     * Represents this Minecart's display tile, in the form of a BlockState
     *
     * @return the display tile of this Minecart
     */
    Object getDisplayTile();    /* TODO: Change return type to valid implementation of BlockState */

    /**
     * Set this Minecart's display tile to the specified block state
     *
     * @param blockState the state to set this to
     */
    void setDisplayTile(Object blockState);    /* TODO: Change param type to valid implementation of BlockState */

    /**
     * Get the offset for this Minecart's display tile
     *
     * @return the offset for this Minecart's display tile
     */
    int getDisplayTileOffset();

    /**
     * Set the offset for this Minecart's display tile
     *
     * @param offset the offset to set
     */
    void setDisplayTileOffset(int offset);

    /**
     * Gets the custom name of this Minecart
     *
     * @return the custom name of this Minecart
     */
    String getName();

    /**
     * Sets the custom name of this Minecart
     *
     * @param name the new value of the custom name
     */
    void setName(String name);
}
