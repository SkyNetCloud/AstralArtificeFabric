package ca.skynetcloud.astralartificefabric.init;


import ca.skynetcloud.astralartificefabric.util.FeatureFlagDisplayItemGenerator;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import static ca.skynetcloud.astralartificefabric.AstralArtificeFabric.MOD_ID;


public class CreativeModeTabsInit {
    public static final ResourceKey<CreativeModeTab> CREATIVE_MODE_TAB = ResourceKey.create(Registries.CREATIVE_MODE_TAB, new ResourceLocation(MOD_ID, "creative_mode_tab"));

    public static final CreativeModeTab.DisplayItemsGenerator displayItems = FeatureFlagDisplayItemGenerator.create((parameters, output) -> {
        var stack = ItemStack.EMPTY;

        output.accept(BlocksInit.STAR_PEDESTAL);
        output.accept(BlocksInit.STAR_ALTAR);

        output.accept(ItemInit.FLAME_WAND);
        output.accept(ItemInit.ICE_WAND);
        output.accept(ItemInit.ROCK_WAND);
        output.accept(ItemInit.STAR_TILLER_TOOL);
        output.accept(ItemInit.STAR_GEM);

    });
}
