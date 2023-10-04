package ca.skynetcloud.astralartificefabric.init;

import ca.skynetcloud.astralartificefabric.blocks.StarAltarBlock;
import ca.skynetcloud.astralartificefabric.blocks.StarPedestalBlock;
import ca.skynetcloud.astralartificefabric.items.BasicBlockItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

import java.util.LinkedHashMap;
import java.util.Map;

import static ca.skynetcloud.astralartificefabric.AstralArtificeFabric.MOD_ID;


public class ModBlocks {
    public static final Map<ResourceLocation, Block> BLOCKS = new LinkedHashMap<>();
    public static final Map<ResourceLocation, BlockItem> BLOCK_ITEMS = new LinkedHashMap<>();

    public static final Block STAR_ALTAR = register(new StarAltarBlock(), "star_altar");
    public static final Block STAR_PEDESTAL = register(new StarPedestalBlock(), "star_pedestal");

    public static void registerBlocks() {
        BLOCKS.forEach((id, block) -> {
            Registry.register(BuiltInRegistries.BLOCK, id, block);
        });

        BLOCK_ITEMS.forEach((id, block_item) -> {
            Registry.register(BuiltInRegistries.ITEM, id, block_item);
        });
    }

    private static Block register(Block block, String name) {
        ResourceLocation id = new ResourceLocation(MOD_ID, name);
        BLOCKS.put(id, block);
        BLOCK_ITEMS.put(id, new BasicBlockItem(block));
        return block;
    }

    private static Block registerNoItem(Block block, String name) {
        var id = new ResourceLocation(MOD_ID, name);
        BLOCKS.put(id, block);
        return block;
    }
}
