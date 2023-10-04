package ca.skynetcloud.astralartificefabric.items;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

import java.util.function.Function;

public class BasicBlockItem extends BlockItem {
    public BasicBlockItem(Block block) {
        super(block, new Properties());
    }

    public BasicBlockItem(Block block, Function<Properties, Properties> properties) {
        super(block, properties.apply(new Properties()));
    }
}