package ca.skynetcloud.astralartificefabric.blocks;

import ca.skynetcloud.astralartificefabric.blockentities.StarPedestalBlockEntity;
import ca.skynetcloud.astralartificefabric.blockentities.bassclasses.BasicEntityBlock;
import ca.skynetcloud.astralartificefabric.util.helper.BasicStackHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class StarPedestalBlock extends BasicEntityBlock {


    public VoxelShape makeShape(){
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0, 0, 0, 1, 0.3125, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.0625, 0.9375, 0.1875, 0.1875, 1.0625, 0.4375),BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.0625, 0.9375, 0.5625, 0.1875, 1.0625, 0.8125),BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.5625, 0.9375, 0.8125, 0.8125, 1.0625, 0.9375),BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.8125, 0.9375, 0.5625, 0.9375, 1.0625, 0.8125),BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.8125, 0.9375, 0.1875, 0.9375, 1.0625, 0.4375),BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.5625, 0.9375, 0.0625, 0.8125, 1.0625, 0.1875),BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.1875, 0.9375, 0.0625, 0.4375, 1.0625, 0.1875),BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.1875, 0.9375, 0.8125, 0.4375, 1.0625, 0.9375),BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.6875, 1.0625, 0.125, 0.875, 1.125, 0.1875),BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.8125, 1.0625, 0.6875, 0.875, 1.125, 0.875),BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.125, 1.0625, 0.8125, 0.3125, 1.125, 0.875),BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.125, 1.0625, 0.125, 0.3125, 1.125, 0.1875),BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.8125, 1.0625, 0.1875, 0.875, 1.125, 0.3125),BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.125, 1.0625, 0.1875, 0.1875, 1.125, 0.3125),BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.125, 1.0625, 0.6875, 0.1875, 1.125, 0.8125),BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.6875, 1.0625, 0.8125, 0.8125, 1.125, 0.875),BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.125, 0.3125, 0.125, 0.875, 0.9375, 0.875),BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.1875, 0.9375, 0.1875, 0.8125, 1.0625, 0.8125),BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.3125, 1.0625, 0.3125, 0.6875, 1.1015625, 0.6875),BooleanOp.OR);

        return shape;
    }

    public StarPedestalBlock() {
        super(SoundType.STONE, 10.0F, 12.0F, true);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new StarPedestalBlockEntity(pos, state);
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult rayTraceResult) {
        var tile = world.getBlockEntity(pos);

        if (tile instanceof StarPedestalBlockEntity pedestal) {
            var inventory = pedestal.getInventory();
            var input = inventory.getItem(0);
            var held = player.getItemInHand(hand);

            if (input.isEmpty() && !held.isEmpty()) {
                inventory.setStackInSlot(0, BasicStackHelper.withSize(held, 1, false));
                player.setItemInHand(hand, BasicStackHelper.shrink(held, 1, false));
                world.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0F, 1.0F);
            } else if (!input.isEmpty()) {
                inventory.setStackInSlot(0, ItemStack.EMPTY);

                var item = new ItemEntity(world, player.getX(), player.getY(), player.getZ(), input);

                item.setNoPickUpDelay();
                world.addFreshEntity(item);
            }
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            var tile = world.getBlockEntity(pos);

            if (tile instanceof StarPedestalBlockEntity altar) {
                Containers.dropContents(world, pos, altar.getInventory().getStacks());
            }
        }

        super.onRemove(state, world, pos, newState, isMoving);
    }

//    @Override
//    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
//        return PEDESTAL_SHAPE;
//    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos post, CollisionContext context) {
        return makeShape();
    }

    @Override
    public VoxelShape getCollisionShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return makeShape();
    }

    @Override
    public VoxelShape getInteractionShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return makeShape();
    }

}