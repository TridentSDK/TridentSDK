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

package net.tridentsdk.world.settings;

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * The possible climate zones which divide the world
 *
 * @author The TridentSDK Team
 * @since 0.3-alpha-DP
 */
public enum Biome {
    OCEAN(0),
    PLAINS(1),
    DESERT(2),
    EXTREME_HILLS(3),
    FOREST(4),
    TAIGA(5),
    SWAMPLAND(6),
    RIVER(7),
    HELL(8),
    THE_END(9),
    /**
     * @deprecated This biome exists only in code, and does not generate naturally
     */
    @Deprecated
    FROZEN_OCEAN(10),
    FROZEN_RIVER(11),
    ICE_PLAINS(12),
    MUSHROOM_ISLAND(14),
    MUSHROOM_ISLAND_SHORE(15),
    BEACH(16),
    /**
     * @deprecated This biome exists only in code, and does not generate naturally
     */
    @Deprecated
    EXTREME_HILLS_EDGE(20),
    JUNGLE(21),
    JUNGLE_EDGE(23),
    DEEP_OCEAN(24),
    STONE_BEACH(25),
    COLD_BEACH(26),
    BIRCH_FOREST(27),
    BIRCH_FOREST_HILLS(28),
    ROOFED_FOREST(29),
    COLD_TAIGA(30),
    MEGA_TAIGA(32),
    EXTREME_HILLS_PLUS(34),

    SAVANNA(35),
    SAVANNA_PLATEAU(36),
    MESA(37),
    MESA_PLATEAU_F(38),
    MESA_PLATEAU(39),

    SUNFLOWER_PLAINS(129),
    DESERT_M(130),
    EXTREME_HILLS_M(131),
    FLOWER_FOREST(132),
    TAIGA_M(133),
    SWAMPLAND_M(134),
    ICE_PLAINS_SPIKES(140),
    JUNGLE_M(149),
    JUNGLE_EDGE_M(151),
    BIRCH_FOREST_M(155),
    BIRCH_FOREST_HILLS_M(156),
    ROOFED_FOREST_M(158),
    COLD_TAIGA_M(158),
    MEGA_SPRUCE_TAIGA(160),
    REDWOOD_TAIGA_HILLS_M(161),
    EXTREME_HILLS_PLUS_M(162),

    SAVANNA_M(163),
    SAVANNA_PLATEAU_M(164),
    MESA_BRYCE(165),
    MESA_PLATEAU_F_M(166),
    MESA_PLATEAU_M(167);

    private final byte id;

    private static final Map<Byte, Biome> map;

    static {
        HashMap<Byte, Biome> temp = new HashMap<>();
        for (Biome biome : EnumSet.allOf(Biome.class)) {
            temp.put(biome.id, biome);
        }
        map = Collections.unmodifiableMap(temp);
    }

    Biome(int id) {
        this.id = (byte) id;
    }

    public int id() {
        return id;
    }

    public static Biome fromId(byte id) {
        return map.get(id);
    }

    public static Biome fromId(int id) {
        return fromId((byte) id);
    }
}
