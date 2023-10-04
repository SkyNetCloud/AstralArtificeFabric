package ca.skynetcloud.astralartifice.items;

import ca.skynetcloud.astralartifice.registry.ObjectRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class BasicWand extends BasicItems {
    public BasicWand(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (context.getClickedFace() == Direction.DOWN) {
            return InteractionResult.FAIL;
        }
        Level world = context.getLevel();
        Player player = context.getPlayer();
        InteractionHand interactionHand = context.getHand();
        ItemStack itemStack = player.getItemInHand(interactionHand);
        BlockPos center = context.getClickedPos();
        Block block = world.getBlockState(center).getBlock();
        Random random = new Random();


        if(player.isCrouching() && itemStack.is(new ItemStack(ObjectRegistry.FLAME_WAND.get()).getItem()) || itemStack.is(new ItemStack(ObjectRegistry.ROCK_WAND.get()).getItem()) || itemStack.is(new ItemStack(ObjectRegistry.ICE_WAND.get()).getItem())){
           if (block == Blocks.CLAY){
                   world.removeBlock(center, true);
                   world.setBlock(center, Blocks.AMETHYST_BLOCK.defaultBlockState(),1);
                   world.playSound(player, center,SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS,1.0F, 1.0F);
           }

        }

        return InteractionResult.SUCCESS;
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack itemStack = player.getItemInHand(interactionHand);
        Vec3 vec3 = player.getViewVector(1.0f);
        Random random = new Random();

        if (itemStack.is(new ItemStack(ObjectRegistry.FLAME_WAND.get()).getItem())){
            if (!level.isClientSide) {
                LargeFireball largeFireball = new LargeFireball(level, player, player.getX(), player.getEyeY(), player.getZ(), 1);
                largeFireball.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 3.0F, 0.0F);
                level.addFreshEntity(largeFireball);
                level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.GHAST_SHOOT, SoundSource.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
                if (!player.isCreative()) {
                    itemStack.setDamageValue(itemStack.getDamageValue() + 1);
                }
                return new InteractionResultHolder<>(InteractionResult.SUCCESS, itemStack);
            }
        }

        if (itemStack.is(new ItemStack(ObjectRegistry.ICE_WAND.get()).getItem())){
            if (!level.isClientSide) {
                LargeFireball largeFireball = new LargeFireball(level, player, player.getX(), player.getEyeY(), player.getZ(), 1);
                largeFireball.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 3.0F, 0.0F);
                level.addFreshEntity(largeFireball);
                level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.GHAST_SHOOT, SoundSource.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
                if (!player.isCreative()) {
                    itemStack.setDamageValue(itemStack.getDamageValue() + 1);
                }
                return new InteractionResultHolder<>(InteractionResult.SUCCESS, itemStack);
            }
        }

        if (itemStack.is(new ItemStack(ObjectRegistry.ROCK_WAND.get()).getItem())){
            if (!level.isClientSide) {
                LargeFireball largeFireball = new LargeFireball(level, player, player.getX(), player.getEyeY(), player.getZ(), 1);
                largeFireball.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 3.0F, 0.0F);
                level.addFreshEntity(largeFireball);
                level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.GHAST_SHOOT, SoundSource.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
                if (!player.isCreative()) {
                    itemStack.setDamageValue(itemStack.getDamageValue() + 1);
                }
                return new InteractionResultHolder<>(InteractionResult.SUCCESS, itemStack);
            }
        }

        return new InteractionResultHolder<>(InteractionResult.FAIL, itemStack);
    }

}
