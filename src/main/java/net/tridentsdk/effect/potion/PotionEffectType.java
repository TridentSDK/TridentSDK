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
package net.tridentsdk.effect.potion;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the types of potion effects
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 */
public enum PotionEffectType {
    SPEED(1),
    SLOWNESS(2),
    HASTE(3),
    MINING_FATIGUE(4),
    STRENGTH(5),
    INSTANT_HEALTH(6),
    INSTANT_DAMAGE(7),
    JUMP_BOOST(8),
    NAUSEA(9),
    REGENERATION(10),
    RESISTANCE(11),
    FIRE_RESISTANCE(12),
    WATER_BREATHING(13),
    INVISIBILITY(14),
    BLINDNESS(15),
    NIGHT_VISION(16),
    HUNGER(17),
    WEAKNESS(18),
    POISON(19),
    WITHER(20),
    HEALTH_BOOST(21),
    ABSORPTION(22),
    SATURATION(23);
    private static Map<Byte, PotionEffectType> byId = new HashMap<>();

    static {
        for (PotionEffectType type : values()) {
            byId.put(type.id(), type);
        }
    }

    private byte id;

    PotionEffectType(int id) {
        this.id = (byte) id;
    }

    public static PotionEffectType fromId(int id) {
        return byId.get((byte) id);
    }

    public byte id() {
        return id;
    }
}
