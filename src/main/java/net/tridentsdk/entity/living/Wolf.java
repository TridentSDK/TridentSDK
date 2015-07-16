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

package net.tridentsdk.entity.living;

import net.tridentsdk.base.SubstanceColor;
import net.tridentsdk.entity.traits.Neutral;
import net.tridentsdk.entity.traits.Tameable;

/**
 * Represents a Wolf
 *
 * @author TridentSDK Team
 */
public interface Wolf extends Tameable, Neutral {
    /**
     * Whether or not this entity is angry
     *
     * @return whether or not this entity is angry
     */
    boolean isAngry();

    /**
     * The color of this entity's collar
     *
     * @return the color of this entity's collar
     */
    SubstanceColor getCollarColor();
}
