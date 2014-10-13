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

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Collection;
import java.util.Map;

public enum VillagerCareer {
    /**
     * Fletcher
     */
    FLETCHER(VillagerProfession.FARMER, 0),

    /**
     * Farmer
     */
    FARMER(VillagerProfession.FARMER, 1),

    /**
     * Fisherman
     */
    FISHERMAN(VillagerProfession.FARMER, 2),

    /**
     * Shepherd
     */
    SHEPHERD(VillagerProfession.FARMER, 3),

    /**
     * Librarian
     */
    LIBRARIAN(VillagerProfession.LIBRARAIAN, 0),

    /**
     * Cleric
     */
    CLERIC(VillagerProfession.PRIEST, 0),

    /**
     * Tool smith
     */
    TOOL_SMITH(VillagerProfession.BLACKSMITH, 0),

    /**
     * Armorer
     */
    ARMORER(VillagerProfession.BLACKSMITH, 1),

    /**
     * Weapon smith
     */
    WEAPON_SMITH(VillagerProfession.BLACKSMITH, 2),

    /**
     * Butcher
     */
    BUTCHER(VillagerProfession.BUTCHER, 0),

    /**
     * Leatherworker
     */
    LEATHERWORKER(VillagerProfession.BUTCHER, 1);
    private static final Map<VillagerProfession, Collection<VillagerCareer>> byProfession = Maps.newHashMap();

    static {
        Collection<VillagerCareer> parentColl = Lists.newArrayList();
        VillagerProfession current = null;
        for (VillagerCareer carrer : VillagerCareer.values()) {
            if (current == null) {
                current = carrer.parent;
                parentColl.add(carrer);
                continue;
            }
            if (current == carrer.parent) {
                parentColl.add(carrer);
            } else {
                VillagerCareer.byProfession.put(current, parentColl);
                parentColl = Lists.newArrayList();
                current = carrer.parent;
            }
        }
    }

    private final VillagerProfession parent;
    private final int id;

    VillagerCareer(VillagerProfession parent, int id) {
        this.parent = parent;
        this.id = id;
    }

}
