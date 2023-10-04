package ca.skynetcloud.astralartificefabric.blockentities.bassclasses;

import ca.skynetcloud.astralartificefabric.util.handler.BasicItemStackHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class BasicInventoryBlockEntity extends BasicBlockEntity {
        public BasicInventoryBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
            super(type, pos, state);
        }

        public abstract BasicItemStackHandler getInventory();

        public void load(CompoundTag tag) {
            super.load(tag);
            this.getInventory().deserializeNBT(tag);
        }

        public void saveAdditional(CompoundTag tag) {
            tag.merge(this.getInventory().serializeNBT());
        }

        public boolean isUsableByPlayer(Player player) {
            BlockPos pos = this.getBlockPos();
            return player.distanceToSqr((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5) <= 64.0;
        }
}
