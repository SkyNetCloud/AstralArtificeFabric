package ca.skynetcloud.astralartificefabric.blockentities.bassclasses;

import ca.skynetcloud.astralartificefabric.blocks.BasicBlock;
import ca.skynetcloud.astralartificefabric.util.helper.BasicBlockEntityHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BasicBlockEntity extends BlockEntity {
    public BasicBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this, BlockEntity::saveWithFullMetadata);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return this.saveWithFullMetadata();
    }

    public void markDirtyAndDispatch() {
        super.setChanged();
        BasicBlockEntityHelper.dispatchToNearbyPlayers(this);
    }
}
