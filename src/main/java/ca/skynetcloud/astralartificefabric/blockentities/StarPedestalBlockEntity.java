package ca.skynetcloud.astralartificefabric.blockentities;

import ca.skynetcloud.astralartificefabric.blockentities.bassclasses.BasicInventoryBlockEntity;
import ca.skynetcloud.astralartificefabric.init.BlockEntitiesInit;
import ca.skynetcloud.astralartificefabric.util.handler.BasicItemStackHandler;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class StarPedestalBlockEntity extends BasicInventoryBlockEntity {
    private final BasicItemStackHandler inventory;

    public StarPedestalBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntitiesInit.STAR_PEDESTAL, pos, state);
        this.inventory = createInventoryHandler(this::markDirtyAndDispatch);
    }

    @Override
    public BasicItemStackHandler getInventory() {
        return this.inventory;
    }

    public static BasicItemStackHandler createInventoryHandler(Runnable onContentsChanged) {
        return BasicItemStackHandler.create(1, onContentsChanged, builder -> {
            builder.setDefaultSlotLimit(1);
        });
    }
}

