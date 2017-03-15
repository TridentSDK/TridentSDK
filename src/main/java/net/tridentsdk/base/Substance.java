/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2017 The TridentSDK Team
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
package net.tridentsdk.base;

import net.tridentsdk.util.Int2ReferenceOpenHashMap;

import javax.annotation.concurrent.Immutable;

/**
 * This class represents the set of all substances of which
 * a block may be composed.
 *
 * @author TridentSDK
 * @since 0.3-alpha-DP
 */
@Immutable
public enum Substance {
    // TODO add more substances
    AIR(0),
    STONE(1),
    GRASS(2),
    DIRT(3),
    COBBLESTONE(4),
    PLANKS(5),
    SAPLING(6),
    BEDROCK(7);

    /**
     * The ID number that is used for identifying the
     * substance.
     */
    private final int id;
    /**
     * The substance name
     */
    private final String name;

    /**
     * Creates a new substance entry with the given
     * substance ID value.
     *
     * @param id the substance ID
     */
    Substance(int id) {
        this.id = id;
        this.name = "minecraft:" + this.name().toLowerCase();
    }

    /**
     * Mapping of ID values to their respective substance,
     * substances can contain 400+ entries and that is not
     * worth using O(n) iteration.
     */
    private static final Int2ReferenceOpenHashMap<Substance> SUBSTANCE_MAP =
            new Int2ReferenceOpenHashMap<>();
    static {
        for (Substance s : values()) {
            SUBSTANCE_MAP.put(s.id, s);
        }
        SUBSTANCE_MAP.trim();
    }

    /**
     * Obtains the ID number value for the this substance.
     *
     * @return the ID number
     */
    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return this.name;
    }

    /**
     * Obtains the substance that is represented by the
     * given ID value.
     *
     * @param id the ID value of the substance to find
     * @return the substance
     */
    public static Substance of(int id){
        Substance substance = SUBSTANCE_MAP.get(id);
        if (substance == null) {
            throw new IndexOutOfBoundsException("Provided Substance ID (" + id + ") is out of bounds");
        }

        return substance;
    }
}
