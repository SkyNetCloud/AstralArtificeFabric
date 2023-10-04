package ca.skynetcloud.astralartificefabric.init;


import ca.skynetcloud.astralartificefabric.util.FeatureFlagDisplayItemGenerator;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import static ca.skynetcloud.astralartificefabric.AstralArtificeFabric.MOD_ID;


public class ModCreativeModeTabs {
    public static final ResourceKey<CreativeModeTab> CREATIVE_MODE_TAB = ResourceKey.create(Registries.CREATIVE_MODE_TAB, new ResourceLocation(MOD_ID, "creative_mode_tab"));

    public static final CreativeModeTab.DisplayItemsGenerator displayItems = FeatureFlagDisplayItemGenerator.create((parameters, output) -> {
        var stack = ItemStack.EMPTY;

        output.accept(ModBlocks.STAR_PEDESTAL);
        output.accept(ModBlocks.STAR_ALTAR);

        output.accept(ModItems.FLAME_WAND);
        output.accept(ModItems.ICE_WAND);
        output.accept(ModItems.ROCK_WAND);
        output.accept(ModItems.STAR_TILLER_TOOL);
        output.accept(ModItems.STAR_GEM);

    });
}
