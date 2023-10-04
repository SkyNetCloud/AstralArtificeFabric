package ca.skynetcloud.astralartificefabric.util.handler;

import ca.skynetcloud.astralartificefabric.util.RecipeMatcher;
import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;


public interface RecipeHandler extends Recipe<Container>  {
    @Override
    default boolean matches(Container inv, Level level) {
        return this.matches(inv);
    }
    default boolean matches(Container container) {
        return this.matches(container, 0, container.getContainerSize());
    }

    default boolean matches(Container container, int startIndex, int endIndex) {
        NonNullList<ItemStack> inputs = NonNullList.create();
        for (int i = startIndex; i < endIndex; i++) {
            inputs.add(container.getItem(i));
        }
        return RecipeMatcher.findMatches(inputs, this.getIngredients()) != null;
    }
}
