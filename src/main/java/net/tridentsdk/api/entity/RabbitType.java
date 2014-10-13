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
 * Possible rabbit types, color and friendliness
 */
public enum RabbitType {
    BROWN(0),

    WHITE(1),

    BLACK(2),

    WHITE_AND_BLACK(3),

    GOLD(4),

    SALT_AND_PEPPER(5),

    KILLER_RABBIT(99);

    private static final RabbitType[] byId = new RabbitType[7];

    static {
        for (RabbitType type : RabbitType.values()) {
            RabbitType.byId[type.id] = type;
            // TODO by ordinal?
        }
    }

    private final int id;

    RabbitType(int id) {
        this.id = id;
    }

    /**
     * Returns the {@code int} value of the RabbitType
     *
     * @return {@code int} value of the RabbitType
     */
    public int toInt() {
        return this.id;
    }

    /**
     * Returns the {@code int} value of the RabbitType
     *
     * @param rabbitType RabbitType
     * @return {@code int} value of the RabbitType
     */
    public static int toInt(RabbitType rabbitType) {
        return rabbitType.toInt();
    }
}
