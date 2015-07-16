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

import net.tridentsdk.entity.types.OcelotType;
import net.tridentsdk.entity.traits.Peaceful;
import net.tridentsdk.entity.traits.Tameable;

/**
 * Represents an Ocelot
 *
 * @author TridentSDK Team
 */
public interface Ocelot extends Tameable, Peaceful {
    /**
     * The breed of this ocelot, represented as a {@link net.tridentsdk.entity.types.OcelotType}OcelotType
     *
     * @return the breed of this ocelot
     */
    OcelotType getBreed();
}
