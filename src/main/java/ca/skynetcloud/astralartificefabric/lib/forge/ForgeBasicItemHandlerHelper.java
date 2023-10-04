package ca.skynetcloud.astralartificefabric.lib.forge;

import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ForgeBasicItemHandlerHelper {
    @NotNull
    public static ItemStack copyStackWithSize(@NotNull ItemStack itemStack, int size)
    {
        if (size == 0)
            return ItemStack.EMPTY;
        ItemStack copy = itemStack.copy();
        copy.setCount(size);
        return copy;
    }
}
