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

package net.tridentsdk.entity;

/**
 * Represents an Experience Orb
 *
 * @author TridentSDK Team
 */
public interface ExperienceOrb extends Entity {
	
    /**
     * Represents the age of this Experience Orb entity
     *
     * @return the age of this Experience Orb entity
     */
    int getAge();

    /**
     * Sets the age of this Experience Orb entity
     *
     * @param age the age to set
     */
    void setAge(int age);

// 	  I feel as if experience orbs don't have health.
//  
//    /**
//     * Represents the health of this Experience Orb entity.
//     *
//     * @return the health of this Experience Orb entity
//     */
//    short health();
//
//    /**
//     * Sets the health for this Experience Orb entity
//     *
//     * @param health the value to set the health to
//     */
//    void setHealth(short health);
}
