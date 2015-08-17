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
package net.tridentsdk.inventory.crafting;

import net.tridentsdk.inventory.Item;
import net.tridentsdk.registry.Registered;

import java.util.Collections;
import java.util.List;

/**
 * Manages server crafting and smelting recipes
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 */
public interface RecipeManager {
    RecipeManager instance = Registered.impl().recipe();

    /**
     * Obtains an instance of the recipe manager
     *
     * @return the recipe manager instance
     */
    static RecipeManager instance() {
        return instance;
    }

    /**
     * Adds a recipe without a shape
     *
     * @param result the result of the recipe
     * @param source the items required to craft the result
     */
    void addShapelessRecipe(Item result, List<Item> source);

    /**
     * Adds a recipe with a specified shape
     *
     * @param result the result of the recipe
     * @param grid   the shape of the recipe
     * @param source the items required to craft the result
     */
    void addShapedRecipe(Item result, char[][] grid, List<CraftTuple> source);

    /**
     * Adds a smelting recipe to the server
     *
     * @param result     the result of smelting
     * @param source     the item to smelt
     * @param smeltTicks the ticks required to smelt the item
     * @param experience the experience earned from smelting the item
     */
    void addSmeltingRecipe(Item result, Item source, int smeltTicks, float experience);

    /**
     * Adds a smelting fuel type
     *
     * @param source     the fuel
     * @param burnTicks  the ticks to burn
     * @param returnItem the item to return TODO what?
     */
    void addSmeltingFuel(Item source, int burnTicks, Item returnItem);

    /**
     * Adds a shapeless recipe
     *
     * @param result the result of the recipe
     * @param source the item that the recipe consumes
     */
    default void addShapelessRecipe(Item result, Item source) {
        addShapelessRecipe(result, Collections.singletonList(source));
    }

    /**
     * Adds a shaped recipe using a single source
     *
     * @param result the result of the recipe
     * @param grid   the recipe shape
     * @param source the recipe ingredients
     */
    default void addShapedRecipe(Item result, char[][] grid, CraftTuple source) {
        addShapedRecipe(result, grid, Collections.singletonList(source));
    }

    /**
     * Adds a smelting fuel item
     *
     * @param source    the item type
     * @param burnTicks the ticks the item burns for
     */
    default void addSmeltingFuel(Item source, int burnTicks) {
        addSmeltingFuel(source, burnTicks, null);
    }
}
