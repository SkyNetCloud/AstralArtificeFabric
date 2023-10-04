package ca.skynetcloud.astralartificefabric.init;

import ca.skynetcloud.astralartificefabric.blockentities.StarAltarBlockEntity;
import ca.skynetcloud.astralartificefabric.blockentities.StarPedestalBlockEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;

import static ca.skynetcloud.astralartificefabric.AstralArtificeFabric.MOD_ID;

public class ModBlockEntities {
;
    public static BlockEntityType<StarPedestalBlockEntity> STAR_PEDESTAL;
    public static BlockEntityType<StarAltarBlockEntity> STAR_ALTAR;


    public static void registerBlockEntities() {

        STAR_PEDESTAL = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, MOD_ID + ":star_pedestal", BlockEntityType.Builder.of(StarPedestalBlockEntity::new, ModBlocks.STAR_PEDESTAL).build(null));
        STAR_ALTAR = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, MOD_ID + ":star_altar_block", BlockEntityType.Builder.of(StarAltarBlockEntity::new, ModBlocks.STAR_ALTAR).build(null));
     }
}
