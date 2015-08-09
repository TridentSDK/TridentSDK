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
package net.tridentsdk.crafting;

import net.tridentsdk.inventory.Item;

import java.util.Collections;
import java.util.List;

/**
 * TODO Write Description
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 */
public abstract class RecipeManager {
    protected static RecipeManager instance;

    public static RecipeManager getInstance() {
        return instance;
    }

    public void addShapelessRecipe(Item result, Item source) {
        addShapelessRecipe(result, Collections.singletonList(source));
    }

    public abstract void addShapelessRecipe(Item result, List<Item> source);

    public void addShapedRecipe(Item result, char[][] grid, CraftTuple source) {
        addShapedRecipe(result, grid, Collections.singletonList(source));
    }

    public abstract void addShapedRecipe(Item result, char[][] grid, List<CraftTuple> source);

    public abstract void addSmeltingRecipe(Item result, Item source, int smeltTicks, float experience);

    public void addSmeltingFuel(Item source, int burnTicks) {
        addSmeltingFuel(source, burnTicks, null);
    }

    public abstract void addSmeltingFuel(Item source, int burnTicks, Item returnItem);

}
