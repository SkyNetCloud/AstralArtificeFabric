package ca.skynetcloud.astralartificefabric.init;


import ca.skynetcloud.astralartificefabric.crafting.recipes.StarAltarRecipes;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

import static ca.skynetcloud.astralartificefabric.AstralArtificeFabric.MOD_ID;


public class RecipeInit {
    public static void registerRecipes() {
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, new ResourceLocation(MOD_ID + StarAltarRecipes.Serializer.ID), StarAltarRecipes.Serializer.Instance);
        Registry.register(BuiltInRegistries.RECIPE_TYPE, new ResourceLocation(MOD_ID + StarAltarRecipes.Type.ID), StarAltarRecipes.Type.INSTANCE);
    }
}
