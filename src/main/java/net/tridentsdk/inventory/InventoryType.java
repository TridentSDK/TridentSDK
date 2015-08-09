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

package net.tridentsdk.inventory;

/**
 * Type of inventories
 *
 * @author The TridentSDK Team
 * @since 0.3-alpha-DP
 */
public enum InventoryType {
    CHEST("minecraft:chest", "Chest"),
    CRAFTING_TABLE("minecraft:crafting_table", "Crafting Table"),
    FURNACE("minecraft:furnace", "Furnace"),
    DISPENSER("minecraft:dispenser", "Dispenser"),
    ENCHANTING_TABLE("minecraft:enchanting_table", "Enchanting Table"),
    BREWING_STAND("minecraft:brewing_stand", "Brewing Stand"),
    VILLAGER_TRADE("minecraft:villager", "Villager"),
    BEACON("minecraft:beacon", "Beacon"),
    ANVIL("minecraft:anvil", "Anvil"),
    HOPPER("minecraft:hopper", "Hopper"),
    DROPPER("minecraft:dropper", "Dropper"),
    HORSE("minecraft:horse", "Horse");

    private final String s;
    private final String defaultName;

    InventoryType(String s, String defaultName) {
        this.s = s;
        this.defaultName = defaultName;
    }

    /**
     * Returns the Minecraft ID for the entity or block which holds the InventoryType
     *
     * @return String Minecraft ID for the entity or block which holds the InventoryType
     */
    @Override
    public String toString() {
        return this.s;
    }

    public String defaultName(){
        return defaultName;
    }

}
