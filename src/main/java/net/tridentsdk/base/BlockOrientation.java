/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2014 The TridentSDK Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.tridentsdk.base;

import net.tridentsdk.Coordinates;
import net.tridentsdk.util.Vector;

/**
 * Represents an orientation towards a given direction set from a {@link net.tridentsdk.util.Vector}
 *
 * @author The TridentSDK Team
 */
public enum BlockOrientation {
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
    NORTH_EAST(NORTH, EAST),
    /**
     * Facing north west
     */
    NORTH_WEST(NORTH, WEST),
    /**
     * Facing south east
     */
    SOUTH_EAST(SOUTH, EAST),
    /**
     * Facing south west
     */
    SOUTH_WEST(SOUTH, WEST),

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

    BlockOrientation(Vector difference) {
        this.difference = difference;
    }

    BlockOrientation(BlockOrientation face1, BlockOrientation face2) {
        this.difference = face1.difference().add(face2.difference());
    }

    /**
     * The copy of the directional vector
     *
     * @return the cloned vector pointing to the specified face
     */
    public Vector difference() {
        return this.difference.clone();
    }

    /**
     * Gets the location relative to the given direction
     *
     * @param loc the location to get relative to
     * @return the relative location
     */
    public Coordinates apply(Coordinates loc) {
        return loc.relative(this.difference);
    }
}
