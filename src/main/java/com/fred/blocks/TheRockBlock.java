package com.fred.blocks;

import com.fred.Main;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.List;

public class TheRockBlock extends RedstoneBlock {
	private static final BooleanProperty POWERED = Properties.POWERED;

	public TheRockBlock(Settings settings) {
		super(settings);
		setDefaultState(getDefaultState().with(POWERED, false));
	}

	@Override
	public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
		tooltip.add(Text.translatable("block.fred.the_rock_block.tooltip").formatted(Formatting.AQUA));
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(POWERED);
	}

	@Override
	public boolean emitsRedstonePower(BlockState state) {
		return state.get(POWERED);
	}

	@Override
	public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
		if (state.get(POWERED)) {
			return 15;
		}

		return 0;
	}

	@Override
	protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
		if (!world.isClient()) {
			world.setBlockState(pos, state.cycle(POWERED));

			if (!state.get(POWERED)) {
				world.playSound(null, pos, Main.THE_ROCK_BLOCK_SCARE.get(), SoundCategory.PLAYERS, 0.7F, 1F);
				player.incrementStat(Main.ROCK_COUNT.get());
			}
		}

		return ActionResult.SUCCESS;
	}
}
