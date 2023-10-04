package ca.skynetcloud.astralartificefabric;

import ca.skynetcloud.astralartificefabric.init.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AstralArtificeFabric implements ModInitializer {

    public static final String MOD_ID = "astralartifice";
    public static final String NAME = "Astral Artifice";
    public static final Logger LOGGER = LoggerFactory.getLogger(NAME);
    public static final CreativeModeTab CREATIVE_MODE_TAB = FabricItemGroup.builder()
            .title(Component.translatable("creativemodetab.minecraft.astralartifice"))
            .icon(() -> new ItemStack(BlocksInit.STAR_PEDESTAL.asItem()))
            .displayItems((CreativeModeTabsInit.displayItems)).build();

    @Override
    public void onInitialize() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, CreativeModeTabsInit.CREATIVE_MODE_TAB, CREATIVE_MODE_TAB);

        BlocksInit.registerBlocks();
        ItemInit.registerItems();
        BlockEntitiesInit.registerBlockEntities();
        RecipeInit.registerRecipes();


    }
}
