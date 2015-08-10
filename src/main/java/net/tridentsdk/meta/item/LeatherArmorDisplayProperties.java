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
package net.tridentsdk.meta.item;

import net.tridentsdk.base.Color;

/**
 * Represents leather armor's display properties
 */
public interface LeatherArmorDisplayProperties extends ItemDisplayProperties {
    /**
     * Returns the color of the leather armor
     * @return the color of the leather armor
     */
    Color color();

    /**
     * Sets the color of the leather armor
     * @param color the color you wish to set the leather armor to
     */
    void setColor(Color color);
}
