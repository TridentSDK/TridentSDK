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

package net.tridentsdk.entity.block;

/**
 * Represents a Primed TNT
 *
 * @author TridentSDK Team
 */
public interface PrimeTNT extends FallingBlock {
    /**
     * The number of ticks until this Primed TNT explodes
     *
     * @return the number of ticks until this Primed TNT explodes
     */
    int getExplodeTicks();

    /**
     * Sets the number of fuse ticks
     *
     * @param ticks the number of ticks to set
     */
    void getExplodeTicks(int ticks);

    /**
     * Obtains the radius of the explosion
     *
     * @return the radius of the explosion
     */
    int getRadius();

    /**
     * Sets the radius of the explosion
     *
     * @param radius the radius
     */
    void setRadius(int radius);
}
