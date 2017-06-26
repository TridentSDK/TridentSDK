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

package net.tridentsdk.entity.types;

/**
 * The types of entities that are possible to be in Minecraft, they populate the world, whether being a mob, animal,
 * player, dropped items, or item frames.
 *
 * @author The TridentSDK Team
 * @since 0.3-alpha-DP
 */
public enum EntityType {
    NOT_IMPL(-1),
    CREEPER(50),
    BAT(65),
    BLAZE(61),
    CHICKEN(93),
    COW(92),
    ENDER_DRAGON(63),
    ENDERMAN(58),
    ENDERMITE(67),
    GHAST(56),
    HORSE(100),
    MAGMA_CUBE(62),
    MOOSHROOM(96),
    OCELOT(98),
    PIG(90),
    GUARDIAN(68),
    PLAYER(-1),
    RABBIT(101),
    SHEEP(91),
    SKELETON(51),
    SLIME(55),
    VILLAGER(120),
    WITHER(64),
    WOLF(95),
    ZOMBIE(54),
    ARMOR_STAND(78),
    FALLING_BLOCK(70), // objects
    ITEM_FRAME(71),
    PAINTING(-1), // what?
    PRIMED_TNT(50),
    ARROW(60),
    EGG(62),
    ENDER_PEARL(65),
    EXPERIENCE_BOTTLE(75),
    FIREBALL(63),
    FISH_HOOK(90),
    POTION(73),
    SMALL_FIREBALL(64), // firecharge...?
    SNOWBALL(61),
    WITHER_SKULL(66),
    BOAT(1),
    COMMAND_MINECART(10),
    FURNANCE_MINECART(10),
    HOPPER_MINECART(10),
    MINECART(10),
    SPAWNER_MINECART(10),
    TNT_MINECART(10),
    ITEM(2),
    EXPERIENCE_ORB(-1), // ?
    FIREWORK(76);
    // TODO: Finish/check this enum

    private final int id;

    EntityType(int id) {
        this.id = id;
    }

    /**
     * Obtains the entity ID as a byte
     *
     * @return a byte representing the entity ID
     */
    public byte asByte() {
        return (byte) this.id;
    }
}
