package ca.skynetcloud.astralartificefabric.crafting.recipe;


import ca.skynetcloud.astralartificefabric.util.handler.BasicItemStackHandler;
import ca.skynetcloud.astralartificefabric.util.handler.SpecialRecipeHandler;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public class StarAltarRecipe implements Recipe<Container>, SpecialRecipeHandler {
    public static final int RECIPE_SIZE = 9;
    private final ItemStack result;
    private final ResourceLocation id;
    private final NonNullList<Ingredient> inputs;

    public StarAltarRecipe(ResourceLocation id, ItemStack result, NonNullList<Ingredient> inputs) {
        this.id = id;
        this.inputs = inputs;
        this.result = result;
    }
    @Override
    public boolean matches(Container container, Level level) {
        if(level.isClientSide) {
            return false;
        }
        return !this.inputs.isEmpty() && this.inputs.get(0).test(container.getItem(0)) && SpecialRecipeHandler.super.matches(container);
    }

    @Override
    public ItemStack assemble(Container container, RegistryAccess registryAccess) {
        return result;
    }

    @Override
    public boolean canCraftInDimensions(int i, int j) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return result;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.Instance;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<StarAltarRecipe> {
        private Type() {
        }
        public static final Type INSTANCE = new Type();
        public static final String ID = "star_altar";
    }
    public static class Serializer implements RecipeSerializer<StarAltarRecipe> {
        public static final Serializer Instance = new Serializer();
        public static final String ID = "star_altar";

        @Override
        public StarAltarRecipe fromJson(ResourceLocation id, JsonObject json) {
            var inputs = NonNullList.withSize(RECIPE_SIZE, Ingredient.EMPTY);
            var input = GsonHelper.getAsJsonObject(json, "input");

            inputs.set(0, Ingredient.fromJson(input));

            var ingredients = GsonHelper.getAsJsonArray(json, "ingredients");

            for (int i = 0; i < ingredients.size(); i++) {
                inputs.set(i + 1, Ingredient.fromJson(ingredients.get(i)));
            }

            var result = ShapedRecipe.itemStackFromJson(json.getAsJsonObject("result"));
            return new StarAltarRecipe(id, result, inputs);
        }

        @Override
        public StarAltarRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buffer) {
            int size = buffer.readVarInt();
            var inputs = NonNullList.withSize(size, Ingredient.EMPTY);

            for (int i = 0; i < size; i++) {
                inputs.set(i, Ingredient.fromNetwork(buffer));
            }

            var result = buffer.readItem();
            return new StarAltarRecipe(id,result,inputs);

        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, StarAltarRecipe recipe) {
            buffer.writeVarInt(recipe.inputs.size());

            for (var ingredient : recipe.inputs) {
                ingredient.toNetwork(buffer);
            }

            buffer.writeItem(recipe.result);
        }
    }
}