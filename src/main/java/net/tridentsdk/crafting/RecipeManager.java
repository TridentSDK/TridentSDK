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

    private static RecipeManager instance;

    public static RecipeManager getInstance(){
        return instance;
    }

    public void addShapelessRecipe(Item result, Item source){
        addShapelessRecipe(result, Collections.singletonList(source));
    }

    public abstract void addShapelessRecipe(Item result, List<Item> source);

    public void addShapedRecipe(Item result, char[][] grid, CraftTuple source){
        addShapedRecipe(result, grid, Collections.singletonList(source));
    }

    public abstract void addShapedRecipe(Item result, char[][] grid, List<CraftTuple> source);

    public abstract void addSmeltingRecipe(Item result, Item source, int smeltTicks, float experience);

    public void addSmeltingFuel(Item source, int burnTicks){
        addSmeltingFuel(source, burnTicks, null);
    }

    public abstract void addSmeltingFuel(Item source, int burnTicks, Item returnItem);

}
