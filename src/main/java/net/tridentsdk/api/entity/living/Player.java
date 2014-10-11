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

package net.tridentsdk.api.entity.living;

import net.tridentsdk.api.GameMode;
import net.tridentsdk.api.entity.LivingEntity;
import net.tridentsdk.api.inventory.ItemStack;

import java.util.Locale;

/**
 * TODO
 */
public interface Player extends LivingEntity {
    /**
     * Send an array of messages to the Player
     * <p></p>
     * 
     * @param message String[] messages to be sent
     */
    void sendMessage(String... message);

    /**
     * Returns the flying speed of the Player
     * 
     * @return float flying speed of the Player
     */
    float getFlyingSpeed();

    /**
     * Set the flying speed of the Player
     * 
     * @param flyingSpeed float flying speed of the Player
     */
    void setFlyingSpeed(float flyingSpeed);

    // TODO: Use word settings?
    /**
     * Returns the Player's {@link Locale} settings
     * 
     * @return Locale the Player's Locale settings
     */
    Locale getLocale();

    /**
     * Returns the ItemStack in the Player's hand
     * 
     * @return ItemStack current ItemStack in the Player's hand
     */
    ItemStack getItemInHand();

    /**
     * Returns the GameMode the Player is in
     *
     * @return GameMode current GameMode of the Player
     */
    GameMode getGameMode();

    /**
     * Returns the move speed of the player
     *
     * @return float the Player's current move speed
     */
    float getMoveSpeed();

    /**
     * Sets the Player's move speed
     *
     * @param speed float Player's move speed
     */
    void setMoveSpeed(float speed);

    /**
     * Returns the sneak speed of the player
     *
     * @return float the Player's current sneak speed
     */
    float getSneakSpeed();

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
    float getWalkSpeed();

    /**
     * Sets the Player's walk speed
     *
     * @param speed float Player's walk speed
     */
    void setWalkSpeed(float speed);
}
