package ca.skynetcloud.astralartificefabric.compat.rei;

import ca.skynetcloud.astralartificefabric.crafting.recipes.StarAltarRecipes;
import ca.skynetcloud.astralartificefabric.init.BlocksInit;

import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;

public class ReiCompat implements REIClientPlugin {
    @Override
    public void registerCategories(CategoryRegistry registry) {
      var star_altar_cat = new StarAltarCategory();
      registry.add(star_altar_cat);
      registry.addWorkstations(star_altar_cat.getCategoryIdentifier(), EntryStacks.of(BlocksInit.STAR_ALTAR), EntryStacks.of(BlocksInit.STAR_PEDESTAL));
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(StarAltarRecipes.class, StarAltarRecipes.Type.INSTANCE, StarAltarCategory.RecipeDisplay::new);
    }
}
