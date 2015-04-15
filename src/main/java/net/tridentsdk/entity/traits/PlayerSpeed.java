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
package net.tridentsdk.entity.traits;

/**
 * Represents various settings of pace of which the player can move at
 *
 * @author The TridentSDK Team
 */
public interface PlayerSpeed {
    /**
     * Returns the flying speed of the Player
     *
     * @return float flying speed of the Player
     */
    float flyingSpeed();

    /**
     * Set the flying speed of the Player
     *
     * @param flyingSpeed float flying speed of the Player
     */
    void setFlyingSpeed(float flyingSpeed);

    /**
     * Returns the sneak speed of the player
     *
     * @return float the Player's current sneak speed
     */
    float sneakSpeed();

    /**
     * Sets the Player's sneak speed
     *
     * @param speed float Player's sneak speed
     */
    void setSneakSpeed(float speed);

    /**
     * Returns the walk speed of the player
     *
     * @return float the Player's current walk speed
     */
    float walkSpeed();

    /**
     * Sets the Player's walk speed
     *
     * @param speed float Player's walk speed
     */
    void setWalkSpeed(float speed);
}
