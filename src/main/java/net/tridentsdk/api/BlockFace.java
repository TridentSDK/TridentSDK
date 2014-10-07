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
/*
 * Copyright (c) 2014, The TridentSDK Team
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 *     2. Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 *     3. Neither the name of TridentSDK nor the names of its
 *   contributors may be used to endorse or promote products derived from
 *   this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package net.tridentsdk.api;

import net.tridentsdk.api.util.Vector;

/**
 * Represents an orientation towards a given direction set from a {@link net.tridentsdk.api.util.Vector}
 *
 * @author The TridentSDK Team
 */
public enum BlockFace {
    /**
     * Facing north
     */
    NORTH(new Vector(0, 0, -1)),
    /**
     * Facing south
     */
    SOUTH(new Vector(0, 0, 1)),
    /**
     * Facing east
     */
    EAST(new Vector(1, 0, 0)),
    /**
     * Facing west
     */
    WEST(new Vector(-1, 0, 0)),

    /**
     * Facing north east
     */
    NORTH_EAST(BlockFace.NORTH, BlockFace.EAST),
    /**
     * Facing north west
     */
    NORTH_WEST(BlockFace.NORTH, BlockFace.WEST),
    /**
     * Facing south east
     */
    SOUTH_EAST(BlockFace.SOUTH, BlockFace.EAST),
    /**
     * Facing south west
     */
    SOUTH_WEST(BlockFace.SOUTH, BlockFace.WEST),

    /**
     * Facing up
     */
    TOP(new Vector(0.0D, 1.0D, 0.0D)),
    /**
     * Facing down
     */
    BOTTOM(new Vector(0.0D, -1.0D, 0.0D)),

    /**
     * The given component direction not leading anywhere
     */
    SELF(new Vector(0, 0, 0));

    private final Vector difference;

    BlockFace(Vector difference) {
        this.difference = difference;
    }

    BlockFace(BlockFace face1, BlockFace face2) {
        this.difference = face1.getDifference().add(face2.getDifference());
    }

    /**
     * The copy of the directional vector
     *
     * @return the cloned vector pointing to the specified face
     */
    public Vector getDifference() {
        return this.difference.clone();
    }

    /**
     * Gets the location relative to the given direction
     *
     * @param loc the location to get relative to
     * @return the relative location
     */
    public Location apply(Location loc) {
        return loc.getRelative(this.difference);
    }
}
