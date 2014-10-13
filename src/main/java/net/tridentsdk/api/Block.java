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

package net.tridentsdk.api;

import net.tridentsdk.api.util.Vector;

/**
 * A basic structure in minecraft, a material bearing piece set at a given location
 *
 * @author The TridentSDK Team
 */
public class Block {
    private final Location location;
    protected Material material;
    protected byte data;

    /**
     * Constructs the wrapper representing the block
     *
     * @param location Location of the Block
     */
    public Block(Location location) {
        this.location = location;

        // Note: Avoid recursion by not creating a new instance from World#getBlockAt(Location)
        Block worldBlock = location.getWorld().getBlockAt(location);

        this.material = worldBlock.material;
    }

    /**
     * For internal use only
     */
    protected Block(Location location, boolean createdByServer) {
        this.location = location;
    }
    /**
     * Returns the Material of the Block
     *
     * @return Material of the Block
     */
    public Material getType() {
        return this.material;
    }

    // TODO: Verify the redundancy
    public void setType(Material material) {
        this.material = material;
    }

    // TODO: Verify the redundancy
    public Material getMaterial() {
        return this.material;
    }

    /**
     * Set the Material of this Block
     *
     * @param material Material to set this Block to
     */
    public void setMaterial(Material material) {
        this.material = material;
    }

    /**
     * Returns the Location of the Block
     *
     * @return Location of the Block
     */
    public Location getLocation() {
        return this.location;
    }

    public byte getData() {
        return data;
    }

    public void setData(byte data) {
        this.data = data;
    }

    /**
     * Returns a block immediately to the direction specified
     *
     * @param vector the direction to look for the block adjacent to the current
     * @return the block adjacent to the current
     */
    public Block getRelative(Vector vector) {
        return new Block(this.location.getRelative(vector));
    }
}
