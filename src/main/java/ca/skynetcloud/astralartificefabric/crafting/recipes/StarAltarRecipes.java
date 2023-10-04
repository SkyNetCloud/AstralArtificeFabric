package ca.skynetcloud.astralartificefabric.crafting.recipes;


import ca.skynetcloud.astralartificefabric.util.handler.RecipeHandler;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;

public class StarAltarRecipes implements RecipeHandler {
    public static final int RECIPE_SIZE = 9;
    private final ResourceLocation recipeId;
    private final NonNullList<Ingredient> inputs;
    private final ItemStack output;
    private final boolean transferNBT;

    public StarAltarRecipes(ResourceLocation recipeId, NonNullList<Ingredient> inputs, ItemStack output, boolean transferNBT) {
        this.recipeId = recipeId;
        this.inputs = inputs;
        this.output = output;
        this.transferNBT = transferNBT;
    }

    @Override
    public ItemStack assemble(Container inventory, RegistryAccess access) {
        var stack = inventory.getItem(0);
        var result = this.output.copy();

        if (this.transferNBT) {
            var tag = stack.getTag();

            if (tag != null) {
                result.setTag(tag.copy());

                return result;
            }
        }

        return result;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess access) {
        return this.output;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return this.inputs;
    }

    @Override
    public ResourceLocation getId() {
        return this.recipeId;
    }

    @Override
    public RecipeSerializer<StarAltarRecipes> getSerializer() {
        return Serializer.Instance;
    }

    @Override
    public RecipeType<? extends StarAltarRecipes> getType() {
        return Type.INSTANCE;
    }

    @Override
    public boolean matches(Container inventory) {
        var altarStack = inventory.getItem(0);
        return !this.inputs.isEmpty() && this.inputs.get(0).test(altarStack) && RecipeHandler.super.matches(inventory);
    }
    public static class Type implements RecipeType<StarAltarRecipes> {
        private Type() {
        }
        public static final Type INSTANCE = new Type();
        public static final String ID = "star_altar";
    }
    public static class Serializer implements RecipeSerializer<StarAltarRecipes> {
        public static final Serializer Instance = new Serializer();
        public static final String ID = "star_altar";
        @Override
        public StarAltarRecipes fromJson(ResourceLocation recipeId, JsonObject json) {
            var inputs = NonNullList.withSize(RECIPE_SIZE, Ingredient.EMPTY);
            var input = GsonHelper.getAsJsonObject(json, "input");

            inputs.set(0, Ingredient.fromJson(input));

            var ingredients = GsonHelper.getAsJsonArray(json, "ingredients");

            for (int i = 0; i < ingredients.size(); i++) {
                inputs.set(i + 1, Ingredient.fromJson(ingredients.get(i)));
            }

            var result = ShapedRecipe.itemStackFromJson(json.getAsJsonObject("result"));
            var transferNBT = GsonHelper.getAsBoolean(json, "transfer_nbt", false);

            return new StarAltarRecipes(recipeId, inputs, result, transferNBT);
        }

        @Override
        public StarAltarRecipes fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            int size = buffer.readVarInt();
            var inputs = NonNullList.withSize(size, Ingredient.EMPTY);

            for (int i = 0; i < size; i++) {
                inputs.set(i, Ingredient.fromNetwork(buffer));
            }

            var output = buffer.readItem();
            var transferNBT = buffer.readBoolean();

            return new StarAltarRecipes(recipeId, inputs, output, transferNBT);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, StarAltarRecipes recipe) {
            buffer.writeVarInt(recipe.inputs.size());

            for (var ingredient : recipe.inputs) {
                ingredient.toNetwork(buffer);
            }

            buffer.writeItem(recipe.output);
            buffer.writeBoolean(recipe.transferNBT);
        }
    }
}