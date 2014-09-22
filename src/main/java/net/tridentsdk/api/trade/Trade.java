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

package net.tridentsdk.api.trade;

import net.tridentsdk.api.inventory.ItemStack;

/**
 * Represents a Trade offered by an {@link net.tridentsdk.api.entity.Tradeable}
 *
 * @author TridentSDK Team
 */
public interface Trade {

    /**
     * Whether or not this trade rewards xp
     *
     * @return Whether or not this trade should reward xp
     */
    boolean rewardExp();

    /**
     * How many times this trade can be fulfilled
     *
     * @return the number of times this trade can be fulfilled
     */
    int maxUses();

    /**
     * How many times this trade has been fulfilled
     *
     * @return how many times this trade has been fulfilled
     */
    int uses();

    /**
     * The itemstack given as a result of this trade
     *
     * @return the itemstack that is given as a result of fulfilling this trade
     */
    ItemStack offer();

    /**
     * The itemstacks required to be input in order to fulfilled this trade This accepts both implementations of
     * ItemPair, including a null second offer
     *
     * @return the input that will fulfill this trade
     */
    ItemPair input();
}
