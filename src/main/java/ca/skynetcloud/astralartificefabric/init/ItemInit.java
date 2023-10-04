package ca.skynetcloud.astralartificefabric.init;


import ca.skynetcloud.astralartificefabric.items.BasicItems;
import ca.skynetcloud.astralartificefabric.items.BasicWand;
import ca.skynetcloud.astralartificefabric.items.StarTillerItem;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;

import static ca.skynetcloud.astralartificefabric.AstralArtificeFabric.MOD_ID;

public class ItemInit {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, Registries.ITEM);

    public static RegistrySupplier<Item> BasicWandStarter;
    public static final RegistrySupplier<Item> FLAME_WAND;
    public static final RegistrySupplier<Item>  ICE_WAND;
    public static final RegistrySupplier<Item>  ROCK_WAND;
    public static final RegistrySupplier<Item>  STAR_TILLER_TOOL;
    public static final RegistrySupplier<Item>  STAR_GEM;

    static {
        BasicWandStarter = ITEMS.register("starter_wand", () -> new BasicWand(new Item.Properties()));
        FLAME_WAND = ITEMS.register("flame_wand", () -> new BasicWand(new Item.Properties()));
        ICE_WAND = ITEMS.register("ice_wand", () -> new BasicWand(new Item.Properties()));
        ROCK_WAND = ITEMS.register("rock_wand", () -> new BasicWand(new Item.Properties()));
        STAR_TILLER_TOOL = ITEMS.register("star_tiller_tool", () -> new StarTillerItem(Tiers.GOLD, new Item.Properties()));
        STAR_GEM = ITEMS.register("star_gem", () -> new BasicItems(new Item.Properties()));
    }


    public static void registerItems() {
        ITEMS.register();
    }
}
