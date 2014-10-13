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
 * Represents a dropped ItemStack
 *
 * @author TridentSDK Team
 */
public interface Item extends Entity {
    /**
     * Represents the age of this Item entity
     *
     * @return the age of this Item entity
     */
    int getAge();

    /**
     * Sets the age of this Item entity
     *
     * @param age the age to set
     */
    void setAge(int age);

    /**
     * Represents the health of this Item entity.
     *
     * @return the health of this Item entity
     */
    short getHealth();

    /**
     * Sets the health for this Item entity
     *
     * @param health the value to set the health to
     */
    void setHealth(short health);

    /**
     * Represents the owner of this Item entity
     */
    String getOwner();

    /**
     * Sets the owner of this Item entity. Nobody else can pickup this Item until 10 seconds are left in its life if
     * this value is set
     *
     * @param owner The name of the Player to set this too
     */
    void setOwner(String owner);

    /**
     * Gets the name of the Player that dropped this Will return {@code null} if this was spawned unnaturally
     *
     * @return the name of the Player that dropped this
     */
    String getDropper();

    /**
     * Sets the Player who dropped this Item
     *
     * @param dropper the name of the Player who will become the dropper
     */
    void setDropper(String dropper);
}
