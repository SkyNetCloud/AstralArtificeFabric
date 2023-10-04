package ca.skynetcloud.astralartificefabric.init;


import ca.skynetcloud.astralartificefabric.AstralArtificeFabric;
import ca.skynetcloud.astralartificefabric.crafting.recipe.StarAltarRecipe;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import static ca.skynetcloud.astralartificefabric.AstralArtificeFabric.MOD_ID;
import static net.minecraft.core.registries.Registries.RECIPE_SERIALIZER;


public class ModRecipe {
    public static void registerRecipes() {
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, new ResourceLocation(MOD_ID + StarAltarRecipe.Serializer.ID), StarAltarRecipe.Serializer.Instance);
        Registry.register(BuiltInRegistries.RECIPE_TYPE, new ResourceLocation(MOD_ID + StarAltarRecipe.Type.ID), StarAltarRecipe.Type.INSTANCE);
    }
}
