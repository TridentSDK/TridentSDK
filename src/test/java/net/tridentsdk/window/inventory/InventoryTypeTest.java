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

package net.tridentsdk.window.inventory;

import org.junit.Assert;
import org.junit.Test;

public class InventoryTypeTest {

    @Test
    public void testToString() throws Exception {
        Assert.assertEquals(InventoryType.CHEST.toString(), "minecraft:chest");
        Assert.assertEquals(InventoryType.CRAFTING_TABLE.toString(), "minecraft:crafting_table");
        Assert.assertEquals(InventoryType.FURNACE.toString(), "minecraft:furnace");
        Assert.assertEquals(InventoryType.DISPENSER.toString(), "minecraft:dispenser");
        Assert.assertEquals(InventoryType.ENCHANTING_TABLE.toString(), "minecraft:enchanting_table");
        Assert.assertEquals(InventoryType.BREWING_STAND.toString(), "minecraft:brewing_stand");
        Assert.assertEquals(InventoryType.VILLAGER_TRADE.toString(), "minecraft:villager");
        Assert.assertEquals(InventoryType.BEACON.toString(), "minecraft:beacon");
        Assert.assertEquals(InventoryType.ANVIL.toString(), "minecraft:anvil");
        Assert.assertEquals(InventoryType.HOPPER.toString(), "minecraft:hopper");
        Assert.assertEquals(InventoryType.DROPPER.toString(), "minecraft:dropper");
        Assert.assertEquals(InventoryType.HORSE.toString(), "minecraft:horse");
    }
}