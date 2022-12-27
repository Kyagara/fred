package net.kyagara.fred.mixin;

import net.kyagara.fred.stat.ModStatistics;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public abstract class BlockMixin {
    @Inject(method = "onPlaced", at = @At("HEAD"), cancellable = true)
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer,
            ItemStack itemStack, CallbackInfo ci) {

        if (placer != null && placer.isPlayer()) {
            ((PlayerEntity) placer).incrementStat(ModStatistics.BLOCK_PLACED_COUNT);
        }
    }

    @Inject(method = "afterBreak", at = @At("HEAD"), cancellable = true)
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state,
            BlockEntity blockEntity, ItemStack stack, CallbackInfo ci) {

        player.incrementStat(ModStatistics.BLOCK_BREAK_COUNT);
    }
}