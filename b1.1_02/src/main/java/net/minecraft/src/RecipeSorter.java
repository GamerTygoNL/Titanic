package net.minecraft.src;

import java.util.Comparator;

class RecipeSorter
        implements Comparator {

    final CraftingManager craftingManager; /* synthetic field */

    RecipeSorter(CraftingManager craftingmanager) {
        craftingManager = craftingmanager;
    }

    public int compareRecipes(CraftingRecipe craftingrecipe, CraftingRecipe craftingrecipe1) {
        if (craftingrecipe1.getRecipeSize() < craftingrecipe.getRecipeSize()) {
            return -1;
        }
        return craftingrecipe1.getRecipeSize() <= craftingrecipe.getRecipeSize() ? 0 : 1;
    }

    public int compare(Object obj, Object obj1) {
        return compareRecipes((CraftingRecipe) obj, (CraftingRecipe) obj1);
    }
}
